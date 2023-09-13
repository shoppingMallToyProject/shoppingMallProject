package com.openlabs.shoppingmall.service;

import com.openlabs.framework.exception.ShopException;
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

    public UserDto findUser(String userId, String password) {
        Users users = getUser(userId);
        UserDto userDto = null;
        if (users != null) {
            if (!users.getUserPw().equals(password)) {
                throw new ShopException("비밀번호가 다릅니다");
            }
            userDto = ObjectConverter.toObject(users, UserDto.class);
        }
        return userDto;
    }
    public boolean updateUser(UserDto userDto) {
        Users users = ObjectConverter.toObject(userDto, Users.class);
        userRepository.save(users);
        return true;
    }

    public Users getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
