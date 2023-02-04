package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.resp.HaveNotice;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
