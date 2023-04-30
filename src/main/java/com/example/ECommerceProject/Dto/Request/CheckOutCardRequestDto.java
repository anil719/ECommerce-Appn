package com.example.ECommerceProject.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CheckOutCardRequestDto {
    int customerId;
    String cardNo;
    int cvv;
}
