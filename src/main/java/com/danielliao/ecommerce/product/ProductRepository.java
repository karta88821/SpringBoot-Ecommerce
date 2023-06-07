package com.danielliao.ecommerce.product;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products WHERE quantity > 0", nativeQuery = true)
    List<Product> getInStockProductList();

    @Transactional
    @Modifying 
    @Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.productID = :productID")
    public void updateProductByProductID(@Param("productID") Integer productID, @Param("quantity") Integer quantity);
}
