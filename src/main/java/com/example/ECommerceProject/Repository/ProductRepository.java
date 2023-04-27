package com.example.ECommerceProject.Repository;

import com.example.ECommerceProject.Models.Product;
import com.example.ECommerceProject.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductCategory(ProductCategory productCategory);
    @Query(value = "select * from Product p where p.price > :price and p.product_category=:category", nativeQuery = true)
     List<Product> getAllProductsByPriceAndCategory(int price, String category);



    @Query(value = "select * from product order by price desc limit 5", nativeQuery = true)
    List<Product> getTopCostlyProducts();



    @Query(value = "select * from product order by price limit 5", nativeQuery = true)

    List<Product> get5CheapestProducts();



    @Query(value = "SELECT * FROM product WHERE product_category = :category ORDER BY price ASC LIMIT 1", nativeQuery = true)

    Product getCheaperProductInTheCategory(String category);


    @Query(value = "SELECT * FROM product WHERE product_category = :category ORDER BY price DESC LIMIT 1", nativeQuery = true)

    Product getCostlierProductInTheCategory(String category);
}
