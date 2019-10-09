package com.atguigu.gmall0422.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall0422.bean.*;
import com.atguigu.gmall0422.manage.mapper.*;
import com.atguigu.gmall0422.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ManageserviceImpl implements ManageService {
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    //获取一级分类
    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }
    //根据一级分类ID获取二级分类
    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        //select * from base_catalog2 where catalog1_id=catalog1Id
        BaseCatalog2 baseCatalog2=new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }
    //根据二级分类ID获取三级分类
    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3=new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        return baseCatalog3Mapper.select(baseCatalog3);
    }
    //根据三级分类ID获取属性列表
    @Override
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo=new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return baseAttrInfoMapper.select(baseAttrInfo);
    }
    
    //添加平台属性
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if(baseAttrInfo.getId()!=null&&baseAttrInfo.getId().length()>0){
            //baseAttrInfo
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{
            //baseAttrInfo
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        //baseAttrValue
        /**
         * 如果是修改 先将BaseAttrValue中的数据删除，在新增
         * delete from baseAttrValue where attrId=baseAttrInfo.id
         */
        BaseAttrValue baseAttrValueDel=new BaseAttrValue();
        baseAttrValueDel.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValueDel);

        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if(attrValueList!=null&&attrValueList.size()>0){
            for (int i = 0; i < attrValueList.size(); i++) {
                //baseAttrInfo.getId()要想获取id，则在baseAttrInfo实体类中必须添加获取主键自增的注解
                attrValueList.get(i).setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(attrValueList.get(i));
            }
        }
    }

    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        BaseAttrValue baseAttrValue=new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        return  baseAttrValueMapper.select(baseAttrValue);

    }

    //完善修改功能（业务角度）根据平台属性ID获取平台属性对象
    @Override
    public BaseAttrInfo getBaseAttrInfo(String attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        baseAttrInfo.setAttrValueList(getAttrValueList(attrId));
        return baseAttrInfo;
    }
    //根据三级分类ID获取商品信息列表
    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {

        return spuInfoMapper.select(spuInfo);
    }
}
