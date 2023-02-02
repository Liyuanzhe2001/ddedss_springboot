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
public class AdminLoginReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private Integer number;

    /**
     * 密码
     */
    private String password;
}
