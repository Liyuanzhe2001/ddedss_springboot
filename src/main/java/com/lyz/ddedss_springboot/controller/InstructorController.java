package com.lyz.ddedss_springboot.controller;


import com.lyz.ddedss_springboot.dto.resp.GetManagedClassRespDto;
import com.lyz.ddedss_springboot.service.ClassService;
import com.lyz.ddedss_springboot.service.InstructorService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController extends BaseController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ClassService classService;

    @GetMapping("/getManagedClass")
    public ResultJson<List<GetManagedClassRespDto>> getManagedClass() {
        Integer teacherId = getRoleId();
        List<Integer> classIds = instructorService.getManagedClassIds(teacherId);

        List<GetManagedClassRespDto> respDtos = new ArrayList<>();
        for (Integer classId : classIds) {
            GetManagedClassRespDto respDto = new GetManagedClassRespDto();
            String className = classService.getById(classId).getName();
            respDto.setClassId(classId)
                    .setClassName(className);
            respDtos.add(respDto);
        }

        return new ResultJson<>(OK, "查询成功", respDtos);
    }

}
