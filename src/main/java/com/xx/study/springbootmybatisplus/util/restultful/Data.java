package com.xx.study.springbootmybatisplus.util.restultful;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Data
 * @Author: XX
 * @Date: 2018/7/31 13:47
 * @Description: 返回结果数据层类
 */
public class Data implements Serializable {
    private List<?> dataList;
    private long totalCount;
    private long currentPage;
    private long pageSize;

    public Data() {
    }

    public Data(List<?> dataList) {
        this.dataList = dataList;
        this.totalCount = 0;
        this.currentPage = 0;
        this.pageSize = 0;
    }

    public Data(List<?> dataList, long totalCount, long currentPage, long pageSize) {
        this.dataList = dataList;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Data{" +
                "dataList=" + dataList +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }

}
