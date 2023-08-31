package com.openlabs.shoppingmall.controller;

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
    public String test(@RequestParam String param) {
        return service.sampleService(param);
    }
}
