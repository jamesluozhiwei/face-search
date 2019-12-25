package com.lzw.face.controller;


import com.lzw.face.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  用户相关 api
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/v1")
@Api(tags = "用户相关 | API")
public class UserController {

    @Resource
    private IUserService userService;



}
