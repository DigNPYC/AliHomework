package com.ebanma.cloud.usertestall.domain.common;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author 肖露
 * @version $ Id: Result, v 0.1 2023/03/20 8:40 banma-0241 Exp $
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 906941499553860342L;

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    private Boolean success;

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

    public String getMessae() {
        return messae;
    }

    public void setMessae(String messae) {
        this.messae = messae;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String code;
    private String messae;

    private T date;

    private String timestamp;

    public static <T> Result<T> success(T payload) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMessae(ErrorCode.SUCCESS.getDesc());
        result.setDate(payload);
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(errorCode.getCode());
        result.setMessae(errorCode.getDesc());
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }

    public static <T> Result<T> fail(Throwable ex, T payload) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        result.setMessae(ex != null ? ExceptionUtils.getRootCauseMessage(ex) : ErrorCode.SYSTEM_ERROR.getDesc());
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        result.setDate(payload);
        return result;
    }

    public static <T> Result<T> fail(Throwable ex) {
        return fail(ex, null);

    }

    public static <T> Result<T> fail() {
        return fail(null, null);
    }

}
