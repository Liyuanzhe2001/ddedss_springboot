package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.QueryClassAndSubjectByTeacherIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryTeacherListByClassIdRespDto;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    /**
     * 通过班级id查询教师课程列表
     */
    @GetMapping("/query_teacher_list_by_class_id/{classId}")
    public ResultJson<List<QueryTeacherListByClassIdRespDto>> queryTeacherListByClassId(@PathVariable("classId") Integer classId) {
        return null;
    }

    /**
     * 通过教师id获取教的班级和科目
     */
    @GetMapping("/query_class_and_subject_by_teacher_id")
    public ResultJson<List<QueryClassAndSubjectByTeacherIdRespDto>> queryClassAndSubjectByTeacherId() {
        return null;
    }


}
