package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Controller.OrderResponseDto;
import com.example.ECommerceProject.Dto.Request.CheckOutCardRequestDto;
import com.example.ECommerceProject.Dto.Response.CartResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCardException;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Models.Item;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartResponseDto saveCart(int customerId, Item savedItem);

    OrderResponseDto checkOutCart(CheckOutCardRequestDto checkOutCardRequestDto) throws Exception;
}
