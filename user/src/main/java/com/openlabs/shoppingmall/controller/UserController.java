package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.shoppingmall.dto.UserDto;
import com.openlabs.shoppingmall.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = {"사용자 관련 API LIST"})
@RestController
@RequestMapping("/labshop/v1/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("user/r-user")
    public ResponseDto<UserDto> findUser(@RequestParam String userId) {
        return ResponseDto.ok(userService.findUser(userId));
    }
}
