package com.atguigu.gmall0422.service;

import com.atguigu.gmall0422.bean.*;

import java.util.List;

public interface ManageService {
    //获取一级分类
    List<BaseCatalog1> getCatalog1();

    //获取二级分类
    List<BaseCatalog2>getCatalog2(String catalog1Id);

    //获取三级分类
    List<BaseCatalog3>getCatalog3(String catalog2Id);
    //根据三级分类ID获取属性列表
    List<BaseAttrInfo> attrInfoList(String catalog3Id);
    /**
     * 添加属性 saveAttrInfo
     * 括号中的参数实际就是接收页面传递的数据
     * VO 接收表单数据，页面数据展示
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
    //修改属性  根据属性ID回显数据
    List<BaseAttrValue> getAttrValueList(String attrId);
    //完善修改功能（业务角度）根据平台属性ID获取平台属性对象
    BaseAttrInfo getBaseAttrInfo(String attrId);
    //根据三级分类ID获取商品信息列表
    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

    // 查询基本销售属性表
    List<BaseSaleAttr> getBaseSaleAttrList();

    //保存Spu信息
    void saveSpuInfo(SpuInfo spuInfo);

}
