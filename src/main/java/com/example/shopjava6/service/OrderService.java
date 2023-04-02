package com.example.shopjava6.service;


import com.example.shopjava6.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order create(JsonNode orderData);


    Order findById(Long id);

    List<Order> findByUser(String username);
}
