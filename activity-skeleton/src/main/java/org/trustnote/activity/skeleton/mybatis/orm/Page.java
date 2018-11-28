package org.trustnote.activity.skeleton.mybatis.orm;

import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis分页参数及查询结果封装. 注意所有序号从1开始.
 * @param <T> Page中记录的类型.
 * @author zhuxl
 * @date   2016年5月20日 下午7:26:25
 */
public class Page<T> extends RowBounds {

    private int inviteeNum;

    public int getInviteeNum() {
        return this.inviteeNum;
    }

    public void setInviteeNum(final int inviteeNum) {
        this.inviteeNum = inviteeNum;
    }
    // --分页参数 --//
    /**
     * 页编号 : 第几页
     */
    protected int pageNo = 1;
    /**
     * 页大小 : 每页的数量
     */
    protected int pageSize = 20;

    /**
     * 偏移量 : 第一条数据在表中的位置
     */
    protected int offset;

    /**
     * 限定数 : 每页的数量
     */
    protected int limit;

    // --结果 --//
    /**
     * 查询结果
     */
    protected List<T> result = new ArrayList<>();

    /**
     * 总条数
     */
    protected int totalCount;

    /**
     * 总页数
     */
    protected int totalPages;

    // --计算 数据库 查询的参数 : LIMIT 3, 3; LIMIT offset, limit; --//
    /**
     * 计算偏移量
     */
    private void calcOffset() {
        this.offset = ((this.pageNo - 1) * this.pageSize);
    }

    /**
     * 计算限定数
     */
    private void calcLimit() {
        this.limit = this.pageSize;
    }

    // -- 构造函数 --//
    public Page() {
        this.calcOffset();
        this.calcLimit();
    }

    public Page(final int pageNo, final int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.calcOffset();
        this.calcLimit();
    }

    // -- 访问查询参数函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * 获得每页的记录数量,默认为1.
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((this.pageNo - 1) * this.pageSize) + 1;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
     */


    // -- 访问查询结果函数 --//
    /**
     * 取得页内的记录列表.
     */
    public List<T> getResult() {
        return this.result;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setResult(final List<T> result) {
        this.result = result;
    }

    /**
     * 取得总记录数, 默认值为-1.
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = this.getTotalPages();
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public int getTotalPages() {
        if (this.totalCount < 0) {
            return -1;
        }
        int pages = this.totalCount / this.pageSize;
        return this.totalCount % this.pageSize > 0 ? ++pages : pages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

}
