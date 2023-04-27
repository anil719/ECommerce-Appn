package com.example.ECommerceProject.Dto.Request;

import com.example.ECommerceProject.Models.Seller;
import com.example.ECommerceProject.enums.ProductCategory;
import com.example.ECommerceProject.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProductRequestDto {

    String productName;
    int price;
    int quantity;
    int sellerId;
    ProductCategory productCategory;

}
