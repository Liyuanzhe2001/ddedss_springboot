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
public class GetAllClassCodeInfoReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级名
     */
    private String like;

    /**
     * 当前页大小
     */
    private Long currentPage;

    /**
     * 每页大小
     */
    private Long pageSize;

}
