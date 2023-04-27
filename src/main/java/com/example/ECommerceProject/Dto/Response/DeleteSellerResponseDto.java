package com.example.ECommerceProject.Dto.Response;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeleteSellerResponseDto {
    String name;
    String mobile;
    String email;
    String status;
}
