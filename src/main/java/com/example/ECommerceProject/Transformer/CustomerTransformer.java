package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Request.UpdateCustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.*;
import com.example.ECommerceProject.Models.Customer;
import lombok.AccessLevel;
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

    public static CustomerResponseDTO2 customerToCustomerResponseDTO2(Customer customer){
        return CustomerResponseDTO2.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .age(customer.getAge())
                .email(customer.getEmail())
                .address(customer.getAddress()).build();
    }

    public static DeleteCustomerResponseDto customerToDeleteCustomerResponseDto(Customer customer){
        return DeleteCustomerResponseDto.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .message("Customer Deleted Successfully").build();
    }

    public static UpdateCustomerResponseDto customerToResponse(Customer customer){
        return UpdateCustomerResponseDto.builder()
                .age(customer.getAge())
                .mobile(customer.getMobile())
                .message("Customer Details Updated").build();
    }

}
