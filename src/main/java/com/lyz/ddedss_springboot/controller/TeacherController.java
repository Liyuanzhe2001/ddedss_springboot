package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.QueryClassAndSubjectByTeacherIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryTeacherListByClassIdRespDto;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.entity.Teacher;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.service.*;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.ClassAndSubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultService resultService;

    /**
     * 通过班级id查询教师课程列表
     */
    @GetMapping("/query_teacher_list_by_class_id/{classId}")
    public ResultJson<List<QueryTeacherListByClassIdRespDto>> queryTeacherListByClassId(@PathVariable("classId") Integer classId) {
        // 通过 班级id 拿到 List<教师科目id>
        List<Integer> teacherSubjectIds = lessonService.getTeacherSubjectId(classId);

        List<QueryTeacherListByClassIdRespDto> respDtos = new ArrayList<>();

        // 通过教师科目id 拿到 教师id 科目id
        for (Integer teacherSubjectId : teacherSubjectIds) {
            TeacherSubject teacherSubject = teacherSubjectService.getById(teacherSubjectId);

            Integer teacherId = teacherSubject.getTeacherId();
            // 通过教师id拿到教师信息
            Teacher teacher = teacherService.getById(teacherId);

            // 通过科目id拿到科目信息
            Integer subjectId = teacherSubject.getSubjectId();
            Subject subject = subjectService.getById(subjectId);

            QueryTeacherListByClassIdRespDto respDto = new QueryTeacherListByClassIdRespDto(teacherId, teacher.getName(), teacher.getSex(), subjectId, subject.getName());

            respDtos.add(respDto);
        }

        return new ResultJson<>(OK, "获取成功", respDtos);
    }

    /**
     * 通过教师id获取教的班级和科目
     */
    @GetMapping("/query_class_and_subject_by_teacher_id")
    public ResultJson<List<QueryClassAndSubjectByTeacherIdRespDto>> queryClassAndSubjectByTeacherId() {
        setRoleId(100);
        Integer teacherId = getRoleId();

        List<QueryClassAndSubjectByTeacherIdRespDto> respDtos = new ArrayList<>();

        List<ClassAndSubjectVO> classAndSubjectList = teacherSubjectService.getClassAndSubject(teacherId);

        // 获取最新的考试id
        Integer examId = examService.getLatestId();

        for (ClassAndSubjectVO classAndSubject : classAndSubjectList) {
            QueryClassAndSubjectByTeacherIdRespDto respDto = new QueryClassAndSubjectByTeacherIdRespDto();
            respDto.setClassAndSubject(classAndSubject);

            Integer classId = classAndSubject.getClassId();
            Integer subjectId = classAndSubject.getSubjectId();
            boolean flag = resultService.checkNoScore(examId, subjectId, classId);
            if (flag) {
                respDto.setHaveFinish((short) 1);
            } else {
                respDto.setHaveFinish((short) 0);
            }
            respDtos.add(respDto);
        }

        return new ResultJson<>(OK, "查询成功", respDtos);
    }


}
