package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.ItemRequestDto;
import com.example.ECommerceProject.Models.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto) {
       return Item.builder()
               .requiredQuantity(itemRequestDto.getRequiredQuantity()).build();
    }
}
