package com.example.ECommerceProject.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSellerResponseDto {
    int id;
    String name;
    int age;
    String mobile;
    String email;
    String status;
}
