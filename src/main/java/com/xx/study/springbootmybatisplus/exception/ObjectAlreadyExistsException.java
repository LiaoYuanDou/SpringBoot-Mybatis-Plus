package com.xx.study.springbootmybatisplus.exception;

/**
 * @Title: ObjectAlreadyExistsException
 * @ProjectName talk915
 * @Description:
 * @author: CZW
 * @date 2018/7/27 11:12
 */
public class ObjectAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String param;

    public ObjectAlreadyExistsException() {
    }

    public ObjectAlreadyExistsException(String param) {
        super();
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}
