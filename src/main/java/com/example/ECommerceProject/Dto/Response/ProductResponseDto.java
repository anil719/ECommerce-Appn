package com.example.ECommerceProject.Dto.Response;

import com.example.ECommerceProject.enums.ProductCategory;
import com.example.ECommerceProject.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProductResponseDto {
    String productName;
    String sellerName;
    int quantity;
    ProductStatus productStatus;
}
