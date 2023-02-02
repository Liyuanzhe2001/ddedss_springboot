package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.GetExaminationResultsReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetAllExamByClassIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetExamListByStudentIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetExaminationResultsRespDto;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.service.ClassService;
import com.lyz.ddedss_springboot.service.ExamService;
import com.lyz.ddedss_springboot.service.ResultService;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.ExaminationResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

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

    /**
     * 获取班级的所有考试
     */
    @GetMapping("/getAllExamByClassId/{classId}")
    public ResultJson<List<GetAllExamByClassIdRespDto>> getAllExamByClassId(@PathVariable Integer classId) {
        // 获取班级信息
        Class_ class_ = classService.getById(classId);
        // 年级
        Integer grade = class_.getGrade();
        // （0本科，1专科）
        Short type = class_.getType();

        LocalDateTime startTime = LocalDateTime.of(grade, 9, 1, 0, 0);
        LocalDateTime endTime;
        if (type == 0) {
            endTime = LocalDateTime.of(grade + 4, 8, 31, 23, 59, 59);
        } else {
            endTime = LocalDateTime.of(grade + 3, 8, 31, 23, 59, 59);
        }
        List<Exam> exams = examService.getExam(startTime, endTime);

        List<GetAllExamByClassIdRespDto> respDtos = new ArrayList<>();
        for (Exam exam : exams) {
            GetAllExamByClassIdRespDto respDto = new GetAllExamByClassIdRespDto()
                    .setExamId(exam.getId())
                    .setMonth(exam.getMonth())
                    .setYear(exam.getYear());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }
}
