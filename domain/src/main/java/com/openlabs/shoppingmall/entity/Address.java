package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "ADDRESSES")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    /** 주소ID */
    @Id @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    /** 도시명 */
    @Column(name = "CITY")
    private String city;
    /** 거리명 */
    @Column(name = "STREET")
    private String street;
    /** 우편번호 */
    @Column(name = "ZIPCODE")
    private String zipcode;

    /** 고객 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;
}
