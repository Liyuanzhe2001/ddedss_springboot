package com.lyz.ddedss_springboot.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateInviteRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 注册码
     */
    private String invite;

    /**
     * 剩余天数
     */
    private Integer day;

    /**
     * 剩余小时
     */
    private Integer hour;

}
