package com.monitor.common.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: heguanyong
 * @Date: 2018/10/10 17:32
 * @Description:
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<?> list;

    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount / (double)pageSize);
    }

    public PageUtils(Page<?> page) {
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = page.getPages();
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}