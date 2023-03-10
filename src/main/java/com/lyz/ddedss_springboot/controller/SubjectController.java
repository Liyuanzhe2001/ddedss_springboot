package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.GetAllSubjectRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetSubjectNameByIdRespDto;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.service.SubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController extends BaseController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 根据id查询科目名称
     */
    @GetMapping("/getSubjectNameById/{subjectId}")
    public ResultJson<GetSubjectNameByIdRespDto> getSubjectNameById(@PathVariable("subjectId") Integer subjectId) {
        Subject subject = subjectService.getById(subjectId);

        GetSubjectNameByIdRespDto respDto = new GetSubjectNameByIdRespDto(subject.getName());

        return new ResultJson<>(OK, "查询成功", respDto);
    }

    @GetMapping("/getAllSubject")
    public ResultJson<List<GetAllSubjectRespDto>> getAllSubject() {
        List<Subject> subjectList = subjectService.getAllSubjectList();

        List<GetAllSubjectRespDto> respDtos = new ArrayList<>();
        for (Subject subject : subjectList) {
            GetAllSubjectRespDto respDto = new GetAllSubjectRespDto()
                    .setSubjectId(subject.getId())
                    .setSubjectName(subject.getName());
            respDtos.add(respDto);
        }

        return new ResultJson<>(OK, "查询成功", respDtos);
    }

}
