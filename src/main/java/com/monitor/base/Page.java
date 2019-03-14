package com.monitor.base;

import java.util.List;

/**
 * @author lisuo
 * @Title: lisuo
 * @ProjectName monitor
 * @Description: TODO
 * @date 2018/9/7 0007下午 11:37
 */
public class Page<T> {
    private Integer total;
    private Integer index;
    private Integer size;
    private List<T> list;

    public Page() {
    }

    public Page(Integer total, Integer index, Integer size, List<T> list) {
        this.total = total;
        this.index = index;
        this.size = size;
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
