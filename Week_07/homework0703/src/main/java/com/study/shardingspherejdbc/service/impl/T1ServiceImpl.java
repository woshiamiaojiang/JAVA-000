package com.study.shardingspherejdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.shardingspherejdbc.entity.T1;
import com.study.shardingspherejdbc.mapper.T1Mapper;
import com.study.shardingspherejdbc.service.IT1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class T1ServiceImpl extends ServiceImpl<T1Mapper, T1> implements IT1Service {

    @Autowired
    private T1Mapper mapper;

    @Override
    public List<Integer> getList() {
        return mapper.getList();
    }

    @Override
    public void add(Integer id) {
        mapper.add(id);
    }
}
