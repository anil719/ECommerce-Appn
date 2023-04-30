package com.example.ECommerceProject.Service.Impl;

import com.example.ECommerceProject.Dto.Request.ItemRequestDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Exceptions.InvalidProductException;
import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Models.Item;
import com.example.ECommerceProject.Models.Product;
import com.example.ECommerceProject.Repository.CustomerRepository;
import com.example.ECommerceProject.Repository.ItemRepository;
import com.example.ECommerceProject.Repository.ProductRepository;
import com.example.ECommerceProject.Service.ItemService;
import com.example.ECommerceProject.Transformer.ItemTransformer;
import com.example.ECommerceProject.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerException("Customer Id is invalid");
        }
        Product product;
        try {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new InvalidProductException("Product doesnt Exist");
        }

        //check availability of product
        if (itemRequestDto.getRequiredQuantity() > product.getQuantity() || product.getProductStatus() != ProductStatus.AVAILABLE)
            throw new Exception("Product out of Stock");


        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        System.out.println(customer.getCart().getItems().size());
        item.setCart(customer.getCart());
        item.setProduct(product);
        product.getItemList().add(item);
        //  Product savedProduct = productRepository.save(product);
        //get saved item
        //int size = product.getItemList().size();
        return itemRepository.save(item);
    }
}
