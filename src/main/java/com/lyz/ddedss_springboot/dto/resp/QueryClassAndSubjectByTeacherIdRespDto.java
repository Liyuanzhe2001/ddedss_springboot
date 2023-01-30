package com.lyz.ddedss_springboot.dto.resp;

import com.lyz.ddedss_springboot.vo.ClassAndSubject;
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

    private ClassAndSubject classAndSubject;

    /**
     * 是否完成（0未完成，1已完成）
     */
    private Short haveFinish;

}
