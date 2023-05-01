package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.CardRequestDto;
import com.example.ECommerceProject.Dto.Response.CardResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CardService {
    CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;

    List<CardResponseDto> getAllVisaCards();

    List<CardResponseDto> getCardsWithExpiryDateGreaterThanGivenDate(Date expiryDate);

    List<CardResponseDto> getMasterCards();
}
