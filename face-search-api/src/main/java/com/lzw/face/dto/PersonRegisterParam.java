package com.lzw.face.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 人脸注册所需参数
 * @author jamesluozhiwei
 * @date 2020/01/05
 */
@Data
@Builder
@ToString
@ApiModel("人脸注册所需参数")
@Accessors(chain = true)
@EqualsAndHashCode
public class PersonRegisterParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty("用户openKey")
    private String openKey;

    @NotBlank
    @ApiModelProperty("人物唯一标识")
    private String personTag;

    @NotEmpty
    @ApiModelProperty("人脸数据 |　base64数组 | 每张图片应当有且只有当前注册的这个人")
    private List<String> imgData;

}
