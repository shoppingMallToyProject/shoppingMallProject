package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.shoppingmall.dto.UserDto;
import com.openlabs.shoppingmall.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"사용자 관련 API LIST"})
@RestController
@RequestMapping("/labshop/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/r-user")
    public ResponseDto<UserDto> findUser(@RequestParam String userId, @RequestParam String password) {
        return ResponseDto.ok(userService.findUser(userId, password));
    }

    @PutMapping("/u-user")
    public ResponseDto<Boolean> updateUser(@RequestBody UserDto userDto) {
        return ResponseDto.ok(userService.updateUser(userDto));
    }


}
