package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Models.Card;
import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Models.Ordered;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Ordered placeOrder(Customer customer, Card card) throws Exception;

}
