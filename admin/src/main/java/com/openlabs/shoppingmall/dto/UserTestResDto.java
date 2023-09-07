package com.openlabs.shoppingmall.dto;

import com.openlabs.shoppingmall.entity.*;
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
public class UserTestResDto {
    private String userId;
    private String userName;
    private String userPw;
    private UserStatus userStatus;
    private UserRating userRating;

    private List<Orders> orders = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<UserCoupons> userCoupons = new ArrayList<>();
}
