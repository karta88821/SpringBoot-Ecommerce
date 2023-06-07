package com.danielliao.ecommerce.order;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielliao.ecommerce.SelectedProductForm;
import com.danielliao.ecommerce.Selection;
import com.danielliao.ecommerce.product.Product;
import com.danielliao.ecommerce.product.ProductRepository;

@Service("Default_OrderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Order saveOrder(SelectedProductForm form) throws OrderException {
        Double totalAmount = 0.0;
        String orderID = generateOrderID();

        Order order = new Order();
        order.setOrderID(orderID);

        for (Selection selection: form.getSelections()) {
            Optional<Product> product = productRepository.findById(selection.getProductID());
            if (product.isPresent()) {

                // 檢查所選擇數量是否合法
                if (selection.getSelectedQuantity() > product.get().getQuantity()) {
                    throw new OrderException("The selected quantity of product" + product.get().getProductName() + " has not enough quantity.");
                }

                // 減去該產品的數量
                productRepository.updateProductByProductID(product.get().getProductID(), selection.getSelectedQuantity());
                
                // 產品單價 * 所選擇數量
                Double itemPrice = product.get().getPrice() * selection.getSelectedQuantity();

                // 儲存訂單詳細
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(product.get());
                orderDetail.setQuantity(selection.getSelectedQuantity());
                orderDetail.setStandPrice(product.get().getPrice());
                orderDetail.setItemPrice(itemPrice);
                orderDetailRepository.save(orderDetail);

                totalAmount += itemPrice;
            } else {
                throw new OrderException("The selected product does not exist.");
            }
        }

        order.setMemberID(form.getMemberID());  
        order.setPrice(totalAmount);
        order.setPayStatus((short) 0);
        Order savedOrder = orderRepository.save(order);
        
        return savedOrder;
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrder(String orderID) {
        orderRepository.updatePayStatus((short) 1, orderID);
    }

    private String generateOrderID() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ms");

        Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(new Date());
        
        sb.append(dateString);

        return sb.toString();
    }
}
