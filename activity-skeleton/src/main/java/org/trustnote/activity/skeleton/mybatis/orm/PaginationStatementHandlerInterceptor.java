package org.trustnote.activity.skeleton.mybatis.orm;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author 
 * @since 2014年5月18日 下午1:36:31
 **/
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PaginationStatementHandlerInterceptor implements Interceptor {
	private static final Logger logger = LogManager.getLogger(PaginationStatementHandlerInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        final StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        final ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        final BoundSql boundSql = statementHandler.getBoundSql();

        final MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,
                PaginationStatementHandlerInterceptor.DEFAULT_OBJECT_FACTORY,
                PaginationStatementHandlerInterceptor.DEFAULT_OBJECT_WRAPPER_FACTORY,
                PaginationStatementHandlerInterceptor.DEFAULT_REFLECTOR_FACTORY);
        final RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        // 没有分页参数
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        final Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        final Dialect dialect = DialectFactory.buildDialect(configuration);
        final String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        // 获取总记录数
        final Page<?> page = (Page<?>) rowBounds;
        final String countSql = dialect.getCountString(originalSql);
        final Connection connection = (Connection) invocation.getArgs()[0];
        final int total = this.getTotal(parameterHandler, connection, countSql);
        page.setTotalCount(total);

        // 设置物理分页语句
        metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, page.getOffset(), page.getLimit()));
        // 屏蔽mybatis原有分页
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        if (PaginationStatementHandlerInterceptor.logger.isDebugEnabled()) {
            PaginationStatementHandlerInterceptor.logger.debug("分页SQL {}" + boundSql.getSql());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(final Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(final Properties properties) {
    }

    /**
     * 获取总计录
     * 
     * @param parameterHandler
     * @param connection
     * @param countSql
     * @return
     * @throws Exception
     */
    private int getTotal(final ParameterHandler parameterHandler, final Connection connection, final String countSql) throws Exception {
        final PreparedStatement prepareStatement = connection.prepareStatement(countSql);
        parameterHandler.setParameters(prepareStatement);
        final ResultSet rs = prepareStatement.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        prepareStatement.close();
        return count;
    }
}
