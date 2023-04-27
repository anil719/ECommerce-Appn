package com.example.ECommerceProject.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)

public class CustomerResponseDto {

    String name;
    String mobile;
    String message;
}
