package com.example.shopjava6.ripository;

import com.example.shopjava6.entity.Product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(String cid);

    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1")
	List<Product> findByName(String name);
}