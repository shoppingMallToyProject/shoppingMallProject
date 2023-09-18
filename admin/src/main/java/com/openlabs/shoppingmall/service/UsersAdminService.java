package com.openlabs.shoppingmall.service;

import com.openlabs.framework.dto.PageDto;
import com.openlabs.framework.util.ObjectConverter;
import com.openlabs.shoppingmall.dto.UsersReqDto;
import com.openlabs.shoppingmall.dto.UsersResDto;
import com.openlabs.shoppingmall.entity.Users;
import com.openlabs.shoppingmall.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Validated
public class UsersAdminService {
    @Autowired
    UserRepository userRepo;

    /**
     * 고객관리
     */
    public UsersResDto updateUser(@Valid UsersReqDto reqDto) {
        return ObjectConverter.toObject(userRepo.save(reqDto.toEntity()), UsersResDto.class);
    }

    /**
     * 고객목록조회
     */
    public Page<UsersResDto> multiQueryUser(@Valid UsersReqDto reqDto, PageDto pageDto) {
        List<UsersResDto> list = new ArrayList<>();
        List<Users> tmp = userRepo.findAll();
        tmp.forEach(user -> list.add(convertDto(user)));
        Long count = userRepo.count();
        Pageable pageable = PageRequest.of(reqDto.getPageNumber(), reqDto.getSize());

        return new PageImpl<>(list, pageable, count);
    }

    /** EntityToDTO */
    private UsersResDto convertDto(Users user) {
        return UsersResDto.builder()
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .userName(user.getUserName())
                .userRating(user.getUserRating())
                .userStatus(user.getUserStatus())
                .build();
    }
}