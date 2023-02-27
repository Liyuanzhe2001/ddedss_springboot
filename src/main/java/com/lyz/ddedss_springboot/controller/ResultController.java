package com.lyz.ddedss_springboot.controller;

import cn.hutool.core.util.NumberUtil;
import com.lyz.ddedss_springboot.dto.req.GetExaminationResultsReqDto;
import com.lyz.ddedss_springboot.dto.req.GetStudentScoreListReqDto;
import com.lyz.ddedss_springboot.dto.req.ModifyStudentsScoreReqDto;
import com.lyz.ddedss_springboot.dto.resp.*;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.service.ExamService;
import com.lyz.ddedss_springboot.service.ResultService;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.service.SubjectService;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.ExaminationResults;
import com.lyz.ddedss_springboot.vo.StudentScore;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private StudentService studentService;

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

        GetAvgScoreByExamIdRespDto respDto = new GetAvgScoreByExamIdRespDto(NumberUtil.round(avgScore, 2).doubleValue());

        return new ResultJson<>(OK, "查询成功", respDto);
    }

    /**
     * 是否有成绩需公布通知
     */
    @GetMapping("/haveAnnounceResultsNotice")
    public ResultJson<HaveNotice> haveAnnounceResultsNotice() {
        String notice = redis.opsForValue().get("announceResultsNotice");
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

        return new ResultJson<>(OK, "修改成功");
    }

    /**
     * 根据班级id 考试id获取该班级在该次考试中的情况
     */
    @GetMapping("/getExaminationResults")
    public ResultJson<List<GetExaminationResultsRespDto>> getExaminationResults(GetExaminationResultsReqDto reqDto) {
        // 获取班级所有学生
        List<Student> students = studentService.getStudents(reqDto.getClassId());
        // 获取班级所有学生id
        List<Integer> studentIds = new ArrayList<>();
        students.forEach(student -> {
            studentIds.add(student.getId());
        });

        List<ExaminationResults> results = resultService.getExaminationResults(studentIds, reqDto.getExamId());
        List<GetExaminationResultsRespDto> respDtos = new ArrayList<>();
        for (ExaminationResults result : results) {
            GetExaminationResultsRespDto respDto = new GetExaminationResultsRespDto()
                    .setSubjectName(result.getSubjectName())
                    .setGood(result.getGood())
                    .setMid(result.getMid())
                    .setBad(result.getBad());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    /**
     * 获取学生信息和分数
     */
    @GetMapping("/getStudentScoreList")
    public ResultJson<List<GetStudentScoreListRespDto>> getStudentScoreList(GetStudentScoreListReqDto reqDto) {
        // 通过classId获取所有学生信息
        List<Student> students = studentService.getStudents(reqDto.getClassId());

        List<GetStudentScoreListRespDto> respDtos = new ArrayList<>();

        Integer examId = examService.getLatestId();

        GetStudentScoreListRespDto tmpDto;
        for (Student student : students) {
            // 通过学生id，科目id，考试id获取学生该次考试分数
            Integer score = resultService.getResult(student.getId(), reqDto.getSubjectId(), examId).getScore();
            if (score < 0) {
                score = null;
            }
            tmpDto = new GetStudentScoreListRespDto()
                    .setStudentId(student.getId())
                    .setStudentName(student.getName())
                    .setStudentSex(student.getSex())
                    .setScore(score);
            respDtos.add(tmpDto);
        }

        return new ResultJson<>(OK, "查询成功", respDtos);
    }

    /**
     * 返回近五年科目平均成绩
     */
    @GetMapping("/getFiveYearResult")
    public ResultJson<List<GetFiveYearResultRespDto>> getFiveYearResult(@RequestParam("subjectIds") List<Integer> subjectIds) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        List<GetFiveYearResultRespDto> respDtos = new ArrayList<>();

        for (Integer subjectId : subjectIds) {
            GetFiveYearResultRespDto respDto = new GetFiveYearResultRespDto();
            String[] resultScore = new String[5];

            Map<Integer, Map<String, Double>> avgScoreByTime = resultService.getAvgScoreByTime(year - 4, year, subjectId);

            for (Integer key : avgScoreByTime.keySet()) {
                resultScore[Math.abs(year - key - 4)] = avgScoreByTime.get(key).get("score") + "";
            }

            respDto.setResultData(resultScore);
            respDtos.add(respDto);
        }

        return new ResultJson<>(OK, "查询成功", respDtos);
    }
}
