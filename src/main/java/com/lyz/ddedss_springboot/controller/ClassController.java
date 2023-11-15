package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.GetAllClassCodeInfoReqDto;
import com.lyz.ddedss_springboot.dto.req.GetClassesByPageReqDto;
import com.lyz.ddedss_springboot.dto.req.ModifyClassReqDto;
import com.lyz.ddedss_springboot.dto.resp.*;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.entity.Lesson;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.exception.*;
import com.lyz.ddedss_springboot.exception.ClassNotFoundException;
import com.lyz.ddedss_springboot.service.ClassService;
import com.lyz.ddedss_springboot.service.LessonService;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.service.UserService;
import com.lyz.ddedss_springboot.util.ResultJson;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/class")
public class ClassController extends BaseController {

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redis;

    /**
     * 根据id查询班级名称
     */
    @GetMapping("/getClassNameById/{classId}")
    public ResultJson<GetClassNameByIdRespDto> getClassNameById(@PathVariable("classId") Integer classId) {
        String name = classService.getById(classId).getName();
        if (name == null) {
            throw new ClassNotFoundException("该班级不存在");
        }
        GetClassNameByIdRespDto respDto = new GetClassNameByIdRespDto(name);
        return new ResultJson<>(OK, "查询成功", respDto);
    }

    /**
     * 获取所有班级列表
     */
    @GetMapping("/getAllClass")
    public ResultJson<List<GetAllClassRespDto>> getAllClass() {
        List<Class_> classList = classService.getAllClassList();

        List<GetAllClassRespDto> respDtos = new ArrayList<>();
        for (Class_ class_ : classList) {
            GetAllClassRespDto respDto = new GetAllClassRespDto()
                    .setClassId(class_.getId())
                    .setClassName(class_.getName())
                    .setClassType(class_.getType())
                    .setMajor(class_.getMajor());
            respDtos.add(respDto);
        }

        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    /**
     * 分页获取班级列表
     */
    @GetMapping("/getClasses")
    public ResultJson<List<GetClassesByPageRespDto>> getClassesByPage(GetClassesByPageReqDto reqDto) {
        // 模糊查询班级
        Page<Class_> page = new Page<>(reqDto.getCurrentPage(), reqDto.getPageSize());
        Page<Class_> classList = classService.getAllDetailedLikeClassList(reqDto.getClassName(), page);
        List<GetClassesByPageRespDto> res = new ArrayList<>();
        for (Class_ class_ : classList.getRecords()) {
            GetClassesByPageRespDto respDto = new GetClassesByPageRespDto()
                    .setClassId(class_.getId())
                    .setClassName(class_.getName())
                    .setGrade(class_.getGrade())
                    .setMajor(class_.getMajor())
                    .setType(class_.getType());
            res.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", res);
    }

    /**
     * 修改班级信息
     */
    @PutMapping("/modifyClass")
    public ResultJson<Void> modifyClass(@RequestBody Class_ class_) {
        boolean update = classService.updateById(class_);
        if (!update) {
            throw new FailedModifyClassException("修改班级信息失败");
        }
        return new ResultJson<Void>(OK, "修改信息成功");
    }

    /**
     * 删除班级
     */
    @DeleteMapping("/deleteClass/{classId}")
    @Transactional
    public ResultJson<Void> deleteClass(@PathVariable("classId") Integer classId) {
        // 删除班级
        boolean rmClass = classService.removeById(classId);
        if (!rmClass) {
            throw new FailedDeleteClassException("删除班级失败");
        }
        // 删除班级课程
        Boolean rmLesson = lessonService.deleteClassLesson(classId);
        if (!rmLesson) {
            throw new FailedDeleteLessonException("删除课程失败");
        }
        // 删除相关用户
        // 1. 获取班级学生
        List<Student> students = studentService.getStudents(classId);
        for (Student student : students) {
            // 2. 删除班级学生
            if (!studentService.removeById(student.getId())) {
                System.err.println("删除学生失败" + student.getId());
                throw new FailedDeleteStudentException("删除学生失败");
            }
            // 3. 删除用户
            if (!userService.deleteUserByStudentId(student.getId())) {
                System.err.println("删除用户失败" + student.getId());
                throw new FailedDeleteUserException("删除用户失败");
            }
        }
        return new ResultJson<>(OK, "删除成功");
    }

    /**
     * 获取所有与用户相关班级
     */
    @GetMapping("/getAllRelevantClasses")
    public ResultJson<List<GetAllRelevantClassesRespDto>> getAllRelevantClasses() {
        Integer teacher = getRoleId();
        List<Class_> classes = classService.getClasses(teacher);
        List<GetAllRelevantClassesRespDto> list = new ArrayList<>();
        for (Class_ aClass : classes) {
            list.add(new GetAllRelevantClassesRespDto()
                    .setClassId(aClass.getId())
                    .setClassName(aClass.getName()));
        }
        return new ResultJson<>(OK, "获取班级列表成功", list);
    }

    /**
     * 获取所有班级、人数、班级注册码
     */
    @GetMapping("/getAllClassCodeInfo")
    public ResultJson<List<GetAllClassCodeInfoRespDto>> getAllClassCodeInfo(GetAllClassCodeInfoReqDto reqDto) {

        Page<Class_> page = new Page<>(reqDto.getCurrentPage(), reqDto.getPageSize());

        Page<Class_> allClassList = classService.getAllLikeClassList(reqDto.getLike(), page);
        List<GetAllClassCodeInfoRespDto> res = new ArrayList<>();
        for (Class_ class_ : allClassList.getRecords()) {
            GetAllClassCodeInfoRespDto respDto = new GetAllClassCodeInfoRespDto();
            respDto.setId(class_.getId())
                    .setName(class_.getName())
                    .setNum(studentService.getStudents(class_.getId()).size())
                    .setCode(redis.opsForValue().get(class_.getName() + ""));
            res.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", res, allClassList.getTotal());
    }

    /**
     * 创建注册码
     */
    @GetMapping("/createInvite/{classId}")
    public ResultJson<String> createInvite(@PathVariable("classId") Integer classId) {
        String className = classService.getById(classId).getName();
        // 创建随机注册码
        String inviteCode = UUID.randomUUID().toString().replaceAll("-", "");
        // 存入redis
        // 注册码 班级id
        redis.opsForValue().set(inviteCode, classId + "");
        // 班级名 注册码
        redis.opsForValue().set(className + "", inviteCode);
        return new ResultJson<>(OK, "创建成功", inviteCode);
    }

    /**
     * 删除注册码
     */
    @DeleteMapping("/deleteInvite/{classId}")
    public ResultJson<Void> deleteInvite(@PathVariable("classId") Integer classId) {
        String className = classService.getById(classId).getName();
        // 通过班级id获取注册码
        String inviteCode = redis.opsForValue().get(className + "");
        if (StringUtil.isNullOrEmpty(inviteCode)) {
            return new ResultJson<>(OK, "删除成功");
        }
        redis.delete(inviteCode);
        redis.delete(className);
        return new ResultJson<>(OK, "删除成功");
    }

}
