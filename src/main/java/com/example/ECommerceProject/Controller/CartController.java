package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.CheckOutCardRequestDto;
import com.example.ECommerceProject.Dto.Request.ItemRequestDto;
import com.example.ECommerceProject.Dto.Response.CartItemsResponseDto;
import com.example.ECommerceProject.Dto.Response.CartResponseDto;
import com.example.ECommerceProject.Dto.Response.DeleteCartResponseDto;
import com.example.ECommerceProject.Dto.Response.OrderResponseDto;
import com.example.ECommerceProject.Models.Item;
import com.example.ECommerceProject.Service.CartService;
import com.example.ECommerceProject.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws  Exception{
        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto =  cartService.saveCart(itemRequestDto.getCustomerId(), savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckOutCardRequestDto checkOutCardRequestDto) throws Exception {
       return cartService.checkOutCart(checkOutCardRequestDto);
    }


    //view all items in cart
    @GetMapping("/itemsInCart")
    public List<CartItemsResponseDto> itemsInCart(){
        return cartService.getItemsInCart();
    }




    //remove from cart
        @DeleteMapping("/delete")

        public DeleteCartResponseDto deleteFromCart(@RequestParam("id")int id) throws Exception {
            return cartService.deleteCart(id);
        }

    }

