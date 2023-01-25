package com.lyz.ddedss_springboot.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("class")
public class EvaluateTeacherVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 科目id
     */
    private Integer subjectId;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 评分
     */
    private Short evaluate;

}
