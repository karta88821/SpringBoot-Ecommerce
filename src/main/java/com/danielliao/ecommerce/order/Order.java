package com.danielliao.ecommerce.order;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "orders")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {
    
    @Id
    @Column(name = "order_id")
    private String orderID;

    @Column(name = "member_id")
    private Integer memberID;

    @Column(name = "price")
    private Double price;

    @Column(name = "pay_status")
    private Short payStatus;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="order")
    private Set<OrderDetail> orderDetails;
}