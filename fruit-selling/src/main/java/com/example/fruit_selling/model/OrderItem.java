package com.example.fruit_selling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;

   // @Column(name="oder_id",nullable = false)
    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderProduct order;

    //@Column(name="product_id",nullable = false)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="quantity",nullable = false)
    private Integer quantity;


    @Column(name="price",nullable = false)
    private Long price;

//    @Column(name="total_cost", columnDefinition = "money", nullable = false)
    private  Long totalCost;

    @CreationTimestamp
    @Column(name="created_at",updatable = false, nullable = false)
    private LocalDateTime createAt;

    @Column(name="flag",nullable = false)
    private boolean flag;



    public OrderItem( Product product, Integer quantity) {
       this.product = product;
       this.quantity = quantity;
       this.price = product.getPrice();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalCost=" + totalCost +
                ", createAt=" + createAt +
                ", flag=" + flag +
                '}';
    }
}
