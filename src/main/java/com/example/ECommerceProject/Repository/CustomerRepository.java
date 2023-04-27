package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByMobile(String mobile);

    Customer findByEmail(String email);



}
