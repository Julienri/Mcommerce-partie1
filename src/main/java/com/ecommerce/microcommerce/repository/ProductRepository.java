package com.ecommerce.microcommerce.repository;

import com.ecommerce.microcommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);
    //List<Product> findByPriceGreaterThan(int limitPrice);

    @Query(value ="SELECT p FROM Product p ORDER BY name")
    List<Product> findAllProductsSorted();
}

