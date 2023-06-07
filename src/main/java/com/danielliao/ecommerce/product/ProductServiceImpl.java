package com.danielliao.ecommerce.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Default_ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getInStockProductList() {
        return productRepository.getInStockProductList();
    }

    @Override
    public Optional<Product> getProduct(Integer productID) {
        return productRepository.findById(productID);
    }
    
}
