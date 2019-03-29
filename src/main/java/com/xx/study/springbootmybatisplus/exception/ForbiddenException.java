package com.xx.study.springbootmybatisplus.exception;

/**
 * @Title: ForbiddenException
 * @ProjectName talk915
 * @Description: yunx
 * @author: CZW
 * @date 2018/7/27 11:11
 */
public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
