package com.example.ECommerceProject.Models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;

    @CreationTimestamp
    Date orderDate;
    String orderNum;
    int totalValue;
    String cardUsed;

    @ManyToOne
    @JoinColumn
    Customer customer;

//    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
//    Card card;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();

}


