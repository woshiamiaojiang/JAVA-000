package com.geekbang.shardingproxy.order.mapper;

import com.geekbang.shardingproxy.order.bean.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
}
