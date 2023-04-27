package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.SellerRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.GetSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.SellerResponseDto;
import com.example.ECommerceProject.Dto.Response.UpdateSellerResponseDto;
import com.example.ECommerceProject.Models.Seller;
import com.example.ECommerceProject.Repository.SellerRepository;

public class SellerTransformer {
    public static Seller SellerRequestToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder().name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .email(sellerRequestDto.getEmail())
                .mobile(sellerRequestDto.getMobile()).build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder().name(seller.getName())
                .age(seller.getAge())
                .status("Seller Added Successfully").build();
    }

    public static GetSellerResponseDto SellerObjToGetSellerResponse(Seller seller){
        return GetSellerResponseDto.builder()
                .id(seller.getId())
                .age(seller.getAge())
                .mobile(seller.getMobile())
                .email(seller.getEmail())
                .name(seller.getName()).build();
    }

    public static UpdateSellerResponseDto updateTOSellerResponseDto(Seller seller){
        return UpdateSellerResponseDto.builder()
                .id(seller.getId())
                .age(seller.getAge())
                .name(seller.getName())
                .mobile(seller.getMobile())
                .email(seller.getEmail())
                .status("Seller details Updated").build();
    }

    public static DeleteSellerResponseDto deleteSeller(Seller seller){
        return DeleteSellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .mobile(seller.getMobile())
                .status("Seller Deleted Successfully").build();
    }
}
