package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.OrderRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteOrderResponseDto;
import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Models.Card;
import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Models.Ordered;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public Ordered placeOrder(Customer customer, Card card) throws Exception;

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;

    List<OrderResponseDto> ordersOfACustomer(int customerId) throws InvalidCustomerException;

    List<OrderResponseDto> recentFiveOrders();

    DeleteOrderResponseDto deleteOrder(int id) throws Exception;

}
