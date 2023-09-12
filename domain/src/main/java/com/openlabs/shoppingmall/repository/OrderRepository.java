package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Orders;
import com.openlabs.shoppingmall.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUsers(Users users);
}
