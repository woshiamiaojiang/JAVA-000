package com.database.masterslave.autocreate.mapper;

import com.database.masterslave.autocreate.entity.T1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface T1Mapper extends BaseMapper<T1> {

    void add(Integer id);

    List<Integer> getList();
}
