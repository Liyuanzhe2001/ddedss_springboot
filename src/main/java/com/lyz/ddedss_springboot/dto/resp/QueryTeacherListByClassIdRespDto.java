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
public class QueryTeacherListByClassIdRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 科目
     */
    private String subjectName;

}
