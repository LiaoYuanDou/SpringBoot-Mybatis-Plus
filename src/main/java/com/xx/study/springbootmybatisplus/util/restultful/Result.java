package com.xx.study.springbootmybatisplus.util.restultful;

/**
 * @ClassName: Result
 * @Author: XX
 * @Date: 2018/7/31 13:54
 * @Description: 返回结果类
 */
public class Result extends BaseResult {

    private Data resultData;

    public Result() {
    }

    Result(int resultCode, String resultMessage, Data resultData) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }


    public Data getResultData() {
        return resultData;
    }

    public void setResultData(Data resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", resutlState='" + resutlState + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}
