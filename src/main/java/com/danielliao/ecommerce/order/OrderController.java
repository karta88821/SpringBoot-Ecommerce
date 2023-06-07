package com.danielliao.ecommerce.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielliao.ecommerce.SelectedProductForm;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:8080/")
public class OrderController {

    @Autowired
    @Qualifier("Default_OrderService")
    private OrderService orderService;
    
    // 新增訂單
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody SelectedProductForm form) {
        Map<String, Object> res = new HashMap<>();
        
        try {
            Order savedOrder = orderService.saveOrder(form);
            res.put("data", savedOrder);
            res.put("message", "Create order successful.");
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (OrderException e) {
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    // 取得訂單列表
    @GetMapping
    public ResponseEntity<?> getOrderList() {
        Map<String, Object> res = new HashMap<>();
        List<Order> orderList = orderService.getOrderList();
        res.put("data", orderList);
        res.put("message", "Get order list successful.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 更新訂單購買狀態
    @PatchMapping("/{orderID}")
    public ResponseEntity<?> updateOrder(@PathVariable(value = "orderID") String orderID) {
        Map<String, Object> res = new HashMap<>();
        orderService.updateOrder(orderID);
        res.put("message", "Update pay status of the order successful.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
