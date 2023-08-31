package com.openlabs.shoppingmall.controller;

import com.openlabs.framework.dto.ResponseDto;
import com.openlabs.framework.exception.ShopException;
import com.openlabs.shoppingmall.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"sample test"})
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService service;

    @GetMapping("/test")
    @ApiOperation(value = "test api")
    public ResponseDto<String> test(@RequestParam String param) {
        try {
            String result = service.sampleService(param);
            return ResponseDto.ok(result);
        } catch (ShopException e) {
            return ResponseDto.error(401, "failure test", "FAIL", null);
        }
    }
}
