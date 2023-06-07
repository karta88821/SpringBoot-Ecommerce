package com.danielliao.ecommerce.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    //@Query(value = "SELECT * FROM product WHERE quantity > 0", nativeQuery = true)
    //List<Product> getInStockProductList();
}