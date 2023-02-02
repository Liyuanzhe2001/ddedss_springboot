package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.EvaluateFinal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaluateFinalMapper extends BaseMapper<EvaluateFinal> {

    /**
     * 根据evaluate ids 获取这几评价的结果总和
     * list[0] 优
     * list[1] 差
     */
    public List<Integer> getResult(List<Integer> ids);

}
