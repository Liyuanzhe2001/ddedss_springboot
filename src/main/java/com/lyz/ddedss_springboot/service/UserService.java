package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.User;

import java.security.NoSuchAlgorithmException;

public interface UserService extends IService<User> {

    /**
     * 通过学号/工号 获取用户
     *
     * @return 用户
     */
    public User getUserByNumber(Integer number);

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
    public boolean judgeUserPassword(Integer roleId, String password) throws NoSuchAlgorithmException;

    /**
     * 通过roleId修改用户密码
     */
    public boolean modifyPasswordByRoleId(Integer roleId, String password) throws NoSuchAlgorithmException;

}
