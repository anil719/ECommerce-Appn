package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Dto.Request.CheckOutCardRequestDto;
import com.example.ECommerceProject.Dto.Response.CartResponseDto;
import com.example.ECommerceProject.Dto.Response.ItemResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCardException;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Models.*;
import com.example.ECommerceProject.Repository.CardRepository;
import com.example.ECommerceProject.Repository.CartRepository;
import com.example.ECommerceProject.Repository.CustomerRepository;
import com.example.ECommerceProject.Repository.OrderRepository;
import com.example.ECommerceProject.Service.CartService;
import com.example.ECommerceProject.Service.OrderService;
import com.example.ECommerceProject.Service.ProductService;
import com.example.ECommerceProject.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

   @Autowired
    ProductService productService;

    @Override
    public CartResponseDto saveCart(int customerId, Item item) {
        Customer customer = customerRepository.findById(customerId).get();

        Cart cart = customer.getCart();
        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setNoOfItems(cart.getItems().size());
        Cart savedcart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(customer.getName())
                .noOfItems(cart.getNoOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item items : cart.getItems()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(items);
            itemResponseDtoList.add(itemResponseDto);
        }
       cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }



    @Override
    public OrderResponseDto checkOutCart(CheckOutCardRequestDto checkOutCardRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(checkOutCardRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new InvalidCustomerException("Customer id is Invalid");
        }

        Card card = cardRepository.findByCardNum(checkOutCardRequestDto.getCardNo());
        if(card == null || card.getCvv() != checkOutCardRequestDto.getCvv() || card.getCustomer() != customer)
            throw new InvalidCardException("Invalid Card!!!");


        Cart cart = customer.getCart()  ;
        if(cart.getNoOfItems() == 0) throw new Exception("Cart is empty!!!");


        try {
            Ordered order = orderService.placeOrder(customer, card);    //this will throw exception if product goes out of stock
            customer.getOrderedList().add(order);
            resetCart(cart);
           // customerRepository.save(customer);
            Ordered savedOrder = orderRepository.save(order);

            //prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNum(savedOrder.getOrderNum());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item item : savedOrder.getItemList()){
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        cart.setNoOfItems(0);
        cart.setItems(new ArrayList<>());
    }


}
