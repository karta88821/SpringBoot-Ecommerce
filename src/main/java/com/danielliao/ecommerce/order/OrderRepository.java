package com.danielliao.ecommerce.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Order o SET o.payStatus = :payStatus WHERE o.orderID = :orderID")
    void updatePayStatus(@Param("payStatus") Short payStatus, @Param("orderID") String orderID);
}
