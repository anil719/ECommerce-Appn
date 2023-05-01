package com.example.ECommerceProject.Dto.Response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDTO2 {
    String name;
    String mobile ;
    int age;
    String email;
    String address;
}
