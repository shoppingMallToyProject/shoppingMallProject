package com.openlabs.shoppingmall.service;

import com.openlabs.shoppingmall.AdminApplication;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.Base64;

@Slf4j
@SpringBootTest(classes = AdminApplication.class)
public class SecurityTest {
    @Test
    void keyGen(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        log.info("key : {}", key);
        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        log.info(secretKey);
    }
}
