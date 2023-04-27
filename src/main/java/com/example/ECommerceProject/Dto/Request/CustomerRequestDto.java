package com.example.ECommerceProject.Dto.Request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CustomerRequestDto {

    String name;
    String mobile ;
    int age;
    String email;
    String address;
}
