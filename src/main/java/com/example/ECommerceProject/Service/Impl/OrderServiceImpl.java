package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Models.*;
import com.example.ECommerceProject.Service.OrderService;
import com.example.ECommerceProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;
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

    public String generateMasterCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0; i < cardNo.length()-4; i++){
            maskedCardNo += 'X';
        }
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;
    }
}
