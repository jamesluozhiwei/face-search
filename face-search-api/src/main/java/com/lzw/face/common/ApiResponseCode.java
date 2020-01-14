package com.lzw.face.common;

/**
 * 响应code枚举
 * @author jamesluozhiwei
 * @date 2019/12/25
 */
public enum ApiResponseCode {

    /**
     * 正常
     */
    NORMAL(200,"SUCCESS"),
    /**
     * 未知错误
     */
    UN_KNOW_ERR(-1,"UN_KNOW_ERR"),

    /**
     * 接口调用异常
     */
    API_USE_ERROR(-2,"API_USE_ERROR"),

    /**
     * 该邮箱已注册
     */
    EMAIL_HAD_REGISTERED(-101,"EMAIL_HAD_REGISTERED"),

    /**
     * 邮箱错误
     */
    EMAIL_ERROR(-102,"EMAIL_ERROR"),
    /**
     * 邮箱验证码错误
     */
    EMAIL_CODE_ERROR(-103,"EMAIL_CODE_ERROR"),
    /**
     * open key 不存在
     */
    OPEN_KEY_NOT_EXISTS(-104,"OPEN_KEY_NOT_EXISTS")
    ;

    /**
     * 编码
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ApiResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
