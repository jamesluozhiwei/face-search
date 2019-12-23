package com.lzw.face.service.impl;

import com.lzw.face.entity.UserPerson;
import com.lzw.face.mapper.UserPersonMapper;
import com.lzw.face.service.IUserPersonService;
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
public class UserPersonServiceImpl extends ServiceImpl<UserPersonMapper, UserPerson> implements IUserPersonService {

}
