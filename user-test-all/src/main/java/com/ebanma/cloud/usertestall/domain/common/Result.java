package com.ebanma.cloud.usertestall.domain.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 结果
 *
 * @author Lenovo
 * @date 2023/03/16
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 2788991284278655213L;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private Boolean success;
    private String code;
    private String message;
    private T data;
    private String timestamp;
    public static <T> Result<T> success(T payload) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ErrorCodeEnum.SUCCESS.getCode());
        result.setMessage(ErrorCodeEnum.SUCCESS.getDesc());
        result.setData(payload);
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(ErrorCodeEnum errorCode) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getDesc());
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }

    public static <T> Result<T> fail(Throwable throwable, T payload) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(throwable != null ? ExceptionUtils.getRootCauseMessage(throwable) :
                ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        result.setData(payload);
        return result;
    }

    public static <T> Result<T> fail(Throwable throwable) {
        return fail(throwable, null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
