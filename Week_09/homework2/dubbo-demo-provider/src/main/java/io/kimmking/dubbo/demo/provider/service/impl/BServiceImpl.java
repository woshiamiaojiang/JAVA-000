package io.kimmking.dubbo.demo.provider.service.impl;

import io.kimmking.dubbo.demo.api.IDealBService;
import io.kimmking.dubbo.demo.provider.mapper.DealBMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;

import javax.annotation.Resource;

@DubboService(version = "1.0.0")
public class BServiceImpl implements IDealBService {

    @Resource
    private DealBMapper bMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmDeal", cancelMethod = "cancelDeal")
    public void deal(long id) {
        bMapper.updateAccount(id);
        throw new RuntimeException();
    }

    public void confirmDeal(long id) {
        bMapper.updateConfirm(id);
    }

    public void cancelDeal(long id) {
        bMapper.updateCancel(id);
    }
}
