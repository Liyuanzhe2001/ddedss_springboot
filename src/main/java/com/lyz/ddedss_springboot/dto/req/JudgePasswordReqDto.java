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
public class JudgePasswordReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    private String password;

}
