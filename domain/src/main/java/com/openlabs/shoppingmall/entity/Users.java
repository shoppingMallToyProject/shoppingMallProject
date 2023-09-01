package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "USERS")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {
    /** 고객ID */
    @Id @Column(name = "USER_ID")
    private String userId;
    /** 고객명 */
    @Column(name = "USER_NAME")
    private String userName;
    /** 고객비밀번호 */
    @Column(name = "USER_PW")
    private String userPw;
    /** 고객상태 */
    @Column(name = "USER_STATUS")
    private String userStatus;
    /** 고객등급 */
    @Column(name = "USER_RATING")
    private String userRating;

    /** 주문 */
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
    /** 고객쿠폰 */
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<UserCoupons> userCoupon = new ArrayList<>();
    /** 주소 */
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();
}
