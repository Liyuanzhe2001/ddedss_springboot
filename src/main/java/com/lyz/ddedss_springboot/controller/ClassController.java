package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.GetAllClassCodeInfoReqDto;
import com.lyz.ddedss_springboot.dto.resp.*;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.exception.ClassNotFoundException;
import com.lyz.ddedss_springboot.exception.InsufficientPermissionException;
import com.lyz.ddedss_springboot.service.ClassService;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.util.ResultJson;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/class")
public class ClassController extends BaseController {

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

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
                    .setCode(redis.opsForValue().get(class_.getId() + ""));
            res.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", res, allClassList.getTotal());
    }

    /**
     * 创建注册码
     */
    @GetMapping("/createInvite/{classId}")
    public ResultJson<String> createInvite(@PathVariable("classId") Integer classId) {
        // 创建随机注册码
        String inviteCode = UUID.randomUUID().toString().replaceAll("-", "");
        // 存入redis
        // 注册码 班级id
        redis.opsForValue().set(inviteCode, classId + "");
        // 班级id 注册码
        redis.opsForValue().set(classId + "", inviteCode);
        return new ResultJson<>(OK, "创建成功", inviteCode);
    }

    /**
     * 删除注册码
     */
    @DeleteMapping("/deleteInvite/{classId}")
    public ResultJson<Void> deleteInvite(@PathVariable("classId") Integer classId) {
        // 通过班级id获取注册码
        String inviteCode = redis.opsForValue().get(classId + "");
        if (StringUtil.isNullOrEmpty(inviteCode)) {
            return new ResultJson<>(OK, "删除成功");
        }
        redis.delete(inviteCode);
        redis.delete(classId + "");
        return new ResultJson<>(OK, "删除成功");
    }

}
