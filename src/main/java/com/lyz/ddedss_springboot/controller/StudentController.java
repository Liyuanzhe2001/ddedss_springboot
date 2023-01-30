package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.HaveNotice;
import com.lyz.ddedss_springboot.dto.resp.QueryClassNameRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryStudentListByClassIdRespDto;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StringRedisTemplate redis;

    /**
     * 查询班级名
     */
    @GetMapping("/queryClassName")
    public ResultJson<QueryClassNameRespDto> queryClassName() {
        Integer roleId = getRoleId();
        Class_ class_ = studentService.getClassName(roleId);

        QueryClassNameRespDto respDto = new QueryClassNameRespDto()
                .setClassId(class_.getId())
                .setClassName(class_.getName());

        return new ResultJson<>(OK, "", respDto);
    }

    /**
     * 通过班级id查询班级学生列表
     */
    @GetMapping("/queryStudentListByClassId/{classId}")
    public ResultJson<List<QueryStudentListByClassIdRespDto>> queryStudentListByClassId(@PathVariable("classId") Integer classId) {
        List<Student> students = studentService.getStudents(classId);

        List<QueryStudentListByClassIdRespDto> respDtos = new ArrayList<>();

        QueryStudentListByClassIdRespDto tmpDto;
        for (Student student : students) {
            tmpDto = new QueryStudentListByClassIdRespDto()
                    .setStudentId(student.getId())
                    .setStudentName(student.getName())
                    .setStudentSex(student.getSex());
            respDtos.add(tmpDto);
        }

        return new ResultJson<>(OK, "获取成功", respDtos);
    }

    /**
     * 是否有课程评价通知
     */
    @GetMapping("/haveEvaluateCourseNotice")
    public ResultJson<HaveNotice> haveEvaluateCourseNotice() {
        setRoleId(10);
        Integer roleId = getRoleId();

        HaveNotice haveNotice = new HaveNotice().setHaveOrNot((short) 1);

        // 判断 redis 中有没有 evaluate
        int size = redis.opsForSet().members("evaluate").size();
        if (size == 0) {
            haveNotice.setHaveOrNot((short) 0);
            return new ResultJson<>(OK, "无课程评价", haveNotice);
        }

        // 名字在evaluate中，说明已完成评价
        Boolean evaluate = redis.opsForSet().isMember("evaluate", String.valueOf(roleId));
        if (Boolean.TRUE.equals(evaluate)) {
            haveNotice.setHaveOrNot((short) 0);
            return new ResultJson<>(OK, "无课程评价", haveNotice);
        }

        return new ResultJson<>(OK, "有课程评价", haveNotice);
    }

}
