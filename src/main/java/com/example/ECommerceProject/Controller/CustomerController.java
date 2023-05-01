package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Request.UpdateCustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.*;
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
    public List<CustomerResponseDTO2> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    //get customer by email
    @GetMapping("/getbyEmail")
    public CustomerResponseDTO2 getCustomerByEmail(@RequestParam("email")String  email) throws Exception {
        return customerService.getCustomerByEmail(email);
    }

    //get all customers whose age is greater than 25
    @GetMapping("/getByAgeGreaterThan")
    public List<CustomerResponseDTO2> getByAgeGreaterThan25(@RequestParam("age")int age){
        return customerService.getCustomerWithGreaterAge(age);
    }


    //get all customers who use visa card
    @GetMapping("/getCustomersWhoUseVisaCard")
    public List<CustomerResponseDTO2> getCustomersWithVisaCard(){
        return customerService.getCustomersWithVisaCard();
    }


    //delete by Mobile
    @DeleteMapping("/deleteByMobile")
    //delete a customer by mobile
    public DeleteCustomerResponseDto deleteByMobile(@RequestParam("mobile")String email) throws Exception {
        return customerService.deleteByMobile(email);
    }


    //update a customer Info by email
    @PutMapping("/updateByEmail")
    public UpdateCustomerResponseDto updateByEmail(@RequestParam("email")String email, @RequestBody UpdateCustomerRequestDto
                                                   updateCustomerRequestDto){
        return customerService.updateByEmail(email, updateCustomerRequestDto);
    }


}
