package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.PageReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeListByIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeListRespDto;
import com.lyz.ddedss_springboot.entity.Knowledge;
import com.lyz.ddedss_springboot.service.KnowledgeService;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

    @Autowired
    private KnowledgeService knowledgeService;

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
     * 根据教师id获取发布的知识
     */
    @GetMapping("/queryKnowledgeListById")
    public ResultJson<List<QueryKnowledgeListByIdRespDto>> queryKnowledgeListById(PageReqDto pageReqDto) {
        Integer teacherId = getRoleId();
        Page<Knowledge> page = new Page<>(pageReqDto.getCurrentPage(), pageReqDto.getPageSize());
        page = knowledgeService.queryKnowledgeList(page, teacherId);

        List<QueryKnowledgeListByIdRespDto> respDtos = new ArrayList<>();

        for (Knowledge knowledge : page.getRecords()) {
            QueryKnowledgeListByIdRespDto respDto = new QueryKnowledgeListByIdRespDto()
                    .setKnowledgeId(knowledge.getId())
                    .setKnowledgeTitle(knowledge.getTitle());
            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos, page.getTotal());
    }

}
