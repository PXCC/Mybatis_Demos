package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

//用户持久层接口
public interface IUserDao {
    //查询所有用户
    List<User> findAll();
    //保存方法
    void saveUser(User user);
    //更新用户
    void updateUser(User user);
    //删除用户
    void deleteUser(Integer userId);
    //根据ID查询
    User findById(Integer userId);
    //根据名称模糊查询用户信息
    List<User> findByName(String username);
    //查询总用户数
    int findTotal();
}
