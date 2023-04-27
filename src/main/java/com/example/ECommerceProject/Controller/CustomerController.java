package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.CustomerResponseDto;
import com.example.ECommerceProject.Exceptions.MobileNoAlreadyPresent;
import com.example.ECommerceProject.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresent {
        return customerService.addCustomer(customerRequestDto);
    }


    //view all customers
    @GetMapping("/getAllCustomers")
    public List<CustomerResponseDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    //get customer by email

    @GetMapping("/getbyEmail")
    public CustomerResponseDto getCustomerByEmail(@RequestParam("email")String email) throws Exception {
        return customerService.getCustomerByEmail(email);
    }



    //get all customers whose age is greater than 25

    //get all customers who use visa card
    @GetMapping("/getCustomersWhoUseVisaCard")
    public List<CustomerResponseDto> getCustomersWithVisaCard(){
        return customerService.getCustomersWithVisaCard();
    }

    //update a customer Info by email

    //delete a customer by email/mobile
}
