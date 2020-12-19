package io.kimmking.dubbo.demo.api;

import org.dromara.hmily.annotation.Hmily;

public interface IDealAService {
    @Hmily
    void deal(long id);
}
