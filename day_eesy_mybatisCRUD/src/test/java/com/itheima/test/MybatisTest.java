package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resources;
import javax.jws.soap.SOAPBinding;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

//测试myatis的crud操作
public class MybatisTest {
    private  InputStream in;
    private SqlSession session;
    private IUserDao userDao;
    @Before//用于在测试方法之前执行
    public void init()throws Exception{
        //读取配置文件生成字节输入流
        in = Resources.class.getResourceAsStream("/SqlMapConfig.xml");
        //获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //获取SqlSesion对象
        session = factory.openSession();
        //获取dao的代理对象
        userDao = session.getMapper(IUserDao.class);
    }
    @After//用于在测试方法之后执行
    public void destory()throws Exception{
        //提交事务
        session.commit();
        //释放资源
        session.close();
        in.close();
    }
    //测试查询所有
    @Test
    public void testFindAll() throws Exception{

        //执行查询所有方法
        List<User> users = userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }

    }
    //测试保存操作
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("mybatis_last_insertid_saveuser");
        user.setUserAddress("南京");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("pre"+user);
        //执行保存方法
        userDao.saveUser(user);
        System.out.println("after"+user);
    }
    //测试更新操作
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(49);
        user.setUserName("mybatis_updateuser");
        user.setUserAddress("南京雨花台");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        //执行更新方法
        userDao.updateUser(user);
    }
    //测试删除方法
    @Test
    public void testDelete(){
        //执行删除
        userDao.deleteUser(48);
    }
    //测试删除方法
    @Test
    public void testfindById(){
        //执行根据ID查询
        User user = userDao.findById(46);
        System.out.println(user);
    }
    //测试模糊查询
    @Test
    public void testfindByName(){
        //执行根据名称模糊查询
        //List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for(User user:users){
            System.out.println(user);
        }
    }
    //测试获取总用户记录数
    @Test
    public void testfindToatal(){
       int count = userDao.findTotal();
       System.out.println(count);
    }
    //测试使用queryVo作为模糊查询条件
    @Test
    public void testfindByVo(){
        //执行根据名称模糊查询
        //List<User> users = userDao.findByName("%王%");
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for(User u:users){
            System.out.println(u);
        }
    }
}
