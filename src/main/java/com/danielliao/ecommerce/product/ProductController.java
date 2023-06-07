package com.danielliao.ecommerce.product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:8080/")
public class ProductController {

    @Autowired
    @Qualifier("Default_ProductService")
    private ProductService productService;
    
    // 取得產品列表
    @GetMapping
    public ResponseEntity<?> getProductList(@RequestParam("in_stock") Integer inStock) {
        Map<String, Object> res = new HashMap<>();
        if (inStock == 1) {
            res.put("data", productService.getInStockProductList());
        } else {
            res.put("data", productService.getProductList());
        }
        res.put("message", "Get product list successful.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 取得單一個產品
    @GetMapping("/{productID}")
    public ResponseEntity<?> getProduct(@PathVariable(value = "productID") Integer productID) {
        Map<String, Object> res = new HashMap<>();
        Optional<Product> product = productService.getProduct(productID);

        if (product.isPresent()) {
            res.put("data", product.get());
            res.put("message", "Get product successful.");
        } else {
            res.put("data", "Get product failed.");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 新增產品
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Map<String, Object> res = new HashMap<>();

        Product savedProduct = productService.saveProduct(product);

        res.put("data", savedProduct);
        res.put("message", "Add a product successful.");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
