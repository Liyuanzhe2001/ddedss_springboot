package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.vo.SubjectLevelVO;
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
     *
     * @return {
     * code - 状态码
     * msg - 信息
     * subjectLevel:[
     * {
     * name - 科目
     * level - 熟悉度
     * }
     * ]
     * }
     */
    @GetMapping("/query_teacher_subject_level")
    public String queryTeacherSubjectLevel() {
        return null;
    }

    /**
     * 修改教师科目熟悉程度
     *
     * @param subjectLevel [
     *                     {
     *                     name - 科目
     *                     level - 熟悉度
     *                     }
     *                     ]
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     * }
     */
    @PutMapping("/modify_teacher_subject_level")
    public String modifyTeacherSubjectLevel(List<SubjectLevelVO> subjectLevel) {
        return null;
    }


}
