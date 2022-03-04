package com.zhanghk.service;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Type;
import com.zhanghk.vo.TypeVo;

import java.util.List;


public interface TypeService {

    /**
     * 分页查询
     */
    PageInfo<Type> findAll(int page,int pageSize,String typeName);

    /**
     * 添加
     */
    int insertType(Type type);

    /**
     * 删除
     */
    int deleteTypeByID(Integer id);

    /**
     * 根据类型统计 数量
     * @return
     */
    List<TypeVo> getCountsByType();

    /**
     * 失物寻物大厅下的类型数量
     * @param flag
     * @return
     */
    List<TypeVo> getCountsByFlag(int flag);


}
