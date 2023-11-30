package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Response.DeleteOrderResponseDto;
import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Models.Ordered;

public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(Ordered ordered){
        return OrderResponseDto.builder()
                .orderNum(ordered.getOrderNum())
                .orderDate(ordered.getOrderDate())
                .cardUsed(ordered.getCardUsed())
                .totalValue(ordered.getTotalValue())
                .customerName(ordered.getCustomer().getName())
                .build();
    }

    public static DeleteOrderResponseDto orderToResponseDto(Ordered order){
        return DeleteOrderResponseDto.builder()
                .orderNum(order.getOrderNum())
                .orderDate(order.getOrderDate())
                .totalValue(order.getTotalValue())
                .cardUsed(order.getCardUsed())
                .status("Order Deleted ").build();
    }
}
