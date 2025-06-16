package org.example.techmateaccessories.repository;

import org.example.techmateaccessories.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN OrderDetail od ON p.id = od.product.id " +
            "GROUP BY p.id ORDER BY SUM(od.quantity) DESC")
    List<Product> findTopSellingProducts();
}
