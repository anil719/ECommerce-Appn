package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Request.OrderRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteOrderResponseDto;
import com.example.ECommerceProject.Dto.Response.ItemResponseDto;
import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCardException;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Exceptions.InvalidProductException;
import com.example.ECommerceProject.Models.*;
import com.example.ECommerceProject.Repository.CardRepository;
import com.example.ECommerceProject.Repository.CustomerRepository;
import com.example.ECommerceProject.Repository.OrderRepository;
import com.example.ECommerceProject.Repository.ProductRepository;
import com.example.ECommerceProject.Service.OrderService;
import com.example.ECommerceProject.Service.ProductService;
import com.example.ECommerceProject.Transformer.ItemTransformer;
import com.example.ECommerceProject.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    private JavaMailSender emailSender;
    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNum(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMasterCard(card.getCardNum());

        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item : cart.getItems()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }
            catch (Exception e){
                throw new Exception("Product Out of stock");
            }
        }
        order.setItemList(orderedItems);
        order.setTotalValue(cart.getCartTotal());


        return order;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerException("Customer Id is invalid");
        }
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new InvalidProductException("Product doesnt Exist");
        }


        Card card = cardRepository.findByCardNum(orderRequestDto.getCardNo());
        if(card == null || card.getCvv() != orderRequestDto.getCvv() || card.getCustomer() != customer)
            throw new InvalidCardException("Invalid Card!!!");


        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try {
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNum(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMasterCard(card.getCardNum());

        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItemList().add(item);

        customer.getOrderedList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderRepository.save(order);

        //For sending Email

        String text = "Hi " + customer.getName() + ",\n" +
                "Thank you for shopping with " + "eCommerce Shop KPHB"  + "!\n" +
                " Your Order of " + product.getName() + " has been placed with OrderId:"
                + order.getOrderNum()
                + "\n" + "It will be delivered to u within 4 days..";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("anilterapalli@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("eCommerce Shop KPHB : " + "Order Placed Successfully");
        message.setText(text);
        emailSender.send(message);


        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(savedOrder);

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item items : savedOrder.getItemList()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(items);
            itemResponseDtos.add(itemResponseDto);
        }

        orderResponseDto.setItems(itemResponseDtos);
        return orderResponseDto;
    }

    public String generateMasterCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0; i < cardNo.length()-4; i++){
            maskedCardNo += 'X';
        }
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;
    }

    @Override
    public List<OrderResponseDto> ordersOfACustomer(int customerId) throws InvalidCustomerException {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer With this Id is not present");
        }

        List<Ordered> orders = customer.getOrderedList();
        List<OrderResponseDto> ans = new ArrayList<>();
        for(Ordered order : orders){
            OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(order);
            List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
            for(Item items : order.getItemList()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(items);
                itemResponseDtos.add(itemResponseDto);
            }
            orderResponseDto.setItems(itemResponseDtos);

            ans.add(orderResponseDto);
        }
        return ans;
    }

    @Override
    public List<OrderResponseDto> recentFiveOrders() {

        List<Ordered> orders= orderRepository.getLast5orders();
        List<OrderResponseDto> ans = new ArrayList<>();
        for(Ordered order : orders){
            OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(order);
            List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
            for(Item items : order.getItemList()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(items);
                itemResponseDtos.add(itemResponseDto);
            }
            orderResponseDto.setItems(itemResponseDtos);

            ans.add(orderResponseDto);
        }
        return ans;
    }

    @Override
    public DeleteOrderResponseDto deleteOrder(int id) throws Exception {
        Ordered order = null;
        try {
            order = orderRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new Exception("OrderId not found!!!");
        }
        orderRepository.delete(order);

        DeleteOrderResponseDto deleteOrderResponseDto = OrderTransformer.orderToResponseDto(order);
        return deleteOrderResponseDto;
    }
}