package com.example.ECommerceProject.Dto.Response;

import com.example.ECommerceProject.Dto.Response.ItemResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)

public class OrderResponseDto {

    String orderNum;
    Date orderDate;
    int totalValue;
    String cardUsed;
    String customerName;
    List<ItemResponseDto> items;
}
