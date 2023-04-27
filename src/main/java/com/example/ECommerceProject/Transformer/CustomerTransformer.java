package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.CustomerResponseDto;
import com.example.ECommerceProject.Models.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerTransformer {

    public static Customer CustomerResquestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobile(customerRequestDto.getMobile())
                .address(customerRequestDto.getAddress())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .message("Hi!" + customer.getName() +" Welcome TO Ecommerce!!").build();
    }
}
