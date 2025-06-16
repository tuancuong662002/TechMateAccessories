package org.example.techmateaccessories.repository;

import org.example.techmateaccessories.domain.Cart;
import org.example.techmateaccessories.domain.Category;
import org.example.techmateaccessories.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository   extends JpaRepository<Cart, Long>  {
       Cart findByUser(User user) ;
}
