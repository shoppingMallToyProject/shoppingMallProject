package com.openlabs.shoppingmall.service;

import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.UserDto;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto findUser(String userId) {
        Optional<Users> optional = userRepository.findById(userId);
        Users users = optional.orElse(null);
        UserDto userDto = null;
        if (users != null) {
            userDto = ObjectConverter.toObject(users, UserDto.class);
        }
        return userDto;
    }
}
