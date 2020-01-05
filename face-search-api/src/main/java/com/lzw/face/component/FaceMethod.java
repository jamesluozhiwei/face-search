package com.lzw.face.component;

import com.lzw.face.common.ApiResponse;
import com.lzw.face.common.ApiResponseCode;
import com.lzw.face.constant.FaceServiceUrl;
import com.lzw.face.dto.FaceRegisterParam;
import com.lzw.face.dto.FaceSearchParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * face 组件
 * @author jamesluozhiwei
 * @date 2020/01/05 13:52
 */
@Slf4j
@Component
public class FaceMethod {

    @Value("${site.face.host}")
    private String host;
    @Value("${site.face.port}")
    private String port;
    @Value("${site.face.http}")
    private String http;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 人脸注册
     * @param param
     * @return
     */
    public boolean faceRegister(FaceRegisterParam param){
        ApiResponse<Object> response = restTemplate.postForObject(this.http + this.host + this.port + FaceServiceUrl.FACE_REGISTER,param, ApiResponse.class);
        if(null == response){
            return false;
        }
        return response.getCode() == ApiResponseCode.NORMAL.getCode();
    }

    /**
     * 人俩搜索
     * @param param
     * @return
     */
    public List<Long> faceSearch(FaceSearchParam param){
        try {
            ApiResponse<Object> response = restTemplate.postForObject(this.http + this.host + this.port + FaceServiceUrl.FACE_SEARCH,param, ApiResponse.class);
            assert response != null && response.getResult() != null;
            return (ArrayList<Long>)response.getResult();
        }catch (Exception e){
            log.error("face search error : ",e);
            return new ArrayList<>();
        }
    }

}


