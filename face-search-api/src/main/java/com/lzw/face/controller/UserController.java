package com.lzw.face.controller;


import com.lzw.face.common.ApiResponse;
import com.lzw.face.dto.UserRegisterParam;
import com.lzw.face.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    /**
     * 注册用户
     * @param userRegisterParam
     * @return
     */
    @PostMapping("/users")
    @ApiOperation("注册用户")
    public ApiResponse userRegister(@RequestBody @Valid UserRegisterParam userRegisterParam){
        return this.userService.userRegister(userRegisterParam);
    }

    /**
     * 发送注册邮箱验证码
     * @param email
     * @return
     */
    @PostMapping("/users/registers/codes")
    @ApiOperation("发送注册邮箱验证码")
    public ApiResponse sendCode(@RequestBody String email){
        return this.userService.sendUserRegisterEmailCode(email);
    }

    /**
     * 忘记OpenKey
     * @param email
     * @return
     */
    @PostMapping("/users/open-key/forget")
    @ApiOperation("忘记OpenKey")
    public ApiResponse forgetOpenKey(@RequestBody String email){
        return this.userService.forgetOpenKey(email);
    }
}
