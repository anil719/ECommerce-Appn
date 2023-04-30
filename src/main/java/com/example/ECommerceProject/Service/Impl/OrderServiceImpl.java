package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Request.OrderRequestDto;
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
import com.example.ECommerceProject.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(savedOrder.getOrderDate());
        orderResponseDto.setCardUsed(savedOrder.getCardUsed());
        orderResponseDto.setCustomerName(customer.getName());
        orderResponseDto.setOrderNum(savedOrder.getOrderNum());
        orderResponseDto.setTotalValue(savedOrder.getTotalValue());


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
}
