package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.SellerRequestDto;
import com.example.ECommerceProject.Dto.Request.UpdateSellerRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.GetSellerResponseDto;
import com.example.ECommerceProject.Dto.Response.SellerResponseDto;
import com.example.ECommerceProject.Dto.Response.UpdateSellerResponseDto;
import com.example.ECommerceProject.Exceptions.DuplicateSellerException;
import com.example.ECommerceProject.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")

public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws DuplicateSellerException {

        try{
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }


    //  GET a seller by email
    @GetMapping("/get_by_Email")
    public ResponseEntity getSellerByEmail(@RequestParam("email")String email) throws Exception {
        try {
            GetSellerResponseDto getSellerResponseDto = sellerService.getSellerByEmail(email);
            return new ResponseEntity(getSellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get by id
    @GetMapping("/get_by_id")
    public ResponseEntity getSellerById(@RequestParam("id")int id) throws Exception {

        try {
            GetSellerResponseDto getSellerResponseDto = sellerService.getSellerById(id);
            return new ResponseEntity(getSellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // get all seller

    @GetMapping("get_all")
    public List<GetSellerResponseDto> getAllSeller(){
        return sellerService.getAllSellers();
    }

    // update seller info based on email id
    @PutMapping("/update_by_email")
    public ResponseEntity updateSellerByEmail(@RequestParam("email")String email, @RequestBody UpdateSellerRequestDto updateSellerRequestDto) throws Exception {

        try {
            UpdateSellerResponseDto updateSellerResponseDto = sellerService.updateSellerByEmail(email,updateSellerRequestDto);
            return new ResponseEntity(updateSellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // delete a seller based on email
    @DeleteMapping("delete_by_email")
    public ResponseEntity deleteSellerByEmail(@RequestParam("email")String email) throws Exception{
        try {
            DeleteSellerResponseDto deleteSellerResponseDto = sellerService.deleteSellerByEmail(email);
            return new ResponseEntity(deleteSellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //delete by id

    @DeleteMapping("/delete_by_id")
    public ResponseEntity deleteById(@RequestParam("id")int id) throws Exception {

        try {
            DeleteSellerResponseDto deleteSellerResponseDto = sellerService.deleteSellerById(id);
            return new ResponseEntity(deleteSellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all sellers of a particular age

    @GetMapping("/sellers_with_given_age")
    public ResponseEntity sellersWithGivenAge(@RequestParam("age")int age)throws Exception{
        try{
            List<GetSellerResponseDto> li = sellerService.sellersWithGivenAge(age);
            return new ResponseEntity(li, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}