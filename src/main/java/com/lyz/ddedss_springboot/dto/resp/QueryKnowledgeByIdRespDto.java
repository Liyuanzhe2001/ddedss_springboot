package com.lyz.ddedss_springboot.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QueryKnowledgeByIdRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 知识标题
     */
    private String knowledgeTitle;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 标签
     */
    private List<String > tags;

    /**
     * 内容
     */
    private String content;
}
