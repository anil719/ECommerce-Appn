package com.example.ECommerceProject.Dto.Response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeleteCartResponseDto {
    int cartTotal;

    int noOfItems;

    String customerName;

    List<ItemResponseDto> items;

    String status;
}





