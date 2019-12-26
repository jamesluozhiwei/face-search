package com.lzw.face.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * api响应实体
 * @author jamesluozhiwei
 * @date 2019/12/25
 */
@Data
@Builder
@ToString
@ApiModel("api响应实体")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    @ApiModelProperty("响应编码")
    private int code;

    @ApiModelProperty("响应消息")
    private String msg;

    @ApiModelProperty("相应结果")
    private T result;

    /**
     * 响应
     * @param code
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> response(ApiResponseCode code,T result){
        return new ApiResponse<>(code.getCode(),code.getMsg(),result);
    }

    /**
     * 响应
     * @param code
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> response(ApiResponseCode code){
        return new ApiResponse<>(code.getCode(),code.getMsg(),null);
    }
}
