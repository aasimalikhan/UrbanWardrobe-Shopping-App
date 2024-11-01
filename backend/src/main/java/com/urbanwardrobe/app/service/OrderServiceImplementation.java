package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.OrderException;
import com.urbanwardrobe.app.model.*;
import com.urbanwardrobe.app.repository.AddressRespository;
import com.urbanwardrobe.app.repository.OrderItemRepository;
import com.urbanwardrobe.app.repository.OrderRepository;
import com.urbanwardrobe.app.repository.UserRepository;
import com.urbanwardrobe.app.user.domain.OrderStatus;
import com.urbanwardrobe.app.user.domain.PaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private AddressRespository addressRepository;
    private UserRepository userRepository;
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;

    public OrderServiceImplementation(OrderRepository orderRepository,CartService cartService,
                                      AddressRespository addressRepository,UserRepository userRepository,
                                      OrderItemService orderItemService,OrderItemRepository orderItemRepository) {
        this.orderRepository=orderRepository;
        this.cartService=cartService;
        this.addressRepository=addressRepository;
        this.userRepository=userRepository;
        this.orderItemService=orderItemService;
        this.orderItemRepository=orderItemRepository;
    }

    @Override
    public Order createOrder(User user, Address shippAddress) {
        // Set user and save shipping address
        shippAddress.setUser(user);
        shippAddress.setId(null);
        Address address = addressRepository.save(shippAddress);
        user.getAddresses().add(address);
        userRepository.save(user);

        // Retrieve user's cart
        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        // Create Order and OrderItems
        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscounte(cart.getDiscounte());
        createdOrder.setTotalItem(cart.getTotalItem());
        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus(OrderStatus.PENDING);
        createdOrder.setCreatedAt(LocalDateTime.now());

        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());
            orderItem.setOrder(createdOrder);  // Link OrderItem to Order
            orderItems.add(orderItem);
        }

        createdOrder.setOrderItems(orderItems);  // Link Order to OrderItems

        // Save the Order (OrderItems will be saved automatically if cascade is set)
        return orderRepository.save(createdOrder);
    }


    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(OrderStatus.PLACED);
        order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(OrderStatus.CONFIRMED);


        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setDeliveryDate(LocalDateTime.now().plusDays(5));
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setDeliveryDate(null);
        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt=orderRepository.findById(orderId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new OrderException("order not exist with id "+orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> orders=orderRepository.getUsersOrders(userId);
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);

        orderRepository.deleteById(orderId);

    }

}
