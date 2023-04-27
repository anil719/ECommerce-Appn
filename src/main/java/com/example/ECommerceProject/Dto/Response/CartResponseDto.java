package com.example.ECommerceProject.Dto.Response;

import com.example.ECommerceProject.Models.Customer;
import com.example.ECommerceProject.Models.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    int cartTotal;

    int noOfItems;

    String customerName;

    List<ItemResponseDto> items;

}
