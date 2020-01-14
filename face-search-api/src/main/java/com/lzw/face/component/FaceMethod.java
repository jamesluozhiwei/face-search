package com.lzw.face.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
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
    @Resource
    private Base64Method base64Method;

    /**
     * 人脸注册
     * @param param
     * @return
     */
    public boolean faceRegister(FaceRegisterParam param){
        List<String> images = param.getImgData();
        for (int i = 0,len = images.size();i < len;i++){
            if (this.base64Method.matchUrl(images.get(i))){
                //将网络图片转base64
                images.set(i,this.base64Method.encodeImageToBase64(images.get(i)));
            }
        }
        ApiResponse<Object> response = this.postBody(FaceServiceUrl.FACE_REGISTER,param);
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
    public List<Integer> faceSearch(FaceSearchParam param){
        if (this.base64Method.matchUrl(param.getImg())){
            //将网络图片转base64
            param.setImg(this.base64Method.encodeImageToBase64(param.getImg()));
        }
        try {
            ApiResponse<Object> response = this.postBody(FaceServiceUrl.FACE_SEARCH,param);
            assert response != null && response.getResult() != null;
            JSONArray jsonArray = (JSONArray) response.getResult();
            return jsonArray.toJavaList(Integer.class);
        }catch (Exception e){
            log.error("face search error : ",e);
            return new ArrayList<>();
        }
    }

    /**
     * 发送post请求
     * @param mapping
     * @param param
     * @return
     */
    private ApiResponse<Object> postBody(String mapping,Object param){
        String body = restTemplate.postForObject(this.http + this.host + ":" + this.port + mapping,param, String.class);
        log.info("post response : {}",body);
        try {
            ApiResponse<Object> response = JSONObject.parseObject(body,ApiResponse.class);
            return response;
        }catch (JSONException e){
            log.error("json parse object error:",e);
        }
        return null;
    }

}


