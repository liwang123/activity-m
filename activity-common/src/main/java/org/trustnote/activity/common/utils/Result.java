package org.trustnote.activity.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * API 接口返回对象
 * @author zhuxl
 *         CreateTime: 17/12/26
 */
public class Result {
    private String code;
    private String msg;
    private Object entity;
    private boolean hasMore;
    private Integer totalCount;

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public Object getEntity() {
        return this.entity;
    }

    public void setEntity(final Object entity) {
        this.entity = entity;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public void setHasMore(final boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getString(final Result result) {
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect);
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }
}
