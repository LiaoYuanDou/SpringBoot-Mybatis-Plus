package com.xx.study.springbootmybatisplus.exception;

/**
 * @Title: UniqueException
 * @ProjectName talk915
 * @Description:
 * @author: CZW
 * @date 2018/7/27 11:13
 */
public class UniqueException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String obj;
    private String message;

    public UniqueException() {
    }

    public UniqueException(String obj, String message) {
        this.obj = obj;
        this.message = message;
    }

    public UniqueException(String obj) {
        this.obj = obj;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
