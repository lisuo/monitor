package com.monitor.common.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: heguanyong
 * @Date: 2018/10/10 17:20
 * @Description:
 */
public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private Page<T> page;
    private int currPage = 1;
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        if (params.get("page") != null) {
            this.currPage = Integer.parseInt((String) params.get("page"));
        }

        if (params.get("limit") != null) {
            this.limit = Integer.parseInt((String) params.get("limit"));
        }

        this.put("offset", (this.currPage - 1) * this.limit);
        this.put("page", this.currPage);
        this.put("limit", this.limit);

    }

    public Page<T> getPage() {
        return this.page;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public int getLimit() {
        return this.limit;
    }
}