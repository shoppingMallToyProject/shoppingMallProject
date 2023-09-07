package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {

}
