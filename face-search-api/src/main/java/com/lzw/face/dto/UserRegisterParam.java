package com.lzw.face.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * 用户注册所需信息
 * @author jamesluozhiwei
 * @date 2019/12/29
 */
@Data
@ToString
@ApiModel("用户注册所需信息")
@EqualsAndHashCode
public class UserRegisterParam implements Serializable {
    private static final long serialVersionUID = -3743063949556083583L;

    @Email
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("验证码")
    private String code;

}
