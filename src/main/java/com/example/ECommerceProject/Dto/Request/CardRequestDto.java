package com.example.ECommerceProject.Dto.Request;

import com.example.ECommerceProject.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    String mobileNo;
    String cardNum ;

    int cvv;

    Date expiryDate;

    CardType cardType;

}
