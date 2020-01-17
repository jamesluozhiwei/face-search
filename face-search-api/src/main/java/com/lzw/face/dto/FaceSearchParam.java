package com.lzw.face.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 人脸搜索请求参数
 * @author jamesluozhiwei
 * @date 2020/01/05 22:31
 */
@Data
@Builder
@ToString
@ApiModel("人脸搜索请求参数")
@Accessors(chain = true)
@EqualsAndHashCode
public class FaceSearchParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty("用户openKey")
    private String openKey;

    @NotBlank
    @ApiModelProperty("人脸数据 |　支持 base64数组 或 图片url")
    private String img;

}
