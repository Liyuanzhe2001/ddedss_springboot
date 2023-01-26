package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.GetClassNameByIdRespDto;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassController extends BaseController {

    /**
     * 根据id查询班级名称
     */
    @GetMapping("/get_class_name_by_id/{classId}")
    public ResultJson<GetClassNameByIdRespDto> getClassNameById(@PathVariable("classId") Integer classId) {
        return null;
    }
}
