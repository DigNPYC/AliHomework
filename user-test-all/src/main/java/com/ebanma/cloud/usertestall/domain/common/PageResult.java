package com.ebanma.cloud.usertestall.domain.common;

import java.io.Serializable;

/**
 * @author 肖露
 * @version $ Id: PageResult, v 0.1 2023/03/17 10:23 banma-0241 Exp $
 */
public class PageResult <T>implements Serializable {
    private static final long serialVersionUID=507400077443555698L;

    private Integer pageNo;
    private Integer pageSize;
    private Long total;
    private Long pageNum;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }
}
