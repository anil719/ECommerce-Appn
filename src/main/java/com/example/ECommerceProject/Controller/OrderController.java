package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.ItemRequestDto;
import com.example.ECommerceProject.Dto.Request.OrderRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteOrderResponseDto;
import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/place")
    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
        return orderService.placeOrder(orderRequestDto);
    }




    //get All the orders for a customer
    @GetMapping("/getAllOrdersOfCustomer")
    public List<OrderResponseDto> ordersOfACustomer(@RequestParam("customerId")int customerId) throws InvalidCustomerException {
        return orderService.ordersOfACustomer(customerId);
    }


  //  get recent 5 orders
    @GetMapping("/recentFiveOrders")
    public List<OrderResponseDto> recentFiveOrders(){
        return orderService.recentFiveOrders();
    }

    //delete an order from the order list
    @DeleteMapping("/delete")
    public DeleteOrderResponseDto deleteOrderFromOrderList(@RequestParam("id")int id) throws Exception {
        return orderService.deleteOrder(id);
    }

}
