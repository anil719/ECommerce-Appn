package com.example.ECommerceProject.Dto.Response;

import com.example.ECommerceProject.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {

    String cardNo;
    String customerName;
    int cvv;
    Date expiryDate;
    CardType cardType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder
    public static class UpdateCustomerResponseDto {

        String mobile;
        int age;
    }
}
