package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.QueryClassAndSubjectByTeacherIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryTeacherListByClassIdRespDto;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.entity.Teacher;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.service.LessonService;
import com.lyz.ddedss_springboot.service.SubjectService;
import com.lyz.ddedss_springboot.service.TeacherService;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
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
        return null;
    }


}
