package org.trustnote.activity.skeleton.mybatis.orm;

import org.apache.ibatis.executor.resultset.FastResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author 
 * @since 2014年5月18日 下午1:36:08
 **/
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PaginationResultSetHandlerInterceptor implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
//    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(final Invocation invocation) throws Throwable {
        final FastResultSetHandler resultSetHandler = (FastResultSetHandler) invocation.getTarget();
        final MetaObject metaStatementHandler = MetaObject.forObject(resultSetHandler, PaginationResultSetHandlerInterceptor.DEFAULT_OBJECT_FACTORY, PaginationResultSetHandlerInterceptor.DEFAULT_OBJECT_WRAPPER_FACTORY);
        final RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("rowBounds");

        final Object result = invocation.proceed();

        if (rowBounds instanceof Page) {
            metaStatementHandler.setValue("rowBounds.result", result);
        }
        return result;
    }

    @Override
    public Object plugin(final Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(final Properties properties) {
    }

}
