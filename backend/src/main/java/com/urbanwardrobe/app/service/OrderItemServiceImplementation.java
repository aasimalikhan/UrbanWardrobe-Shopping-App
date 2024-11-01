package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.model.OrderItem;
import com.urbanwardrobe.app.repository.OrderItemRepository;

public class OrderItemServiceImplementation implements OrderItemService{
    private OrderItemRepository orderItemRepository;
    public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
        this.orderItemRepository=orderItemRepository;
    }
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {

        return orderItemRepository.save(orderItem);
    }
}
