package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Request.CardRequestDto;
import com.example.ECommerceProject.Dto.Response.CardResponseDto;
import com.example.ECommerceProject.Dto.Response.CustomerResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Models.Card;
import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Repository.CardRepository;
import com.example.ECommerceProject.Repository.CustomerRepository;
import com.example.ECommerceProject.Service.CardService;
import com.example.ECommerceProject.Transformer.CardTransformer;
import com.example.ECommerceProject.enums.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findByMobile(cardRequestDto.getMobileNo());
        if(customer == null) throw new InvalidCustomerException("Customer Doesnt Exists!!!");

        Card card = CardTransformer.CardResquestToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCardList().add(card);
        customerRepository.save(customer);

        //prepare response Dto

        CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(card);
        return cardResponseDto;
    }

    @Override
    public List<CardResponseDto> getAllVisaCards() {
        List<Card> cards = cardRepository.findAll();
        List<CardResponseDto> list = new ArrayList<>();
        for(Card card : cards){
            if(card.getCardType()== CardType.VISA){
                CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(card);
                list.add(cardResponseDto);
            }
        }
        return list;
    }

    @Override
    public List<CardResponseDto> getCardsWithExpiryDateGreaterThanGivenDate(Date expiryDate) {
        List<Card> cards = cardRepository.getCardsGreaterThanGivenExpiryDate(expiryDate);
        List<CardResponseDto> list = new ArrayList<>();
        for(Card card : cards){
            CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(card);
            list.add(cardResponseDto);
        }
        return list;
    }

    @Override
    public List<CardResponseDto> getMasterCards() {
        List<Card> cards = cardRepository.getMasterCardsWithExpiryDateGreaterThanJan1st2k25();
        List<CardResponseDto> list = new ArrayList<>();
        for(Card card : cards){
            if(card.getCardType()== CardType.MASTERCARD) {
                CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(card);
                list.add(cardResponseDto);
            }
        }
        return list;
    }
}
