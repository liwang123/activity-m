//package org.trustnote.activity.skeleton.mybatis;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.jdbc.datasource.AbstractDataSource;
//import org.springframework.util.CollectionUtils;
//
//import javax.sql.DataSource;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 多数据源实现
// * @author zhuxl
// */
//public class MultipleDataSource extends AbstractDataSource implements InitializingBean {
//    private static final Logger log = LogManager.getFormatterLogger(MultipleDataSource.class);
//    private DataSource activityDataSource;
//    private Map<String, DataSource> otherDataSourceMap;
//    private String[] otherDataSourceNames;
//    private DataSource[] otherDataSources;
//    private int otherDataSourceCount;
//    private AtomicInteger counter = new AtomicInteger(1);
//
//    public void setOtherDataSourceMap(Map<String, DataSource> otherDataSourceMap) {
//        this.otherDataSourceMap = otherDataSourceMap;
//    }
//
//    public void setActivityDataSource(DataSource activityDataSource) {
//        this.activityDataSource = activityDataSource;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        if (activityDataSource == null) {
//            throw new IllegalArgumentException("property 'dataSource' is required");
//        }
//        if (CollectionUtils.isEmpty(otherDataSourceMap)) {
//            throw new IllegalArgumentException("property 'otherDataSource' is required");
//        }
//        otherDataSourceCount = otherDataSourceMap.size();
//        otherDataSources = new DataSource[otherDataSourceCount];
//        otherDataSourceNames = new String[otherDataSourceCount];
//        int i = 0;
//        for (Map.Entry<String, DataSource> entry: otherDataSourceMap.entrySet()) {
//            otherDataSources[i] = entry.getValue();
//            otherDataSourceNames[i] = entry.getKey();
//            i++;
//        }
//    }
//}
