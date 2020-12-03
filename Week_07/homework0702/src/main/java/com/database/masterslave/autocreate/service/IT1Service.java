package com.database.masterslave.autocreate.service;

import com.database.masterslave.autocreate.entity.T1;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IT1Service extends IService<T1> {

    public List<Integer> getList();

    public void add(Integer id);
}
