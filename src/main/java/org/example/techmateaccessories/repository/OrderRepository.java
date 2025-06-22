package org.example.techmateaccessories.repository;

import org.example.techmateaccessories.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
