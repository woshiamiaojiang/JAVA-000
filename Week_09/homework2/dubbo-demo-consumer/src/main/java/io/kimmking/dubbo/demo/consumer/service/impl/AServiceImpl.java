package io.kimmking.dubbo.demo.consumer.service.impl;

import io.kimmking.dubbo.demo.api.IDealAService;
import io.kimmking.dubbo.demo.api.IDealBService;
import io.kimmking.dubbo.demo.consumer.mapper.DealAMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;

import javax.annotation.Resource;

@DubboService(version = "1.0.0")
public class AServiceImpl implements IDealAService {

    @Resource
    private DealAMapper dealaMapper;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private IDealBService bService;

    @Override
    @HmilyTCC(confirmMethod = "confirmDeal", cancelMethod = "cancelDeal")
    public void deal(long id) {
        dealaMapper.updateAccount(id);
        bService.deal(id);
    }

    public void confirm(long id) {
        dealaMapper.updateConfirm(id);
    }

    public void cancel(long id) {
        dealaMapper.updateCancel(id);
    }
}
