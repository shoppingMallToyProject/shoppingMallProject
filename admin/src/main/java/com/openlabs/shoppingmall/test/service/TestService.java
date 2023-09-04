package com.openlabs.shoppingmall.test.service;

import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository repo;

    public List<Users> searchUsers() {
        List<Users> result = repo.findAll();

        return result;
    }
}
