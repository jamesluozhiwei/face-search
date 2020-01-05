package com.lzw.face.service;

import com.lzw.face.common.ApiResponse;
import com.lzw.face.dto.FaceSearchParam;
import com.lzw.face.dto.PersonRegisterParam;
import com.lzw.face.entity.UserPerson;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
public interface IUserPersonService extends IService<UserPerson> {

    /**
     * 注册人脸
     * @param param
     * @return
     */
    ApiResponse<Object> personRegister(PersonRegisterParam param);

    /**
     * 人脸搜索
     * @param param
     * @return
     */
    ApiResponse<Object> personSearch(FaceSearchParam param);
}
