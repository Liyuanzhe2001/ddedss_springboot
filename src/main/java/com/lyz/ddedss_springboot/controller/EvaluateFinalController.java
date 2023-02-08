package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.GetEvaluationByTeacherNameReqDto;
import com.lyz.ddedss_springboot.dto.req.GradeTeacherReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetEvaluationByTeacherNameRespDto;
import com.lyz.ddedss_springboot.entity.Evaluate;
import com.lyz.ddedss_springboot.entity.EvaluateFinal;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.service.*;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.GradeTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StringRedisTemplate redis;

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

        redis.opsForSet().add("evaluate", getRoleId().toString());

        return new ResultJson<>(OK, "评价成功");
    }

    @GetMapping("/getEvaluationByTeacherName")
    public ResultJson<List<GetEvaluationByTeacherNameRespDto>> getEvaluationByTeacherName(GetEvaluationByTeacherNameReqDto reqDto) {
        String likeInputValue = reqDto.getLikeInputValue();
        // 获取teacher_subject
        Page<TeacherSubject> page = new Page<>(reqDto.getCurrentPage(), reqDto.getPageSize());
        page = teacherSubjectService.getListLikeTeacherName(likeInputValue, page);

        List<GetEvaluationByTeacherNameRespDto> respDtos = new ArrayList<>();

        for (TeacherSubject teacherSubject : page.getRecords()) {
            GetEvaluationByTeacherNameRespDto respDto = new GetEvaluationByTeacherNameRespDto();
            // 根据teacherId获取teacherName
            String teacherName = teacherService.getById(teacherSubject.getTeacherId()).getName();
            // 根据subjectId获取subjectName
            String subjectName = subjectService.getById(teacherSubject.getSubjectId()).getName();

            respDto.setTeacherName(teacherName)
                    .setSubjectName(subjectName);

            // 根据teacher_subject_id获取评价ids
            List<Integer> ids = evaluateService.getIds(teacherSubject.getId());
            // 根据evaluate ids 获取这几评价的结果总和
            if (!ids.isEmpty()) {
                // 判断 evaluate 有没有好评
                boolean haveGood = evaluateFinalService.haveGood(ids);
                // 判断 evaluate 中有没有差评
                boolean haveBad = evaluateFinalService.haveBad(ids);
                List<Integer> result = evaluateFinalService.getResult(ids);

                if (haveBad && haveGood) {
                    respDto.setGoodNum(result.get(0))
                            .setBadNum(result.get(1));
                } else if (haveBad) {
                    respDto.setGoodNum(0)
                            .setBadNum(result.get(0));
                } else if (haveGood) {
                    respDto.setGoodNum(result.get(0))
                            .setBadNum(0);
                }
            } else {
                respDto.setGoodNum(0)
                        .setBadNum(0);
            }
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos, page.getTotal());
    }
}
