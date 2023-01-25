package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.PageReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeListByIdRespDto;
import com.lyz.ddedss_springboot.dto.resp.QueryKnowledgeListRespDto;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

    /**
     * 查询知识列表
     */
    @GetMapping("/query_knowledge_list")
    public ResultJson<List<QueryKnowledgeListRespDto>> queryKnowledgeList(PageReqDto pageReqDto) {
        return null;
    }

    /**
     * 根据教师id获取发布的知识
     */
    @GetMapping("/query_knowledge_list_by_id")
    public ResultJson<List<QueryKnowledgeListByIdRespDto>> queryKnowledgeListById(PageReqDto pageReqDto) {
        return null;
    }

}
