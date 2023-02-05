package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.AddKnowledgeReqDto;
import com.lyz.ddedss_springboot.dto.req.PageReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeByIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeListByTeacherIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeListRespDto;
import com.lyz.ddedss_springboot.entity.Knowledge;
import com.lyz.ddedss_springboot.exception.FailedCreateKnowledgeException;
import com.lyz.ddedss_springboot.exception.FailedDeleteKnowledgeException;
import com.lyz.ddedss_springboot.exception.InsufficientPermissionException;
import com.lyz.ddedss_springboot.service.KnowledgeService;
import com.lyz.ddedss_springboot.service.TeacherService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询知识列表
     */
    @GetMapping("/queryKnowledgeList")
    public ResultJson<List<QueryKnowledgeListRespDto>> queryKnowledgeList(PageReqDto pageReqDto) {
        Page<Knowledge> page = new Page<>(pageReqDto.getCurrentPage(), pageReqDto.getPageSize());
        knowledgeService.page(page);
        List<QueryKnowledgeListRespDto> respDtos = new ArrayList<>();
        for (Knowledge knowledge : page.getRecords()) {
            QueryKnowledgeListRespDto respDto = new QueryKnowledgeListRespDto();
            respDto
                    .setKnowledgeId(knowledge.getId())
                    .setKnowledgeTitle(knowledge.getTitle());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos, page.getTotal());
    }

    /**
     * 根据知识id获取知识
     */
    @GetMapping("/queryKnowledgeById/{knowledgeId}")
    public ResultJson<QueryKnowledgeByIdRespDto> queryKnowledgeById(@PathVariable("knowledgeId") Integer knowledgeId) {
        Knowledge knowledge = knowledgeService.getById(knowledgeId);
        String title = knowledge.getTitle();
        Integer teacherId = knowledge.getTeacherId();
        String teacherName = teacherService.getById(teacherId).getName();
        String tagString = knowledge.getTags();
        String[] split = tagString.split(",");
        List<String> tags = Arrays.asList(split);
        String content = knowledge.getContent();

        QueryKnowledgeByIdRespDto respDto = new QueryKnowledgeByIdRespDto()
                .setKnowledgeTitle(title)
                .setTeacherName(teacherName)
                .setTags(tags)
                .setContent(content);

        return new ResultJson<>(OK, "查询成功", respDto);
    }

    /**
     * 根据教师id获取发布的知识
     */
    @GetMapping("/queryKnowledgeListByTeacherId")
    public ResultJson<List<QueryKnowledgeListByTeacherIdRespDto>> queryKnowledgeListByTeacherId(PageReqDto pageReqDto) {
        Integer teacherId = getRoleId();
        Page<Knowledge> page = new Page<>(pageReqDto.getCurrentPage(), pageReqDto.getPageSize());
        page = knowledgeService.queryKnowledgeList(page, teacherId);

        List<QueryKnowledgeListByTeacherIdRespDto> respDtos = new ArrayList<>();

        for (Knowledge knowledge : page.getRecords()) {
            QueryKnowledgeListByTeacherIdRespDto respDto = new QueryKnowledgeListByTeacherIdRespDto()
                    .setKnowledgeId(knowledge.getId())
                    .setKnowledgeTitle(knowledge.getTitle());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos, page.getTotal());
    }

    /**
     * 删除知识
     */
    @DeleteMapping("/deleteKnowledge/{knowledgeId}")
    public ResultJson<Void> deleteKnowledge(@PathVariable("knowledgeId") Integer knowledgeId) {
        // 获取教师id
        Integer teacherId = getRoleId();
        // 判断该知识是否属于该教师
        Knowledge knowledge = knowledgeService.getById(knowledgeId);
        if (knowledge.getTeacherId().equals(teacherId)) {
            boolean flag = knowledgeService.removeById(knowledgeId);
            if (flag) {
                return new ResultJson<>(OK, "删除成功");
            } else {
                throw new FailedDeleteKnowledgeException("删除知识失败");
            }
        } else {
            throw new InsufficientPermissionException("权限不足");
        }
    }

    /**
     * 新增知识
     */
    @PostMapping("/addKnowledge")
    public ResultJson<Void> addKnowledge(@RequestBody AddKnowledgeReqDto reqDto){
        Integer teacherId = getRoleId();
        Knowledge knowledge = new Knowledge()
                .setTeacherId(teacherId)
                .setTitle(reqDto.getTitle())
                .setContent(reqDto.getContent())
                .setTags(reqDto.getTags());
        boolean flag = knowledgeService.save(knowledge);
        if(flag){
            return new ResultJson<>(OK,"创建知识成功");
        }else {
            throw new FailedCreateKnowledgeException("知识创建失败");
        }
    }
}
