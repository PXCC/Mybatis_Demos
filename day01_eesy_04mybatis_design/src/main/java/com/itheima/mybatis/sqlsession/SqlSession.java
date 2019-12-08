package com.itheima.mybatis.sqlsession;
//自定义Mybatis中和数据库交互的核心类
//可以创建dao接口的代理对象
public interface SqlSession {
    //根据参数创建一个代理对象
    //daoInterfaceClass dao的接口字节码
    <T>T getMapper(Class<T> daoInterfaceClass);
    //释放资源
    void close();
}
