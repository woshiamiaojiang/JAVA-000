package io.kimmking.dubbo.demo.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class DealA implements Serializable {

    private Long id;

    private Integer dollar;

    private Integer rmb;

    private Integer lokk;

}

