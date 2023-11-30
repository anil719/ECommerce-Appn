package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Response.CartItemsResponseDto;
import com.example.ECommerceProject.Dto.Response.DeleteCartResponseDto;
import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Dto.Request.CheckOutCardRequestDto;
import com.example.ECommerceProject.Dto.Response.CartResponseDto;
import com.example.ECommerceProject.Models.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartResponseDto saveCart(int customerId, Item savedItem);

    OrderResponseDto checkOutCart(CheckOutCardRequestDto checkOutCardRequestDto) throws Exception;


    List<CartItemsResponseDto> getItemsInCart();

    DeleteCartResponseDto deleteCart(int id) throws Exception;
}
