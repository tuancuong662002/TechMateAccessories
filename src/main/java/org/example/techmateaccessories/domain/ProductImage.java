package org.example.techmateaccessories.domain;

import jakarta.persistence.*;

@Entity
public class ProductImage {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       private String imageUrl;
       private String description;
       private Integer position;

       @ManyToOne
       @JoinColumn(name = "product_id")
       private Product product;

       public Long getId() {
              return id;
       }

       public void setId(Long id) {
              this.id = id;
       }

       public String getImageUrl() {
              return imageUrl;
       }

       public void setImageUrl(String imageUrl) {
              this.imageUrl = imageUrl;
       }

       public String getDescription() {
              return description;
       }

       public void setDescription(String description) {
              this.description = description;
       }

       public Integer getPosition() {
              return position;
       }

       public void setPosition(Integer position) {
              this.position = position;
       }

       public Product getProduct() {
              return product;
       }

       public void setProduct(Product product) {
              this.product = product;
       }
// getter, setter
}