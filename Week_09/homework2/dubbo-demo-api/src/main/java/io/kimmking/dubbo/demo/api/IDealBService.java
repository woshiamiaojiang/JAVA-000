package io.kimmking.dubbo.demo.api;


import org.dromara.hmily.annotation.Hmily;

public interface IDealBService {
    @Hmily
    void deal(long id);
}
