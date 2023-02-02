package com.lyz.ddedss_springboot.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QueryAllUserListReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模糊查询学号/工号 姓名
     */
    private String searchInput;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 每页大小
     */
    private Integer pageSize;
}
