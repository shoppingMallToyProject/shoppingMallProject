package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Users, String> {

}
