package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.ModifyStudentsScoreReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetAvgScoreByExamIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetResultByExamIdRespDto;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
public class ResultController extends BaseController {

    /**
     * 根据考试id获取用户成绩
     */
    @GetMapping("/get_result_by_exam_id/{examId}")
    public ResultJson<GetResultByExamIdRespDto> getResultByExamId(@PathVariable("examId") Integer examId) {
        return null;
    }

    /**
     * 根据考试id获取用户班级平均分
     */
    @GetMapping("/get_avg_score_by_exam_id/{examId}")
    public ResultJson<GetAvgScoreByExamIdRespDto> getAvgScoreByExamId(@PathVariable("examId") Integer examId) {
        return null;
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
