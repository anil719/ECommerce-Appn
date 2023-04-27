package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Integer> {
}
