package com.openlabs.shoppingmall.service;

import com.openlabs.shoppingmall.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class OrdersAdminService {
    @Autowired
    OrderRepository orderRepo;
}