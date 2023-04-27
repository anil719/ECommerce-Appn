package com.example.ECommerceProject.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetSellerResponseDto {
    int id;
    String name;
    int age;
    String mobile;
    String email;
}
