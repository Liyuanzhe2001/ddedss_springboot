package com.lyz.ddedss_springboot.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegisterReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请码
     */
    private String invite;

    /**
     * 学号
     */
    private Integer number;

    /**
     * 姓名
     */
    private String username;

    /**
     * 性别（0女，1男）
     */
    private Short sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String code;
}
