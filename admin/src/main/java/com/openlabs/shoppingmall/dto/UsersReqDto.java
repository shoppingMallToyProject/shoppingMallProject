package com.openlabs.shoppingmall.dto;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.shoppingmall.entity.UserRating;
import com.openlabs.shoppingmall.entity.UserStatus;
import com.openlabs.shoppingmall.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersReqDto extends PageDto {
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