package com.example.ECommerceProject.Dto.Response;

import com.example.ECommerceProject.enums.ProductCategory;
import com.example.ECommerceProject.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteProductResponseDto {
    String name;
    int price;
    ProductCategory productCategory;
    String status;
}
