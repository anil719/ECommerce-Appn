package com.example.ECommerceProject.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteOrderResponseDto {

    Date orderDate;
    String orderNum;
    int totalValue;
    String cardUsed;
    String status;
}