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
public class GetAllSubjectRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目id
     */
    private Integer subjectId;

    /**
     * 科目名
     */
    private String subjectName;

}
