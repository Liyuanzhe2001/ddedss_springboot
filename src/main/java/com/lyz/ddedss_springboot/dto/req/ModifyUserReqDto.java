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
public class ModifyUserReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String number;

    private String email;

}
