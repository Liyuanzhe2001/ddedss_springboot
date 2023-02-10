package com.lyz.ddedss_springboot.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.lyz.ddedss_springboot.dto.req.SetCourseEvaluationReqDto;
import com.lyz.ddedss_springboot.dto.resp.GetAvgScoreByExamIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.GetEvaluationTimeRespDto;
import com.lyz.ddedss_springboot.dto.resp.HaveNotice;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController {

    @Autowired
    private StringRedisTemplate redis;

    /**
     * 是否有课程评价通知
     */
    @GetMapping("/haveEvaluateCourseNotice")
    public ResultJson<HaveNotice> haveEvaluateCourseNotice() {
        Integer roleId = getRoleId();

        HaveNotice haveNotice = new HaveNotice().setHaveOrNot((short) 1);

        // 判断 redis 中有没有 evaluate
        int size = redis.opsForSet().members("evaluate").size();
        if (size == 0) {
            haveNotice.setHaveOrNot((short) 0);
            return new ResultJson<>(OK, "无课程评价", haveNotice);
        }

        // 名字在evaluate中，说明已完成评价
        Boolean evaluate = redis.opsForSet().isMember("evaluate", String.valueOf(roleId));
        if (Boolean.TRUE.equals(evaluate)) {
            haveNotice.setHaveOrNot((short) 0);
            return new ResultJson<>(OK, "无课程评价", haveNotice);
        }

        return new ResultJson<>(OK, "有课程评价", haveNotice);
    }

    /**
     * 获取课程评价时间
     */
    @GetMapping("/getEvaluationTime")
    public ResultJson<GetEvaluationTimeRespDto> getEvaluationTime() {
        String startTime = redis.opsForValue().get("startTime");
        String endTime = redis.opsForValue().get("endTime");
        boolean startFlag = true, endFlag = true;
        if (startTime == null) {
            startFlag = false;
        }
        if (endTime == null) {
            endFlag = false;
        }
        GetEvaluationTimeRespDto respDto = new GetEvaluationTimeRespDto();
        if (startFlag && endFlag) {
            respDto.setStart(startTime)
                    .setEnd(endTime);
            return new ResultJson<>(OK, "查询成功", respDto);
        }
        if (!startFlag && !endFlag) {
            return new ResultJson<>(OK, "查询成功", respDto);
        }
        if (startFlag) {
            redis.delete("startTime");
        }
        if (endFlag) {
            redis.delete("endTime");
        }
        return new ResultJson<>(OK, "查询成功", respDto);
    }

    /**
     * 预定课程评估价
     */
    @PostMapping("/setCourseEvaluation")
    public ResultJson<Void> setCourseEvaluation(@RequestBody SetCourseEvaluationReqDto reqDto) {
        String start = reqDto.getStart();
        String end = reqDto.getEnd();
        redis.opsForValue().set("startTime", start);
        redis.opsForValue().set("endTime", end);
        return new ResultJson<>(OK, "设置成功");
    }

}
