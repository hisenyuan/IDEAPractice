package com.hisen.mybatisStudy.no4.mapper;

import com.hisen.mybatisStudy.po.User;
import com.hisen.mybatisStudy.po.UserCustom;
import com.hisen.mybatisStudy.po.UserQueryVo;

import java.util.List;

/**
 * Created by hisen on 17-3-25.
 */
public interface UserMapper {
    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws Exception;

    //删除用户信息
    public void deleteUser(int id) throws Exception;

    //更新用户
    public void updateUser(User user)throws Exception;

    //用户信息综合查询
    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
}
