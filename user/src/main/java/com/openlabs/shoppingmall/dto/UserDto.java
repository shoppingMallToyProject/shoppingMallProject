package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.BaseEntity;
import com.openlabs.shoppingmall.entity.UserRating;
import com.openlabs.shoppingmall.entity.UserStatus;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto extends BaseDto {
    private String userId;
    private String userName;
    private String userPW;
    private UserStatus userStatus;
    private UserRating userRating;
}
