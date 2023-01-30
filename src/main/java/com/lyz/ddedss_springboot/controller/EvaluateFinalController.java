package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.GradeTeacherReqDto;
import com.lyz.ddedss_springboot.entity.Evaluate;
import com.lyz.ddedss_springboot.entity.EvaluateFinal;
import com.lyz.ddedss_springboot.service.EvaluateFinalService;
import com.lyz.ddedss_springboot.service.EvaluateService;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.GradeTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/evaluateFinal")
public class EvaluateFinalController extends BaseController {

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private EvaluateFinalService evaluateFinalService;

    /**
     * 给教师的课程评价
     */
    @PostMapping("/gradeTeacher")
    public ResultJson<Void> gradeTeacher(@RequestBody GradeTeacherReqDto reqDtos) {
        // 获得evaluate最新time
        Integer latestTime = evaluateService.getLatestTime();

        Integer studentId = getRoleId();
        List<GradeTeacher> gradeTeachers = reqDtos.getGradeTeachers();

        for (GradeTeacher gradeTeacher : gradeTeachers) {
            // 根据teacherId subjectId 获取 teacher_subject_id
            Integer teacherId = gradeTeacher.getTeacherId();
            Integer subjectId = gradeTeacher.getSubjectId();
            Integer teacherSubjectId = teacherSubjectService.getId(teacherId, subjectId);

            // 根据teacher_subject_id time 获取 evaluate_id
            Integer evaluateId = evaluateService.getId(teacherSubjectId, latestTime);

            // 插入数据 evaluate_id studentId(session) final_
            EvaluateFinal evaluateFinal = new EvaluateFinal()
                    .setEvaluateId(evaluateId)
                    .setStudentId(studentId)
                    .setFinal_(gradeTeacher.getFinal_());
            evaluateFinalService.save(evaluateFinal);
        }

        return new ResultJson<>(OK, "评价成功");
    }

}
