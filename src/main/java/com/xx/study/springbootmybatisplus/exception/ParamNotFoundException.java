package com.xx.study.springbootmybatisplus.exception;

/**
 * @Title: ParamNotFoundException
 * @ProjectName talk915
 * @Description:
 * @author: CZW
 * @date 2018/7/27 11:12
 */
public class ParamNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String param;

    public ParamNotFoundException() {
    }

    public ParamNotFoundException(String param) {
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
