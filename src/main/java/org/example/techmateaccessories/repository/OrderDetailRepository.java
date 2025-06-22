package org.example.techmateaccessories.repository;

import org.example.techmateaccessories.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>  {
}
