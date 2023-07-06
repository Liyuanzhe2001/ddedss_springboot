package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.exception.ClassNotFoundException;
import com.lyz.ddedss_springboot.mapper.ClassMapper;
import com.lyz.ddedss_springboot.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class_> implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public Integer getIdByName(String className) {
        LambdaQueryWrapper<Class_> lambdaQueryWrapper = new LambdaQueryWrapper<Class_>()
                .select(Class_::getId)
                .eq(Class_::getName, className);
        Class_ class_ = classMapper.selectOne(lambdaQueryWrapper);
        if (class_ == null) {
            throw new ClassNotFoundException();
        }
        return class_.getId();
    }

    @Override
    public List<Class_> getAllClassList() {
        return classMapper.selectList(new LambdaQueryWrapper<Class_>());
    }

    @Override
    public Page<Class_> getAllLikeClassList(String like, Page<Class_> page) {
        Long pageSize = page.getSize();
        Long pageNo = (page.getCurrent() - 1) * page.getSize();
        LambdaQueryWrapper<Class_> wrapper = new LambdaQueryWrapper<Class_>()
                .like(Class_::getName, like);
        Long total = classMapper.selectCount(wrapper);

        wrapper.last("limit " + pageNo + "," + pageSize);

        List<Class_> classList = classMapper.selectList(wrapper);

        Page<Class_> res = new Page<>();
        res.setTotal(total)
                .setRecords(classList);

        return res;
    }

    @Override
    public List<Class_> getClasses(Integer teacherId) {
        return classMapper.getClassNames(teacherId);
    }
}
