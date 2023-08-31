package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
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
    @Column(name = "USER_NAME")
    /** 고객명 */
    private String userName;
    @Column(name = "USER_PW")
    /** 고객비밀번호 */
    private String userPw;
    @Column(name = "USER_STATUS")
    /** 고객상태 */
    private String userStatus;
    /** 고객등급 */
    @Column(name = "USER_RATING")
    private String userRating;

    /** 주소 */
    @Embedded
    private Address address;
//    /** 쿠폰DTO */
//    @Embedded
//    private Coupons coupons;

    /** 주문 */
    @OneToMany(mappedBy = "users")
    private List<Orders> orders;
}
