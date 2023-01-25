package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.ModifyTeacherSubjectLevelReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryTeacherSubjectLevelRespDto;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher_subject")
public class TeacherSubjectController extends BaseController {

    /**
     * 查询教师科目熟悉程度
     */
    @GetMapping("/query_teacher_subject_level")
    public ResultJson<List<QueryTeacherSubjectLevelRespDto>> queryTeacherSubjectLevel() {
        return null;
    }

    /**
     * 修改教师科目熟悉程度
     */
    @PutMapping("/modify_teacher_subject_level")
    public ResultJson<Void> modifyTeacherSubjectLevel(List<ModifyTeacherSubjectLevelReqDto> reqDtos) {
        return null;
    }


}
