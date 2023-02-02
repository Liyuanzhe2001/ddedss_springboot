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
public class AddProfessionalReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工号
     */
    private Integer number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

}
