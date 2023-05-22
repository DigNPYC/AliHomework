package com.ebanma.cloud.usertestall.domain.common;

import java.io.Serializable;

/**
 * @author 肖露
 * @version $ Id: PageQuery, v 0.1 2023/03/17 10:22 banma-0241 Exp $
 */
public class PageQuery <T>implements Serializable {
    private static final long serialVersionUID=5443997546397888159L;
    private Integer pageNo=1;
    private Integer pageSize=20;
    private T query;


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

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }
}
