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
    UN_KNOW_ERR(-1,"UN_KNOW_ERR")

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
