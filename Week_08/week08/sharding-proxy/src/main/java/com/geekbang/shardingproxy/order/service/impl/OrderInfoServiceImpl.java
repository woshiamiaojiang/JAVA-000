package com.geekbang.shardingproxy.order.service.impl;

import com.geekbang.shardingproxy.order.bean.OrderInfo;
import com.geekbang.shardingproxy.order.mapper.OrderInfoMapper;
import com.geekbang.shardingproxy.order.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
