package com.openlabs.shoppingmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "USERS")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {
    /** 고객ID */
    @Id
    @Column(name = "USER_ID")
    private String userId;
    /** 고객명 */
    @Column(name = "USER_NAME")
    private String userName;
    /** 고객비밀번호 */
    @Column(name = "USER_PW")
    private String userPw;
    /** 고객상태 */
    @Column(name = "USER_STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    /** 고객등급 */
    @Column(name = "USER_RATING")
    @Enumerated(EnumType.STRING)
    private UserRating userRating;

    /** 주문 */
//    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();
    /** 고객쿠폰 */
//    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserCoupons> userCoupons = new ArrayList<>();
    /** 주소 */
//    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    // 연관관계 메서드
    public void addOrder(Orders newOrder) {
        this.orders.add(newOrder);
    }

}
