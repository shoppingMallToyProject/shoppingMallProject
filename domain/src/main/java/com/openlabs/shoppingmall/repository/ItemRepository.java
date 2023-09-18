package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.Items;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
    /** 상품명 다건조회 */
    Slice<Items> findByItemName(String itemName, Pageable pageable);
    /** 상품명 전체조회 */
    Slice<Items> findSliceBy(Pageable pageable);
    /** 상품명 단건조회(상품명 중복체크) */
    Optional<Items> findByItemName(String itemName);
}
