package com.zhanghk.dao;

import com.zhanghk.entity.Type;
import com.zhanghk.vo.TypeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("typeDao")
public interface TypeMapper {

    /**
     * 查询 高级查询
     */
    List<Type> queryList(@Param("typeName") String typeName);

    /**
     * 添加
     */
    int insertType(Type type);

    /**
     * 删除 根据id删除
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
