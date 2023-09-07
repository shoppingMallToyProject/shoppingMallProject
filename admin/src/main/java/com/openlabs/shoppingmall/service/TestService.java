package com.openlabs.shoppingmall.service;

import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.OrderItemTestResDto;
import com.openlabs.shoppingmall.dto.UserTestResDto;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.OrderItemRepository;
import com.openlabs.shoppingmall.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class TestService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    OrderItemRepository orderItemRepo;

    public List<Users> searchUsers() {
        List<Users> result = userRepo.findAll();

        return result;
    }

    public UserTestResDto createUsers(UserTestResDto user) {
        return ObjectConverter.toObject(userRepo.save(
                        ObjectConverter.toObject(user, Users.class)
                ), UserTestResDto.class);
    }

    public UserTestResDto searchOneUsers(String userId) {
        UserTestResDto result = ObjectConverter.toObject(userRepo.findById(userId), UserTestResDto.class);
//        log.info("result : {}", result.toString());
        return result;
    }

    public OrderItemTestResDto searchOneOrderItem(Long orderItemId) {
        OrderItemTestResDto result = ObjectConverter.toObject(orderItemRepo.findById(orderItemId), OrderItemTestResDto.class);

        return result;
    }
}
