package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.ModifyStudentsScoreReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetAvgScoreByExamIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetResultByExamIdRespDto;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.service.ResultService;
import com.lyz.ddedss_springboot.service.SubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController extends BaseController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 根据考试id获取用户成绩
     */
    @GetMapping("/get_result_by_exam_id/{examId}")
    public ResultJson<List<GetResultByExamIdRespDto>> getResultByExamId(@PathVariable("examId") Integer examId) {
        Integer roleId = getRoleId();
        List<Result> results = resultService.getResults(examId, roleId);

        List<GetResultByExamIdRespDto> respDtos = new ArrayList<>();

        for (Result result : results) {
            GetResultByExamIdRespDto respDto = new GetResultByExamIdRespDto();
            String name = subjectService.getById(result.getSubjectId()).getName();
            respDto.setSubjectName(name);
            respDto.setScore(result.getScore());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    /**
     * 根据考试id获取用户班级平均分
     */
    @GetMapping("/get_avg_score_by_exam_id/{examId}")
    public ResultJson<GetAvgScoreByExamIdRespDto> getAvgScoreByExamId(@PathVariable("examId") Integer examId) {
        Integer roleId = getRoleId();

        Double avgScore = resultService.getAvgScore(roleId, examId);

        GetAvgScoreByExamIdRespDto respDto = new GetAvgScoreByExamIdRespDto(avgScore);

        return new ResultJson<>(OK, "查询成功", respDto);
    }

    /**
     * 是否有成绩需公布通知
     */
    @GetMapping("/have_announce_results_notice")
    public String haveAnnounceResultsNotice() {
        return null;
    }

    /**
     * 修改学生分数
     */
    @PutMapping("/modify_students_score")
    public ResultJson<Void> modifyStudentsScore(ModifyStudentsScoreReqDto reqDto) {
        return null;
    }


}
