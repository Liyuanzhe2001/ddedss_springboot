package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.ModifyStudentsScoreReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetAvgScoreByExamIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetResultByExamIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.HaveNotice;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.service.ExamService;
import com.lyz.ddedss_springboot.service.ResultService;
import com.lyz.ddedss_springboot.service.SubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.StudentScore;
import io.netty.util.internal.StringUtil;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private ExamService examService;

    @Autowired
    private StringRedisTemplate redis;

    /**
     * 根据考试id获取用户成绩
     */
    @GetMapping("/getResultByExamId/{examId}")
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
    @GetMapping("/getAvgScoreByExamId/{examId}")
    public ResultJson<GetAvgScoreByExamIdRespDto> getAvgScoreByExamId(@PathVariable("examId") Integer examId) {
        Integer roleId = getRoleId();

        Double avgScore = resultService.getAvgScore(roleId, examId);

        GetAvgScoreByExamIdRespDto respDto = new GetAvgScoreByExamIdRespDto(avgScore);

        return new ResultJson<>(OK, "查询成功", respDto);
    }

    /**
     * 是否有成绩需公布通知
     */
    @GetMapping("/haveAnnounceResultsNotice")
    public ResultJson<HaveNotice> haveAnnounceResultsNotice() {
        String notice = redis.opsForValue().get("announceResultsNotice");
        new HaveNotice();
        if (StringUtil.isNullOrEmpty(notice)) {
            return new ResultJson<>(OK, "没有成绩需要公布", new HaveNotice((short) 0));
        }
        return new ResultJson<>(OK, "有成绩需要公布", new HaveNotice((short) 1));
    }

    /**
     * 修改学生分数
     */
    @PutMapping("/modifyStudentsScore")
    public ResultJson<Void> modifyStudentsScore(@RequestBody ModifyStudentsScoreReqDto reqDto) {
        // 获取考试id
        Integer examId = examService.getLatestId();

        Integer subjectId = reqDto.getSubjectId();

        List<StudentScore> studentScores = reqDto.getStudentScores();

        for (StudentScore studentScore : studentScores) {
            Integer studentId = studentScore.getStudentId();
            Integer score = studentScore.getScore();
            resultService.modifyStudentScore(studentId, examId, subjectId, score);
        }

        return new ResultJson<>(OK,"修改成功");
    }

}
