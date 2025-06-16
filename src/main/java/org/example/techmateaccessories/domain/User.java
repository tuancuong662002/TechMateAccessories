package org.example.techmateaccessories.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.techmateaccessories.validation.PasswordMatching;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long ID ;
     @NotBlank(message = "Name must not be empty")
     @NotNull
     @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters long")
     private String fullName;
     @NotNull
     @Email
     private String email;
     @NotNull
     @Size(min = 8, message = "Password must be at least 8 characters")
     private String password;
     private String phone ;
     private String address ;
     private String avatar ;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role ;
    @OneToMany(mappedBy = "user")
    private List<Order> orders ;
    @OneToOne(mappedBy = "user")
    private Cart cart  ;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String  getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String  avatar) {
        this.avatar = avatar;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Role getRole() {
        return role;
    }

    public List<Order> getOrders() {
        return orders;
    }


     public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
