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
public class ModifyTeacherSubjectLevelReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<SubjectLevel> subjectLevelList;

}
