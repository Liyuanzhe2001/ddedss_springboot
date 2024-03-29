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
public class DeleteScheduleLessonReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 星期几
     */
    private Short weekday;

    /**
     * 第几节课
     */
    private Short section;

}
