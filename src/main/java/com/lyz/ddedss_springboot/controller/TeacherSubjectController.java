package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.ModifyTeacherSubjectLevelReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryTeacherSubjectLevelRespDto;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.service.SubjectService;
import com.lyz.ddedss_springboot.service.TeacherService;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.SubjectLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher_subject")
public class TeacherSubjectController extends BaseController {

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 查询教师科目熟悉程度
     */
    @GetMapping("/queryTeacherSubjectLevel")
    public ResultJson<List<QueryTeacherSubjectLevelRespDto>> queryTeacherSubjectLevel() {
        setRoleId(1);
        Integer teacherId = getRoleId();
        List<TeacherSubject> teacherSubjects = teacherSubjectService.getTeacherSubjects(teacherId);

        List<QueryTeacherSubjectLevelRespDto> respDtos = new ArrayList<>();
        for (TeacherSubject teacherSubject : teacherSubjects) {
            QueryTeacherSubjectLevelRespDto respDto = new QueryTeacherSubjectLevelRespDto();
            String subjectName = subjectService.getById(teacherSubject.getSubjectId()).getName();
            respDto.setSubjectName(subjectName);
            respDto.setLevel(teacherSubject.getLevel());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    /**
     * 修改教师科目熟悉程度
     */
    @PutMapping("/modifyTeacherSubjectLevel")
    public ResultJson<Void> modifyTeacherSubjectLevel(@RequestBody ModifyTeacherSubjectLevelReqDto reqDto) {
        setRoleId(1);
        Integer teacherId = getRoleId();

        // 创建teacherSubject列表，方便修改失败进行回滚
        List<TeacherSubject> teacherSubjectList = new ArrayList<>();

        List<SubjectLevel> subjectLevelList = reqDto.getSubjectLevelList();
        for (SubjectLevel subjectLevel : subjectLevelList) {
            TeacherSubject teacherSubject = new TeacherSubject()
                    .setLevel(subjectLevel.getLevel())
                    .setTeacherId(teacherId);

            // 获取该科目的id
            Integer subjectId = subjectService.getId(subjectLevel.getSubjectName());
            // 如果不存在该科目，创建科目，再拿到科目id
            // 如果存在，拿到teacherSubjectId，拿到科目id
            if (subjectId == -1) {
                Subject subject = new Subject()
                        .setName(subjectLevel.getSubjectName());
                subjectService.save(subject);
                teacherSubject.setSubjectId(subject.getId());
            } else {
                teacherSubject
                        .setId(teacherSubjectService.getId(teacherId, subjectId))
                        .setSubjectId(subjectId);
            }
            teacherSubjectList.add(teacherSubject);
        }

        // 保存或修改
        teacherSubjectService.saveOrUpdateBatch(teacherSubjectList);

        return new ResultJson<>(OK, "修改成功");
    }


}
