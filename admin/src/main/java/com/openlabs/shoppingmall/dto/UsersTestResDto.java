package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.Address;
import com.openlabs.shoppingmall.entity.UserRating;
import com.openlabs.shoppingmall.entity.UserStatus;
import com.openlabs.shoppingmall.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersTestResDto {
    /** 고객ID */
//    @NotBlank(message = "회원ID는 이메일 형식의 필수 입력 사항입니다.")
//    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String userId;
    /** 고객명 */
//    @NotBlank(message = "회원 이름은 필수 입력 항목입니다.")
    private String userName;
    /** 고객비밀번호 */
//    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*\\\\W).{8,20}$", message = "비밀번호는 영문과 특수문자를 포함하며 8자 이상이어야 합니다.")
    private String userPw;
    /** 고객상태 */
    private UserStatus userStatus;
    /** 고객등급 */
    private UserRating userRating;

//    private List<Orders> orders = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
//    private List<UserCoupons> userCoupons = new ArrayList<>();

    public Users saveEntity(){
        return Users.builder()
                .userId(this.userId)
                .userName(this.userName)
                .userPw(this.userPw)
                .userStatus(this.userStatus)
                .userRating(this.userRating)
                .addresses(this.addresses)
                .build();
    }
}
