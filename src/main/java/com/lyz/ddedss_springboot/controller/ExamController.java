package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.GetExamListByStudentIdRespDto;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.service.ExamService;
import com.lyz.ddedss_springboot.service.ResultService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultService resultService;

    /**
     * 获取学生考试列表
     */
    @GetMapping("/getExamListByStudentId")
    public ResultJson<List<GetExamListByStudentIdRespDto>> getExamListByStudentId() {
        Integer roleId = getRoleId();
        // 获取考试id
        List<Integer> examIds = resultService.getExamId(roleId);

        List<GetExamListByStudentIdRespDto> respDtos = new ArrayList<>();

        // 根据考试id获取考试
        for (Integer examId : examIds) {
            GetExamListByStudentIdRespDto respDto = new GetExamListByStudentIdRespDto();
            Exam exam = examService.getById(examId);
            String examName;
            Integer year = exam.getYear();
            Integer month = exam.getMonth();
            if (month >= 9 || month <= 2) {
                examName = year + "年上学期期末考试";
            } else {
                examName = year + "年下学期期末考试";
            }
            respDto.setExamId(examId);
            respDto.setExamName(examName);
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }

}
