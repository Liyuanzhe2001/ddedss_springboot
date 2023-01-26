package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.HaveNotice;
import com.lyz.ddedss_springboot.dto.resp.QueryClassNameRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryStudentListByClassIdRespDto;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    /**
     * 查询班级名
     */
    @GetMapping("/query_class_name")
    public ResultJson<QueryClassNameRespDto> queryClassName() {
        return null;
    }

    /**
     * 通过班级id查询班级学生列表
     */
    @GetMapping("/query_student_list_by_class_id/{classId}")
    public ResultJson<List<QueryStudentListByClassIdRespDto>> queryStudentListByClassId(@PathVariable("classId") Integer classId) {
        return null;
    }

    /**
     * 是否有课程评价通知
     */
    @GetMapping("/have_evaluate_course_notice")
    public ResultJson<HaveNotice> haveEvaluateCourseNotice() {
        return null;
    }

}
