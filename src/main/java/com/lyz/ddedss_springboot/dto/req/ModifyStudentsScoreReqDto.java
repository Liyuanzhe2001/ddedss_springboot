package com.lyz.ddedss_springboot.dto.req;

import com.lyz.ddedss_springboot.vo.StudentScore;
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
public class ModifyStudentsScoreReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目名称
     */
    private Integer subjectId;

    /**
     * 学生分数列表
     */
    private List<StudentScore> studentScores;

}
