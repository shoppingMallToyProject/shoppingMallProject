package com.openlabs.shoppingmall.controller;

import com.openlabs.shoppingmall.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleService service;

    @GetMapping("/test")
    public String test(@RequestParam String param) {
        return service.sampleService(param);
    }
}
