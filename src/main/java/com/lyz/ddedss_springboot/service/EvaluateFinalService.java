package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.EvaluateFinal;

import java.util.List;

public interface EvaluateFinalService extends IService<EvaluateFinal> {

    /**
     * 判断evaluate_id中存在好评
     */
    public boolean haveGood(List<Integer> ids);

    /**
     * 判断evaluate_id中存在差评
     */
    public boolean haveBad(List<Integer> ids);

    /**
     * 根据evaluate ids 获取这几评价的结果总和
     */
    public List<Integer> getResult(List<Integer> ids);

}
