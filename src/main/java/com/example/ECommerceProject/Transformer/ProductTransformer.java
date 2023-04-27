package com.example.ECommerceProject.Transformer;

import com.example.ECommerceProject.Dto.Request.ProductRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteProductResponseDto;
import com.example.ECommerceProject.Dto.Response.ProductResponseDto;
import com.example.ECommerceProject.Models.Product;
import com.example.ECommerceProject.enums.ProductStatus;

public class ProductTransformer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder().name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus()).build();
    }

    public static DeleteProductResponseDto productToProductDto(Product product){
        return DeleteProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .status("Product Deleted Successfully").build();
    }
}
