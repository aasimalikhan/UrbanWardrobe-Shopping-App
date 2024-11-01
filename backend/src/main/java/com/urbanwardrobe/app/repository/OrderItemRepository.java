package com.urbanwardrobe.app.repository;

import com.urbanwardrobe.app.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}