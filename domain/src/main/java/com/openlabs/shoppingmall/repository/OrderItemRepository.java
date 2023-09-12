package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.OrderItem;
import com.openlabs.shoppingmall.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrders(Orders orders);
}
