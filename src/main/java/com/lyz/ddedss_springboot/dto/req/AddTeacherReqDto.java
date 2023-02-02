package com.lyz.ddedss_springboot.dto.req;

import com.lyz.ddedss_springboot.vo.SubjectLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AddTeacherReqDto implements Serializable {

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

    /**
     * 性别 0女 1男
     */
    private Short sex;

    /**
     * 擅长科目
     */
    private List<SubjectLevel> subjectLevelList;

}
