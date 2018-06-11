package org.trustnote.activity.skeleton.mybatis.core;


/**
 * 所有自定义枚举类型实现该接口
 * @author zhuxl
 * @date 2016年3月24日
 */
public interface GenericEnum {

    /**
     * value: 为保存在数据库中的值
     */
    public String getValue();

    /**
     * text : 为前端显示值
     */
    public String getText();

}
