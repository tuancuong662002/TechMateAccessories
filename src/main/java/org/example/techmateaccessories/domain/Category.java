package org.example.techmateaccessories.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category{
     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     long id  ;
     String name ;
     @OneToMany(mappedBy = "category")
     private List<Product> products ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
