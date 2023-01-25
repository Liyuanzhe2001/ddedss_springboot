package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.vo.StudentScoreVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController extends BaseController {

    /**
     * @param examId 考试id
     * @return {
     * code - 状态码
     * msg - 信息
     * result:[
     * {
     * subjctName - 科目
     * score - 分数
     * }
     * ],
     * totalScore - 总分
     * }
     */
    @GetMapping("/get_result_by_exam_id/{examId}")
    public String getResultByExamId(@PathVariable("examId") Integer examId) {
        return null;
    }

    /**
     * 根据考试id获取用户班级平均分
     *
     * @param examId 考试id
     * @return {
     * code - 状态码
     * msg - 信息
     * avgScore - 平均分
     * }
     */
    @GetMapping("/get_avg_score_by_exam_id/{examId}")
    public String getAvgScoreByExamId(@PathVariable("examId") Integer examId) {
        return null;
    }

    /**
     * 是否有成绩需公布通知
     *
     * @return {
     * code - 状态码
     * msg - 信息
     * haveOrNot - 有没有（0没有，1有）
     * }
     */
    @GetMapping("/have_announce_results_notice")
    public String haveAnnounceResultsNotice() {
        return null;
    }

    /**
     * 修改学生分数
     *
     * @param subjectName 科目名称
     * @param students    [
     *                    {
     *                    id - 学生id
     *                    score - 分数
     *                    }
     *                    ]
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PutMapping("/modify_students_score")
    public String modifyStudentsScore(String subjectName, List<StudentScoreVO> students) {
        return null;
    }


}
