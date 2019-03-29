package com.xx.study.springbootmybatisplus.exception;

/**
 * @Title: MissingParamException
 * @ProjectName talk915
 * @Description:
 * @author: CZW
 * @date 2018/7/27 11:12
 */
public class MissingParamException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String parameterName;
    private String parameterType;

    public MissingParamException() {
        super();
    }

    public MissingParamException(String parameterName) {
        super();
        this.parameterName = parameterName;
    }

    public MissingParamException(String parameterName, String parameterType) {
        super();
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
}


