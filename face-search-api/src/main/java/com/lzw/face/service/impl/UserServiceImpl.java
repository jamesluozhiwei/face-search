package com.lzw.face.service.impl;

import com.lzw.face.entity.User;
import com.lzw.face.mapper.UserMapper;
import com.lzw.face.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
