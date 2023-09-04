package com.openlabs.shoppingmall.service;

import com.openlabs.framework.exception.ShopException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SampleService {

    public String sampleService(String param) {
        if (param.equals("exception")) {
            throw new ShopException("throw customException");
        } else {
            return "true";
        }
    }
}
