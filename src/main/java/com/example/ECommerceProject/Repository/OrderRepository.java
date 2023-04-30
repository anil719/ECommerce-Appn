package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered, Integer> {
}
