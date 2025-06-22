package org.example.techmateaccessories.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private long id ;
       private double totalPrice ;
       private String address;
       private String phone;
       private String name;
       private String status ;
       public String getStatus() {
              return status;
       }

       public void setStatus(String status) {
              this.status = status;
       }

       public String getName() {
              return name;
       }

       public void setName(String name) {
              this.name = name;
       }

       public String getAddress() {
              return address;
       }

       public void setAddress(String address) {
              this.address = address;
       }

       public String getPhone() {
              return phone;
       }

       public void setPhone(String phone) {
              this.phone = phone;
       }

       public void setUser(User user) {
              this.user = user;
       }

       public void setOrderDetails(List<OrderDetail> orderDetails) {
              this.orderDetails = orderDetails;
       }

       public List<OrderDetail> getOrderDetails() {
              return orderDetails;
       }
       @ManyToOne
       @JoinColumn(name = "user_id")
       private User user;
       @OneToMany(mappedBy = "order")
       List<OrderDetail> orderDetails ;
       public User getUser() {
              return user;
       }
       public long getId() {
              return id;
       }
       public void setId(long id) {
              this.id = id;
       }
       public double getTotalPrice() {
              return totalPrice;
       }
       public void setTotalPrice(double totalPrice) {
              this.totalPrice = totalPrice;
       }
}
