package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.CardRequestDto;
import com.example.ECommerceProject.Dto.Response.CardResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public CardResponseDto addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException {
       return cardService.addCard(cardRequestDto);
    }

    //get all visa cards
    @GetMapping("/allVisaCard")
    public List<CardResponseDto> getAllVisaCards(){
        return cardService.getAllVisaCards();
    }




    //get all master card whose expiry date greater than given date
    @GetMapping("/getCardsWithExpiryDateGreaterThanGivenDate")
    public List<CardResponseDto> getCardsWithExpiryDateGreaterThanGivenDate(@RequestParam("expiryDate")
                                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date expiryDate){
        return cardService.getCardsWithExpiryDateGreaterThanGivenDate(expiryDate);
    }


    //get all master card whose expiry date greater than 1st jan 2k25
    @GetMapping("/masterCardsWithExpiryDate Greater than jan 1st 2k25")
    public List<CardResponseDto> masterCards(){
        return cardService.getMasterCards();
    }


}
