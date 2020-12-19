package io.kimmking.dubbo.demo.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User implements java.io.Serializable {

    private int id;
    private String name;

}
