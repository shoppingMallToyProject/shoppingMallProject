package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResDto {
    /** 고객ID */
    private String userId;
    /** 고객명 */
    private String userName;
    /** 고객비밀번호 */
    private String userPw;
    /** 고객상태 */
    private UserStatus userStatus;
    /** 고객등급 */
    private UserRating userRating;

    private List<UserCoupons> userCoupons;

    private List<Address> addresses;

    public Users toEntity(){
        return Users.builder()
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userRating(this.userRating)
                .userStatus(this.userStatus)
                .build();
    }
}