package com.lzw.face.config;

import com.lzw.face.common.ApiResponse;
import com.lzw.face.common.ApiResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * api 异常处理
 * @author jamesluozhiwei
 * @date 2020/01/08 20:03
 */
@Slf4j
@ControllerAdvice(basePackages = "com.lzw.face.controller")
public class ApiExceptionHandler {

    /**
     * 运行时异常
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage(),ex);
        return ApiResponse.response(ApiResponseCode.UN_KNOW_ERR,"系统运行异常");
    }



    /**
     * 空指针异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse nullPointerExceptionHandler(NullPointerException ex) {
        log.error(ex.getMessage(),ex);
        return ApiResponse.response(ApiResponseCode.UN_KNOW_ERR,"系统空指针异常");
    }


    /*----- REQUEST ERROR -----*/

    /**
     * 400错误
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse requestNotReadable(HttpMessageNotReadableException ex){
        log.error(ex.getMessage(),ex);
        return ApiResponse.response(ApiResponseCode.UN_KNOW_ERR,"参数格式错误(缺少分隔符或结束标签)："+ex.getMessage());
    }

    /**
     * 400错误
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse requestTypeMismatch(TypeMismatchException ex){
        log.error(ex.getMessage(),ex);
        return ApiResponse.response(ApiResponseCode.UN_KNOW_ERR,"参数类型不匹配："+ex.getMessage());
    }

    /**
     * 400错误
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse requestMissingServletRequest(MissingServletRequestParameterException ex){
        log.error(ex.getMessage(),ex);
        return ApiResponse.response(ApiResponseCode.UN_KNOW_ERR,"缺少请求参数："+ex.getMessage());
    }

    /**
     * 405错误
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ApiResponse request405(){
        return ApiResponse.response(ApiResponseCode.UN_KNOW_ERR,"不支持该请求方式：");
    }

}
