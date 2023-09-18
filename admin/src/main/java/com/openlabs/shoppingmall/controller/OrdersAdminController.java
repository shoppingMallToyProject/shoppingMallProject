package com.openlabs.shoppingmall.controller;

import com.openlabs.shoppingmall.service.OrdersAdminService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/labshop/v1/admin")
@Api(value = "상품관련 API")
public class OrdersAdminController {
    @Autowired
    OrdersAdminService service;
}
