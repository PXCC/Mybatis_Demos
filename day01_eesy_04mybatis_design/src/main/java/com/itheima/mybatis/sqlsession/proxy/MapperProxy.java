package com.itheima.mybatis.sqlsession.proxy;

import com.itheima.mybatis.cfg.Mapper;
import com.itheima.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    private Map<String,Mapper> mappers;//<namespace.id,sql和resultType>
    private Connection conn;
    public MapperProxy(Map<String,Mapper> mappers,Connection conn){
        this.conn = conn;
        this.mappers=mappers;
    }
    //用于对方法进行增强，我们的增强其实就会调用selectlist方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法名
        String methodNmae = method.getName();
        //获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //组合key
        String key = className+"."+methodNmae;
        //获取mappers中的mapper对象
        Mapper mapper = mappers.get(key);
        if(mapper==null){
            throw new IllegalAccessException("传入的参数有误");
        }
        //调用工具类执行查询所有
        return new Executor().selectList(mapper,conn);
    }
}
