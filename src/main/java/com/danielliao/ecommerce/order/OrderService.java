package com.danielliao.ecommerce.order;

import java.util.List;

import com.danielliao.ecommerce.SelectedProductForm;

public interface OrderService {
    // Save operation
    Order saveOrder(SelectedProductForm form) throws OrderException;
  
    // Read operation
    List<Order> getOrderList();

    // Update operation
    void updateOrder(String orderID);
}
