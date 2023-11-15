package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 通过学号/工号 获取用户
     *
     * @return 用户
     */
    public User getUserByNumber(String number);

    /**
     * 通过学号/工号 邮箱 判断是否存在该用户
     */
    public boolean judgeUserByNumberAndEmail(Integer number, String email);

    /**
     * 通过学号/工号修改用户密码
     */
    public boolean modifyPasswordByNumber(Integer number, String password) throws NoSuchAlgorithmException;

    /**
     * 判断用户密码
     */
    public boolean judgeUserPassword(Integer userId, String password) throws NoSuchAlgorithmException;

    /**
     * 通过roleId修改用户密码
     */
    public boolean modifyPasswordByRoleId(Integer userId, String password) throws NoSuchAlgorithmException;

    /**
     * 通过用户id获取管理员
     */
    public User getAdminByNumber(Integer number);

    /**
     * 模糊查询所有用户 （学号/工号）
     */
    public Page<User> queryAllUserLike(String like, Page<User> page);

    /**
     * 查询非管理员用户
     */
    public User getNormalUser(Integer userId);

    /**
     * 修改用户学号/工号
     */
    public Boolean modifyNumber(Integer id, String number);

    /**
     * 修改用户邮箱
     */
    public Boolean modifyEmail(Integer id, String email);

    /**
     * 通过学生id删除用户
     */
    public Boolean deleteUserByStudentId(Integer studentId);

}
