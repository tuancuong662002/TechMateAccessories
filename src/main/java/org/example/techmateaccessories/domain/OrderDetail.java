package org.example.techmateaccessories.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id ;
     private long quantity ;
     private double price ;
     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product ;
     @ManyToOne
     @JoinColumn(name = "order_id")
     private Order order ;
}
