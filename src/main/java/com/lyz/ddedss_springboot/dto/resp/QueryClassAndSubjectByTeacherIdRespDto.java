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
public class QueryClassAndSubjectByTeacherIdRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 班级名
     */
    private String className;

    /**
     * 班级人数
     */
    private Integer peopleNum;

    /**
     * 课程id
     */
    private Integer subjectId;

    /**
     * 课程名
     */
    private String subjectName;

}
