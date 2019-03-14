package com.monitor.base;

import com.monitor.common.Constant;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * @Description: 分页入参
 * @author lisuo
 * @date 2018/9/8 0008下午 9:07
 */
public class PageVo {

    @NotNull
    @ApiModelProperty(value = "页码（默认值1）")
    private Integer page = Constant.ONE;

    @NotNull
    @ApiModelProperty(value = "页面数量（认值50）")
    private Integer limit = 50;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
