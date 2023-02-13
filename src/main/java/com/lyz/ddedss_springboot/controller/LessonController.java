package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.ScheduleLessonReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetLessonsByClassIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetLessonsByTeacherIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetSubjectNameByIdRespDto;
import com.lyz.ddedss_springboot.entity.Lesson;
import com.lyz.ddedss_springboot.exception.FailedCreateLessonException;
import com.lyz.ddedss_springboot.service.LessonService;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController extends BaseController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @GetMapping("/getLessonsByClassId/{classId}")
    public ResultJson<List<GetLessonsByClassIdRespDto>> getLessonsByClassId(@PathVariable("classId") Integer classId) {
        List<Lesson> lessonList = lessonService.getLessonList(classId);
        List<GetLessonsByClassIdRespDto> respDtos = new ArrayList<>();
        for (Lesson lesson : lessonList) {
            GetLessonsByClassIdRespDto respDto = new GetLessonsByClassIdRespDto()
                    .setWeekday(lesson.getWeekday())
                    .setSection(lesson.getSection());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    /**
     * 获取教师的带的课
     */
    @GetMapping("/getLessonsByTeacherId/{teacherId}")
    public ResultJson<List<GetLessonsByTeacherIdRespDto>> getLessonsByTeacherId(@PathVariable("teacherId") Integer teacherId) {
        List<Lesson> lessonList = lessonService.getLessonListByTeacherId(teacherId);
        List<GetLessonsByTeacherIdRespDto> respDtos = new ArrayList<>();
        for (Lesson lesson : lessonList) {
            GetLessonsByTeacherIdRespDto respDto = new GetLessonsByTeacherIdRespDto()
                    .setWeekday(lesson.getWeekday())
                    .setSection(lesson.getSection());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    @PostMapping("/scheduleLesson")
    public ResultJson<Void> scheduleLesson(@RequestBody ScheduleLessonReqDto reqDto) {
        // 根据教师id，科目id获取 teacher_subject_id
        Integer teacherSubjectId = teacherSubjectService.getId(reqDto.getTeacherId(), reqDto.getSubjectId());
        // 创建lesson
        Lesson lesson = new Lesson()
                .setClassId(reqDto.getClassId())
                .setTeacherSubjectId(teacherSubjectId)
                .setWeekday(reqDto.getWeekday())
                .setSection(reqDto.getSection());

        boolean flag = lessonService.save(lesson);

        if (!flag) {
            throw new FailedCreateLessonException("课程创建失败");
        }

        return new ResultJson<>(OK, "课程创建成功");
    }

}
