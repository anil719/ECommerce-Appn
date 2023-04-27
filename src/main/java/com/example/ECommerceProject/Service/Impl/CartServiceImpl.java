package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Response.CartResponseDto;
import com.example.ECommerceProject.Dto.Response.CustomerResponseDto;
import com.example.ECommerceProject.Dto.Response.ItemResponseDto;
import com.example.ECommerceProject.Models.Cart;
import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Models.Item;
import com.example.ECommerceProject.Repository.CartRepository;
import com.example.ECommerceProject.Repository.CustomerRepository;
import com.example.ECommerceProject.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

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
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(items.getProduct().getPrice());
            itemResponseDto.setTotalPrice(items.getRequiredQuantity()*item.getProduct().getPrice());
            itemResponseDto.setProductName(items.getProduct().getName());
            itemResponseDto.setQuantity(items.getRequiredQuantity());
            itemResponseDtoList.add(itemResponseDto);
        }
       cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }
}
