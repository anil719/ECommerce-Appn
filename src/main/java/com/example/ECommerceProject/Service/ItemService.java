package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.ItemRequestDto;
import com.example.ECommerceProject.Exceptions.InvalidCustomerException;
import com.example.ECommerceProject.Exceptions.InvalidProductException;
import com.example.ECommerceProject.Models.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
