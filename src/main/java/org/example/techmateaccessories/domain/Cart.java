package org.example.techmateaccessories.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.List;

@Entity
@Table(name ="carts")
public class Cart {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
      private Long id ;
      @Min(value  =0  )
      private int sum ;
      @OneToOne
      @JoinColumn(name = "user_id")
      private User user ;
      @OneToMany(mappedBy = "cart")
      private List<CartDetail> cartDetail ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartDetail> getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(List<CartDetail> cartDetail) {
        this.cartDetail = cartDetail;
    }
}
