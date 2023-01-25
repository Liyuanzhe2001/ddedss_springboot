package com.lyz.ddedss_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

    /**
     * 查询知识列表
     *
     * @param currentPage 当前页码
     * @param pageSize    每页的大小
     * @return {
     * code - 状态码
     * msg - 信息
     * knowledgeList:[
     * {
     * id - 知识id
     * title - 知识标题
     * }
     * ]
     * totalPage - 总条数
     * }
     */
    @GetMapping("/query_knowledge_list")
    public String queryKnowledgeList(Integer currentPage, Integer pageSize) {
        return null;
    }

    /**
     * 根据教师id获取发布的知识
     *
     * @param currentPage 当前页码
     * @param pageSize    每页的大小
     * @return {
     * code - 状态码
     * msg - 信息
     * knowledgeList:[
     * {
     * id - 知识id
     * title - 知识标题
     * }
     * ]
     * totalPage - 总条数
     * }
     */
    @GetMapping("/query_knowledge_list_by_id")
    public String queryKnowledgeListById(Integer currentPage, Integer pageSize) {
        return null;
    }

}
