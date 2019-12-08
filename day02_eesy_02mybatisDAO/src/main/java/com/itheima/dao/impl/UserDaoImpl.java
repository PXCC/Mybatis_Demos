package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    public List<User> findAll() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法,实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll");//参数就是能获取到配置信息的key
        //释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //根据方法实现保存,不仅需要知道执行语句在哪，还必须获取参数
        session.insert("com.itheima.dao.IUserDao.saveUser",user);
        //提交事务
        session.commit();
        //释放资源
        session.close();

    }

    public void updateUser(User user) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //根据方法实现保存,不仅需要知道执行语句在哪，还必须获取参数
        session.update("com.itheima.dao.IUserDao.updateUser",user);
        //提交事务
        session.commit();
        //释放资源
        session.close();

    }

    public void deleteUser(Integer userId) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //根据方法实现保存,不仅需要知道执行语句在哪，还必须获取参数
        session.delete("com.itheima.dao.IUserDao.deleteUser",userId);
        //提交事务
        session.commit();
        //释放资源
        session.close();

    }

    public User findById(Integer userId) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法,实现查询列表
        User user = session.selectOne("com.itheima.dao.IUserDao.findById",userId);//参数就是能获取到配置信息的key
        //释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法,实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findByName",username);//参数就是能获取到配置信息的key
        //释放资源
        session.close();
        return users;
    }

    public int findTotal() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法,实现查询列表
        Integer count = session.selectOne("com.itheima.dao.IUserDao.findTotal");//参数就是能获取到配置信息的key
        //释放资源
        session.close();
        return count;
    }
}
