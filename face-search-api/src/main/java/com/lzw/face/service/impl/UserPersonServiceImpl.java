package com.lzw.face.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzw.face.common.ApiResponse;
import com.lzw.face.common.ApiResponseCode;
import com.lzw.face.component.FaceMethod;
import com.lzw.face.dto.FaceRegisterParam;
import com.lzw.face.dto.FaceSearchParam;
import com.lzw.face.dto.PersonRegisterParam;
import com.lzw.face.entity.User;
import com.lzw.face.entity.UserPerson;
import com.lzw.face.mapper.UserMapper;
import com.lzw.face.mapper.UserPersonMapper;
import com.lzw.face.service.IUserPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    @Resource
    private FaceMethod faceMethod;

    /**
     * 用户mapper
     */
    @Resource
    private UserMapper userMapper;

    @Override
    public ApiResponse<Object> personRegister(PersonRegisterParam param) {
        String openKey = param.getOpenKey().trim();
        //openKey是否存在
        int i = userMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getOpenKey,openKey));
        if (i == 0){
            return ApiResponse.response(ApiResponseCode.OPEN_KEY_NOT_EXISTS);
        }
        UserPerson userPerson = super.baseMapper.selectOne(Wrappers.<UserPerson>lambdaQuery()
            .eq(UserPerson::getOpenKey,param.getOpenKey())
            .eq(UserPerson::getPersonTag,param.getPersonTag())
        );
        if (userPerson == null){
            int personCount = super.baseMapper.selectCount(Wrappers.<UserPerson>lambdaQuery().eq(UserPerson::getOpenKey,openKey));
            userPerson = UserPerson.builder()
                    .openKey(openKey)
                    .personTag(param.getPersonTag())
                    .personIndex(personCount)
                    .build();
            super.baseMapper.insert(userPerson);
        }
        boolean result = this.faceMethod.faceRegister(FaceRegisterParam.builder()
                .imgData(param.getImgData())
                .openKey(openKey)
                .personId(userPerson.getId())
                .build());
        return ApiResponse.response(result ? ApiResponseCode.NORMAL : ApiResponseCode.UN_KNOW_ERR);
    }

    @Override
    public ApiResponse<Object> personSearch(FaceSearchParam param) {
        String openKey = param.getOpenKey().trim();
        //openKey是否存在
        int i = userMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getOpenKey,openKey));
        if (i == 0){
            return ApiResponse.response(ApiResponseCode.OPEN_KEY_NOT_EXISTS);
        }
        List<Integer> personIndexes = this.faceMethod.faceSearch(param);
        if (personIndexes.size() == 0){
            return ApiResponse.response(ApiResponseCode.NORMAL,personIndexes);
        }
        Set<String> persons = super.baseMapper.listPersonTag(openKey,personIndexes);
        return ApiResponse.response(ApiResponseCode.NORMAL,persons);
    }
}
