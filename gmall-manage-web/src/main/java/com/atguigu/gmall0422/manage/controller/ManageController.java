package com.atguigu.gmall0422.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0422.bean.*;
import com.atguigu.gmall0422.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;

    //获取一级分类
    @RequestMapping("getCatalog1")
    public List<BaseCatalog1>getCatalog1(){
        return manageService.getCatalog1();
    }
    //http://localhost:8082/getCatalog2?catalog1Id=3
    //根据一级分类ID获取二级分类
    @RequestMapping("getCatalog2")
    public List<BaseCatalog2>getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);
    }
    //http://localhost:8082/getCatalog3?catalog2Id=16
    //根据二级分类ID获取三级分类
    @RequestMapping("getCatalog3")
    public List<BaseCatalog3>getCatalog3(String catalog2Id ){
        return manageService.getCatalog3(catalog2Id);
    }
    //http://localhost:8082/attrInfoList?catalog3Id=86
    //根据三级分类ID获取属性列表
    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo>attrInfoList(String catalog3Id){
        return manageService.attrInfoList(catalog3Id);
    }
    //http://localhost:8082/saveAttrInfo
    //添加平台属性

    /**
     * baseAttrInfo从前台页面获取数据，封装到实体类中
     * @RequestBody 把前台数据转换成java对象格式
     * @ResponseBody 把后台数据转换成json格式传递给前台
     * @param baseAttrInfo
     */
    @RequestMapping("saveAttrInfo")
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
       manageService.saveAttrInfo( baseAttrInfo);
    }
    /**
     * http://localhost:8082/getAttrValueList?attrId=89
     * 修改属性
     */

//    @RequestMapping("getAttrValueList")
//    public List<BaseAttrValue>getAttrValueList(String attrId){
//        return manageService.getAttrValueList(attrId);
//    }
    //完善修改功能（业务角度）
    @RequestMapping("getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(String attrId){
        BaseAttrInfo baseAttrInfo=manageService.getBaseAttrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
    }
    /**
     * http://localhost:8082/spuList?catalog3Id=86
     *根据三级分类ID获取商品信息列表
     */
    @RequestMapping("spuList")
    public List<SpuInfo> spuList(SpuInfo spuInfo){
        return manageService.getSpuInfoList(spuInfo);
    }
    /**
     * http://localhost:8082/baseSaleAttrList
     * 销售属性列表
     */
    @RequestMapping("baseSaleAttrList")
    public List<BaseSaleAttr>baseSaleAttrList(){
        return manageService.getBaseSaleAttrList();
    }

    /** http://localhost:8082/saveSpuInfo
     * 添加商品信息
     */
    @RequestMapping("saveSpuInfo")
    public void saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
    }
}
