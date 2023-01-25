package com.lyz.ddedss_springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StudentScoreVO {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Integer id;

    /**
     * 分数
     */
    private Integer score;

}
