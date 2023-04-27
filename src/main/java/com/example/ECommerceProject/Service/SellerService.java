package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.SellerRequestDto;
import com.example.ECommerceProject.Dto.Request.UpdateSellerRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.GetSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.SellerResponseDto;
import com.example.ECommerceProject.Dto.Response.UpdateSellerResponseDto;
import com.example.ECommerceProject.Exceptions.DuplicateSellerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {
    public DeleteSellerResponseDto deleteSellerByEmail(String email) throws Exception;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws DuplicateSellerException;

    GetSellerResponseDto getSellerByEmail(String email) throws Exception;

    GetSellerResponseDto getSellerById(int id) throws Exception;

    List<GetSellerResponseDto> getAllSellers();

    UpdateSellerResponseDto updateSellerByEmail(String email, UpdateSellerRequestDto updateSellerRequestDto) throws Exception;

    DeleteSellerResponseDto deleteSellerById(int id) throws Exception;

    List<GetSellerResponseDto> sellersWithGivenAge(int age) throws Exception;

}
