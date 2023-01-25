package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.GetExamListByStudentIdRespDto;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    /**
     *  获取学生考试列表
     */
    @GetMapping("/get_exam_list_by_student_id/{studentId}")
    public ResultJson<List<GetExamListByStudentIdRespDto>> getExamListByStudentId(@PathVariable("studentId") Integer studentId) {
        return null;
    }

}
