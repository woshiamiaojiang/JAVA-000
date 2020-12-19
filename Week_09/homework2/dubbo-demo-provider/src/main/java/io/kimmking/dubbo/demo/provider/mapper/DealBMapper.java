package io.kimmking.dubbo.demo.provider.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DealBMapper {
    void updateAccount(long id);

    void updateConfirm(long id);

    void updateCancel(long id);
}
