package com.danielliao.ecommerce.product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    // Save operation
    Product saveProduct(Product product);
  
    // Read operation
    List<Product> getProductList();
    List<Product> getInStockProductList();
    Optional<Product> getProduct(Integer productID);
}
