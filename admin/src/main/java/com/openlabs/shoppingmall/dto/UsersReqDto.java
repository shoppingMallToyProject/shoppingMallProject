package com.openlabs.shoppingmall.dto;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.shoppingmall.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersReqDto extends PageDto {
    /** 고객ID */
    @NotBlank(message = "회원ID는 이메일 형식의 필수 입력 사항입니다.")
    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String userId;
    /** 고객명 */
//    @NotBlank(message = "회원 이름은 필수 입력 항목입니다.")
    private String userName;
    /** 고객비밀번호 */
//    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*\\\\W).{8,20}$", message = "비밀번호는 영문과 특수문자를 포함하며 8자 이상이어야 합니다.")
    private String userPw;
    /** 고객상태 */
    private UserStatus userStatus;
    /** 고객등급 */
    private UserRating userRating;

    /** 주문 */
//    private List<Orders> orders = new ArrayList<>();
    /** 고객쿠폰 */
    private List<UserCoupons> userCoupons = new ArrayList<>();
    /** 주소 */
//    private List<Address> addresses = new ArrayList<>();

    private Long couponId;

    public Users toEntity(){
        return Users.builder()
                .userId(this.userId)
                .userPw(this.userPw)
                .userName(this.userName)
                .userRating(this.userRating)
                .userStatus(this.userStatus)
                .userCoupons(this.userCoupons)
                .build();
    }
}