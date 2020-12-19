package io.kimmking.dubbo.demo.consumer.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DealAMapper {

    void updateAccount(long id);

    void updateConfirm(long id);

    void updateCancel(long id);
}
