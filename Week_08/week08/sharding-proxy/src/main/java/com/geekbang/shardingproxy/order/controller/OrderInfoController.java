package com.geekbang.shardingproxy.order.controller;

import com.geekbang.shardingproxy.order.bean.OrderInfo;
import com.geekbang.shardingproxy.order.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("orderInfo")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService service;

    @RequestMapping("query")
    public OrderInfo query(@RequestParam("id") Integer id) {
        return service.getById(id);
    }

    @RequestMapping("add")
    public void add(@RequestBody OrderInfo bean) {
        service.save(bean);
    }

    @RequestMapping("update")
    public void update(@RequestBody OrderInfo bean) {
        service.updateById(bean);
    }

    @RequestMapping("del")
    public void delete(@RequestParam("id") Integer id) {
        service.removeById(id);
    }
}
