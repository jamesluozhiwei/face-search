package com.lzw.face.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzw.face.common.ApiResponse;
import com.lzw.face.common.ApiResponseCode;
import com.lzw.face.component.EmailMethod;
import com.lzw.face.dto.UserRegisterParam;
import com.lzw.face.entity.User;
import com.lzw.face.mapper.UserMapper;
import com.lzw.face.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private EmailMethod emailMethod;

    @Override
    public ApiResponse<Object> sendUserRegisterEmailCode(String email) {
        Integer count = this.baseMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getEmail,email));
        if (0 < count){
            return ApiResponse.response(ApiResponseCode.EMAIL_HAD_REGISTERED);
        }
        String key = "user_register:" + email;
        String code = (String) redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(code)){
            Random random = new Random();
            StringBuilder randomCode = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                randomCode.append(codeSequence[random.nextInt(36)]);
            }
            code = randomCode.toString();
        }
        log.info("email code:{} {}",email,code);
        redisTemplate.opsForValue().set(key,code,30 * 60 * 1000);
        try {
            this.emailMethod.sendTextEmail(email,"ccccyc用户注册邮箱验证码",code+"；打死都不要告诉别人，三十分钟内有限！");
        } catch (MessagingException e) {
            log.error("send email error:",e);
            return ApiResponse.response(ApiResponseCode.EMAIL_ERROR);
        }
        return ApiResponse.response(ApiResponseCode.NORMAL);
    }

    @Override
    public ApiResponse<Object> userRegister(UserRegisterParam param) {
        Integer count = this.baseMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getEmail,param.getEmail()));
        if (0 < count){
            return ApiResponse.response(ApiResponseCode.EMAIL_HAD_REGISTERED);
        }
        String key = "user_register:" + param.getEmail();
        String code = (String) redisTemplate.opsForValue().get(key);
        if (null == code || !code.equals(param.getCode())){
            return ApiResponse.response(ApiResponseCode.EMAIL_CODE_ERROR);
        }
        redisTemplate.delete(key);
        User user = User.builder()
                .company(param.getCompany())
                .email(param.getEmail())
                .name(param.getName())
                .openKey(UUID.randomUUID().toString().replace("-",""))
                .createTime(LocalDateTime.now())
                .enableStatus(true)
                .build();
        super.baseMapper.insert(user);
        return ApiResponse.response(ApiResponseCode.NORMAL,user.getOpenKey());
    }
}
