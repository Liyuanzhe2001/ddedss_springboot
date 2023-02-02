package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.GetAllClassRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetClassNameByIdRespDto;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.exception.ClassNotFoundException;
import com.lyz.ddedss_springboot.service.ClassService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController extends BaseController {

    @Autowired
    private ClassService classService;

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

}
