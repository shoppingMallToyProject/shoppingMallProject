package com.openlabs.shoppingmall.service;

import com.openlabs.shoppingmall.dto.CartDto;
import com.openlabs.shoppingmall.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    //TODO 장바구니 구현 필요
    public boolean createCart(CartDto cartDto) {
        return true;
    }

    public Slice<CartDto> getCart(String userId) {
        return null;
    }
}
