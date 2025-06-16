package org.example.techmateaccessories.domain;

import jakarta.persistence.*;
import org.example.techmateaccessories.validation.PasswordMatching;

import java.util.List;

@Entity
@Table(name ="cartDetails")
public class CartDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id ;
    private int quantity ;
    private double price ;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart ;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
