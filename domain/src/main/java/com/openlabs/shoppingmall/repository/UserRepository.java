package com.openlabs.shoppingmall.repository;

import com.openlabs.shoppingmall.entity.UserRating;
import com.openlabs.shoppingmall.entity.UserStatus;
import com.openlabs.shoppingmall.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    /** 고객목록조회 */
    Slice<Users> findSliceByUserNameAndUserStatusAndUserRating(String userName, UserStatus userStauts, UserRating userRating, Pageable pageable);
    /** 전체조회 */
    Slice<Users> findSliceBy(Pageable pageable);
}
