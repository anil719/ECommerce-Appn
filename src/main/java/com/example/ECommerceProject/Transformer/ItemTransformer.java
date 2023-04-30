package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.ItemRequestDto;
import com.example.ECommerceProject.Dto.Response.ItemResponseDto;
import com.example.ECommerceProject.Models.Item;

public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto) {
       return Item.builder()
               .requiredQuantity(itemRequestDto.getRequiredQuantity()).build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice()).build();
    }
}
