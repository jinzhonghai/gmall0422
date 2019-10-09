package com.atguigu.gmall0422.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class BaseAttrInfo implements Serializable {
    @Id
    @Column
    //Oracle获取主键自增:GenerationType.AUTO
    // MySQL获取主键自增的注解
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    @Transient //表示在数据库中没有该字段，而是业务需要使用
    private List<BaseAttrValue> attrValueList;


}
