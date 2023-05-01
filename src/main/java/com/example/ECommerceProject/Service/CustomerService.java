package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Request.UpdateCustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.*;
import com.example.ECommerceProject.Exceptions.MobileNoAlreadyPresent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresent;

    List<CustomerResponseDTO2> getAllCustomers();

    CustomerResponseDTO2 getCustomerByEmail(String email) throws Exception;

    List<CustomerResponseDTO2> getCustomersWithVisaCard();

    List<CustomerResponseDTO2> getCustomerWithGreaterAge(int age);

    DeleteCustomerResponseDto deleteByMobile(String mobile) throws Exception;

    UpdateCustomerResponseDto updateByEmail(String email, UpdateCustomerRequestDto updateCustomerRequestDto);
}
