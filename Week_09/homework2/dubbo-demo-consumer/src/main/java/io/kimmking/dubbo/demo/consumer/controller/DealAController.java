package io.kimmking.dubbo.demo.consumer.controller;

import io.kimmking.dubbo.demo.api.IDealAService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dealA")
public class DealAController {

    @Resource
    private IDealAService dealAService;

    @GetMapping("/deal/{id}")
    public String deal(@PathVariable("id") Long id) {
        dealAService.deal(id);
        return "success";
    }

}
