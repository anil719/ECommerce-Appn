package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.CardRequestDto;
import com.example.ECommerceProject.Dto.Response.CardResponseDto;
import com.example.ECommerceProject.Models.Card;

public class CardTransformer {

    public static Card CardResquestToCard(CardRequestDto cardRequestDto){
            return Card.builder()
                    .cardNum(cardRequestDto.getCardNum())
                    .cvv(cardRequestDto.getCvv())
                    .expiryDate(cardRequestDto.getExpiryDate())
                    .cardType(cardRequestDto.getCardType()).build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNum())
                .cvv(card.getCvv())
                .expiryDate(card.getExpiryDate())
                .cardType(card.getCardType()).build();
    }


}
