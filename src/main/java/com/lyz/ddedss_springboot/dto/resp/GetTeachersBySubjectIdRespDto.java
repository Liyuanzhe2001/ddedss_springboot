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
public class GetTeachersBySubjectIdRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 教师名
     */
    private String  teacherName;

}
