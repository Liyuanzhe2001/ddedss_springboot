package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.User;

public interface UserService extends IService<User> {

    /**
     * 通过学号/工号 密码获取用户
     *
     * @param number   学号/工号
     * @param password 密码
     * @return 获取到的用户
     */
    public User getUserByNumberAndPassword(Integer number, String password);
}
