package com.openlabs.shoppingmall.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"댓글 관련 API LIST"})
@RestController
@RequestMapping("/labshop/v1/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
}
