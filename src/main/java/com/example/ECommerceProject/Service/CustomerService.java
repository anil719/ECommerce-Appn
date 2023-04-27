package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.CustomerResponseDto;
import com.example.ECommerceProject.Exceptions.MobileNoAlreadyPresent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresent;

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerByEmail(String email) throws Exception;

    List<CustomerResponseDto> getCustomersWithVisaCard();
}
