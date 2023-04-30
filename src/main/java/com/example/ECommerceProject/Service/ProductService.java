package com.example.ECommerceProject.Service;

import com.example.ECommerceProject.Dto.Request.ProductRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteProductResponseDto;
import com.example.ECommerceProject.Dto.Response.ProductResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidSellerException;
import com.example.ECommerceProject.Models.Item;
import com.example.ECommerceProject.Models.Ordered;
import com.example.ECommerceProject.enums.ProductCategory;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);

    List<ProductResponseDto> productsBySellerEmail(String email) throws Exception;

    DeleteProductResponseDto deleteBySellerProductId(int sid, int pid) throws Exception;

    List<ProductResponseDto> getAvailableProducts() throws Exception;

    List<ProductResponseDto> getProductWithQuantityLessThan10() throws Exception;

    List<ProductResponseDto> getCheapestProducts();

    List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category);

    List<ProductResponseDto> getCostlyProducts();

    ProductResponseDto getCheaperProductInTheCategory(String category);

    ProductResponseDto getCostlierProductInTheCategory(String category);

    void decreaseProductQuantity(Item item) throws Exception;
}
