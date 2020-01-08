package com.lzw.face.controller;


import com.lzw.face.common.ApiResponse;
import com.lzw.face.dto.FaceSearchParam;
import com.lzw.face.dto.PersonRegisterParam;
import com.lzw.face.service.IUserPersonService;
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
 *  前端控制器
 * </p>
 *
 * @author jamesluozhiwei
 * @since 2019-12-23
 */
@Api(tags = "人脸相关 | API")
@RestController
@RequestMapping("/v1")
public class UserPersonController {

    @Resource
    private IUserPersonService userPersonService;

    /**
     * 人脸注册
     * @param param
     * @return
     */
    @ApiOperation("人脸注册")
    @PostMapping("/faces/register")
    public ApiResponse<Object> faceRegister(@RequestBody @Valid PersonRegisterParam param){
        return this.userPersonService.personRegister(param);
    }

    /**
     * 人脸搜索
     * @param param
     * @return
     */
    @ApiOperation("人脸搜索")
    @PostMapping("/faces/search")
    public ApiResponse<Object> faceSearch(@RequestBody @Valid FaceSearchParam param){
        return this.userPersonService.personSearch(param);
    }
}
