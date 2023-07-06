package com.lyz.ddedss_springboot;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.lyz.ddedss_springboot.entity.Evaluate;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.service.EvaluateService;
import com.lyz.ddedss_springboot.service.ExamService;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class DdedssSpringbootApplication {

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private ExamService examService;

    @Autowired
    private StringRedisTemplate redis;

    public static void main(String[] args) {
        SpringApplication.run(DdedssSpringbootApplication.class, args);
    }

    /**
     * 每日0:00检查是否有课程评价需要开始
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void searchCourseEvaluation() {
        // 获取预定的评价开始时间
        String start = redis.opsForValue().get("startTime");
        // 无开始时间，则说明未预定评价
        if (start == null) {
            return;
        }

        // 如果有开始时间，判断今天是否为开始时间
        DateTime startTime = DateUtil.parse(start);
        DateTime nowTime = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd");
        // 如果预定评价早已开始，直接退出
        if (startTime.compareTo(nowTime) < 0) {
            return;
        }

        // 如果今天为开始时间，在redis中放入 "evaluate 开始时间"
        if (startTime.compareTo(nowTime) == 0) {
            Integer latestTime = evaluateService.getLatestTime() + 1;
            List<Integer> teacherSubjectIds = teacherSubjectService.getAllIds();
            List<Evaluate> evaluateList = new ArrayList<>();
            teacherSubjectIds.forEach(teacherSubjectId -> {
                evaluateList.add(new Evaluate()
                        .setTeacherSubjectId(teacherSubjectId)
                        .setTime(latestTime));
            });
            boolean flag = evaluateService.saveBatch(evaluateList);
            if (!flag) {
                throw new RuntimeException("评价创建出错");
            }
            redis.opsForSet().add("evaluate", startTime.toDateStr());
            return;
        }

        String end = redis.opsForValue().get("endTime");
        // 无开始时间，则说明程序出错，终止评价
        if (end == null) {
            redis.delete("evaluate");
            redis.delete("startTime");
            redis.delete("endTime");
            return;
        }
        DateTime endTime = DateUtil.parse(end);
        // 如果今天不是结束时间，直接退出
        if (endTime.compareTo(nowTime) != 0) {
            return;
        }
        // 如果今天是结束时间
        if (endTime.compareTo(nowTime) == 0) {
            redis.delete("evaluate");
            redis.delete("startTime");
            redis.delete("endTime");
        }
    }

    /**
     * 每日0:00检查是否有考试评分需要开始
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void searchScore() {
        // 获取预定的评价开始时间
        String start = redis.opsForValue().get("scoreStartTime");
        // 无开始时间，则说明未预定评价
        if (start == null) {
            return;
        }

        // 如果有开始时间，判断今天是否为开始时间
        DateTime startTime = DateUtil.parse(start);
        DateTime nowTime = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd");
        // 如果预定评价早已开始，直接退出
        if (startTime.compareTo(nowTime) < 0) {
            return;
        }

        // 如果今天为开始时间，在redis中放入 "announceResultsNotice true"
        if (startTime.compareTo(nowTime) == 0) {
            redis.opsForSet().add("announceResultsNotice", "true");
            return;
        }

        String end = redis.opsForValue().get("scoreEndTime");
        // 无结束时间，则说明程序出错，终止评价
        if (end == null) {
            redis.delete("announceResultsNotice");
            redis.delete("scoreStartTime");
            redis.delete("scoreEndTime");
            return;
        }
        DateTime endTime = DateUtil.parse(end);
        // 如果今天不是结束时间，直接退出
        if (endTime.compareTo(nowTime) != 0) {
            return;
        }
        // 如果今天是结束时间
        if (endTime.compareTo(nowTime) == 0) {
            redis.delete("announceResultsNotice");
            redis.delete("scoreStartTime");
            redis.delete("scoreEndTime");
        }
    }

    /**
     * 每年 1月1日 6月1日新增当前学期考试
     */
    @Scheduled(cron = "0 0 0 1 1,6 *")
    public void addExam() {
        Exam exam = new Exam();
        Calendar now = Calendar.getInstance();
        exam.setYear(now.get(Calendar.YEAR))
                .setMonth(now.get(Calendar.MONTH) + 1);
        examService.addExam(exam);
    }
}
