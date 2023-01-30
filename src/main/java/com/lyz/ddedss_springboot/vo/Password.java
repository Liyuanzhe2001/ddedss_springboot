package com.lyz.ddedss_springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Password implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    private String password;

}
