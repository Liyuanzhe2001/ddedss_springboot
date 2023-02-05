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
public class GetStudentScoreListRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 性别
     */
    private Short studentSex;

    /**
     * 分数
     */
    private Integer score;

}
