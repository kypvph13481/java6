package com.example.shopjava6.ripository;

import com.example.shopjava6.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder(Long id);

//    @Query("select o from OrderDetails o where o.order.id=?1")
//    public List<OrderDetail> findByOrder(long id);
}