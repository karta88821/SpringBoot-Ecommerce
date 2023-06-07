package com.danielliao.ecommerce.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.danielliao.ecommerce.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "order_details")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_sn")
    private Integer orderItemSN;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product; // 外鍵

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order; // 外鍵

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "stand_price")
    private Double standPrice;

    @Column(name = "item_price")
    private Double itemPrice;
}
