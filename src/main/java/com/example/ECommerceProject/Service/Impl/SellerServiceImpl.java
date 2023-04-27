package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Request.SellerRequestDto;
import com.example.ECommerceProject.Dto.Request.UpdateSellerRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.GetSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.SellerResponseDto;
import com.example.ECommerceProject.Dto.Response.UpdateSellerResponseDto;
import com.example.ECommerceProject.Exceptions.DuplicateSellerException;
import com.example.ECommerceProject.Models.Seller;
import com.example.ECommerceProject.Repository.SellerRepository;
import com.example.ECommerceProject.Service.SellerService;
import com.example.ECommerceProject.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws DuplicateSellerException {

       if(sellerRepository.findByEmail(sellerRequestDto.getEmail()) != null) throw new DuplicateSellerException("Email Id Already Registered");

        Seller seller = SellerTransformer.SellerRequestToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }

    @Override
    public GetSellerResponseDto getSellerByEmail(String email) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception("Email Id is not registered");
        }

        GetSellerResponseDto getSellerResponseDto = SellerTransformer.SellerObjToGetSellerResponse(seller);
        return getSellerResponseDto;
    }

    @Override
    public GetSellerResponseDto getSellerById(int id) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new Exception("Seller Id is not Present");
        }

        GetSellerResponseDto getSellerResponseDto = SellerTransformer.SellerObjToGetSellerResponse(seller);
        return getSellerResponseDto;
    }

    @Override
    public List<GetSellerResponseDto> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        List<GetSellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller : sellers){
            GetSellerResponseDto getSellerResponseDto = new GetSellerResponseDto();
            getSellerResponseDto.setId(seller.getId());
            getSellerResponseDto.setName(seller.getName());
            getSellerResponseDto.setMobile(seller.getMobile());
            getSellerResponseDto.setAge(seller.getAge());
            getSellerResponseDto.setEmail(seller.getEmail());
            sellerResponseDtoList.add(getSellerResponseDto);
        }
        return sellerResponseDtoList;
    }

    @Override
    public UpdateSellerResponseDto updateSellerByEmail(String email, UpdateSellerRequestDto updateSellerRequestDto) throws Exception {
        Seller seller ;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception("Email Id is not valid");
        }

        seller.setMobile(updateSellerRequestDto.getMobile());
        seller.setAge(updateSellerRequestDto.getAge());
        Seller updateSeller = sellerRepository.save(seller);

        UpdateSellerResponseDto updateSellerResponseDto = SellerTransformer.updateTOSellerResponseDto(updateSeller);
        return updateSellerResponseDto;

    }

    @Override
    public DeleteSellerResponseDto deleteSellerByEmail(String email) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception("There is no seller with this EmailId");
        }
        sellerRepository.delete(seller);
        return SellerTransformer.deleteSeller(seller);
    }

    @Override
    public DeleteSellerResponseDto deleteSellerById(int id) throws Exception {

        Seller seller ;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch (Exception e){
            throw new Exception("seller Id is not valid");
        }

        sellerRepository.delete(seller);
        return SellerTransformer.deleteSeller(seller);
    }

    @Override
    public List<GetSellerResponseDto> sellersWithGivenAge(int age) throws Exception {
        List<Seller> sellers = sellerRepository.findAll();
        List<Seller> sellerWithRequiredAge = new ArrayList<>();
        for(Seller seller : sellers){
            if(seller.getAge() == age) sellerWithRequiredAge.add(seller);
        }
        if(sellerWithRequiredAge.isEmpty()) throw new Exception("No seller Present with this age");

        List<GetSellerResponseDto> list = new ArrayList<>();
        for(Seller seller : sellerWithRequiredAge){
            GetSellerResponseDto s1 = SellerTransformer.SellerObjToGetSellerResponse(seller);
            list.add(s1);
        }
        return list;
    }
}
