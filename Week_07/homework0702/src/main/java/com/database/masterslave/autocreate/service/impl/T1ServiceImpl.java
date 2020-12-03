package com.database.masterslave.autocreate.service.impl;

import com.database.masterslave.autocreate.entity.T1;
import com.database.masterslave.autocreate.mapper.T1Mapper;
import com.database.masterslave.autocreate.service.IT1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.database.masterslave.util.readOnly;
import com.database.masterslave.util.DataSourceNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class T1ServiceImpl extends ServiceImpl<T1Mapper, T1> implements IT1Service {

    @Autowired
    private T1Mapper mapper;

    @readOnly(name = DataSourceNames.SECOND_DATASOURCE)
    @Override
    public List<Integer> getList() {
        return mapper.getList();
    }

    @Override
    public void add(Integer id) {
        mapper.add(id);
    }
}
