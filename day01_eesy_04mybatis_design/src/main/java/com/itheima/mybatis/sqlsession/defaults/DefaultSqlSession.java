package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

//SqlSession接口的实现类
public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection connection;
    public DefaultSqlSession(Configuration cfg){
        this.cfg=cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }
    //用于创建代理对象
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        //T表示的是返回值T是泛型，T是一个占位符，用来告诉编译器，
        // 这个东西先给我留着，等我编译的时候，告诉你
         return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));

    }
    //用于释放资源
    public void close() {
        if(connection!=null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
