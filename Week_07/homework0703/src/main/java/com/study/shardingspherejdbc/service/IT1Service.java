package com.study.shardingspherejdbc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.study.shardingspherejdbc.entity.T1;

import java.util.List;

public interface IT1Service extends IService<T1> {

    public List<Integer> getList();

    public void add(Integer id);
}
