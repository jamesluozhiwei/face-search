package com.lzw.face.dto;

import io.swagger.annotations.ApiModel;
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
    private String openKey;

    @NotBlank
    private String img;

}
