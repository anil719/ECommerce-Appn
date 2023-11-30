package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Ordered, Integer> {


    @Query(value = "Select * from ordered ORDER By order_date desc limit 5", nativeQuery = true)
    List<Ordered> getLast5orders();
}
