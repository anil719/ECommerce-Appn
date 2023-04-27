package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Request.CustomerRequestDto;
import com.example.ECommerceProject.Dto.Response.CustomerResponseDto;
import com.example.ECommerceProject.Exceptions.MobileNoAlreadyPresent;
import com.example.ECommerceProject.Models.Card;
import com.example.ECommerceProject.Models.Cart;
import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Repository.CardRepository;
import com.example.ECommerceProject.Repository.CustomerRepository;
import com.example.ECommerceProject.Service.CustomerService;
import com.example.ECommerceProject.Transformer.CustomerTransformer;
import com.example.ECommerceProject.enums.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresent {

        if(customerRepository.findByMobile(customerRequestDto.getMobile()) != null){
            throw new MobileNoAlreadyPresent("Sorry!! This mobile number is Already Registered!!! ");
        }

        //customer RequestDto to customer to save it in repo
        Customer customer = CustomerTransformer.CustomerResquestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .noOfItems(0)
                .customer(customer).build();

        customer.setCart(cart);
        Customer savedCustomer =  customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> ans = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(customer);
            ans.add(customerResponseDto);
        }
        return ans;
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception("Customer With This Email Id is not exist");
        }

        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getCustomersWithVisaCard() {
        List<Card> cardList = cardRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        for(Card card : cardList){
           if( card.getCardType() == CardType.VISA ) customers.add(card.getCustomer());
        }
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }
}
