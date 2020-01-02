package com.lzw.face.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty(value = "手机号码")
    private String tel;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "公司")
    private String company;

}
