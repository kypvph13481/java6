package com.example.shopjava6.ripository;

import com.example.shopjava6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o from Order o WHERE o.account.username=?1")
    List<Order> findByUser(String username);
}