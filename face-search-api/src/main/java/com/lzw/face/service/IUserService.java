package com.lzw.face.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzw.face.common.ApiResponse;
import com.lzw.face.entity.User;

/**
 * <p>
 *  用户相关 服务类
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
public interface IUserService extends IService<User> {

    /**
     * 发送邮箱注册验证码
     * @param email
     * @return
     */
    ApiResponse<Object> sendUserRegisterEmailCode(String email);

}
