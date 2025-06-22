package org.example.techmateaccessories.repository;

import org.example.techmateaccessories.domain.Cart;
import org.example.techmateaccessories.domain.CartDetail;
import org.example.techmateaccessories.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CartDetailRepository   extends JpaRepository<CartDetail, Long> {
       boolean existsByCartAndProduct(Cart cart , Product product) ;
       @Query("SELECT cd FROM CartDetail cd WHERE cd.cart = :cart AND cd.product = :product")
       CartDetail findByCartAndProduct(@org.springframework.data.repository.query.Param("cart") Cart cart,
                                       @org.springframework.data.repository.query.Param("product") Product product);
       int countByCart(Cart cart);


}
