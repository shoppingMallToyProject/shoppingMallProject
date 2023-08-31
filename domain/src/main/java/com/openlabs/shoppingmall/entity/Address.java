package com.openlabs.shoppingmall.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Table(name = "ADDRESSES")
@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    /** 도시명 */
    private String city;
    /** 거리명 */
    private String street;
    /** 우편번호 */
    private String zipcode;
}
