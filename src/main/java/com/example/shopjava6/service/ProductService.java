package com.example.shopjava6.service;


import com.example.shopjava6.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> findAll();
    Product findById(Integer id);
    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);
	List<Product> findByName(String string);
}
