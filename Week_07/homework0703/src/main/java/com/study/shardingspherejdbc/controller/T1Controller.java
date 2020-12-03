package com.study.shardingspherejdbc.controller;


import com.study.shardingspherejdbc.service.IT1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/t1")
public class T1Controller {

    @Autowired
    private IT1Service service;

    @RequestMapping("/getList")
    public List<Integer> getList(){
        return service.getList();
    }

    @RequestMapping("/add")
    public void add(@RequestParam("id") Integer id){
        service.add(id);
    }
}
