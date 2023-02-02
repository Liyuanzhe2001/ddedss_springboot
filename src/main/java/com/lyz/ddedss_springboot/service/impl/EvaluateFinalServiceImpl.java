package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.EvaluateFinal;
import com.lyz.ddedss_springboot.mapper.EvaluateFinalMapper;
import com.lyz.ddedss_springboot.service.EvaluateFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateFinalServiceImpl extends ServiceImpl<EvaluateFinalMapper, EvaluateFinal> implements EvaluateFinalService {

    @Autowired
    private EvaluateFinalMapper evaluateFinalMapper;

    @Override
    public boolean haveGood(List<Integer> ids) {
        LambdaQueryWrapper<EvaluateFinal> lambdaQueryWrapper = new LambdaQueryWrapper<EvaluateFinal>()
                .eq(EvaluateFinal::getFinal_, 1)
                .in(EvaluateFinal::getEvaluateId, ids);
        return evaluateFinalMapper.selectList(lambdaQueryWrapper).size() != 0;
    }

    @Override
    public boolean haveBad(List<Integer> ids) {
        LambdaQueryWrapper<EvaluateFinal> lambdaQueryWrapper = new LambdaQueryWrapper<EvaluateFinal>()
                .eq(EvaluateFinal::getFinal_, 0)
                .in(EvaluateFinal::getEvaluateId, ids);
        return evaluateFinalMapper.selectList(lambdaQueryWrapper).size() != 0;
    }

    @Override
    public List<Integer> getResult(List<Integer> ids) {

        List<Integer> result = evaluateFinalMapper.getResult(ids);

        return result;
    }
}
