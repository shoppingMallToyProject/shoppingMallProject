package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Orders;
import com.openlabs.shoppingmall.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Slice<Orders> findByUsers(Users users, Pageable pageable);
}
