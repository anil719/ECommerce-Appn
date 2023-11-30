package com.example.ECommerceProject.Dto.Response;




import lombok.*;
        import lombok.experimental.FieldDefaults;

        import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CartItemsResponseDto {

    String customerName;
    List<ItemResponseDto> list;
}