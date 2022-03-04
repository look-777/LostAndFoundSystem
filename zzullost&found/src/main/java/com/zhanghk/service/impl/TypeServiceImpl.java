package com.zhanghk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghk.dao.TypeMapper;
import com.zhanghk.entity.Type;
import com.zhanghk.service.TypeService;
import com.zhanghk.vo.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeDao;

    @Override
    public PageInfo<Type> findAll(int page, int pageSize, String typeName) {
        PageHelper.startPage(page,pageSize);
        List<Type> list = typeDao.queryList(typeName);
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int insertType(Type type) {
        return typeDao.insertType(type);
    }

    @Override
    public int deleteTypeByID(Integer id) {
        return typeDao.deleteTypeByID(id);
    }

    @Override
    public List<TypeVo> getCountsByType() {
        return typeDao.getCountsByType();
    }

    @Override
    public List<TypeVo> getCountsByFlag(int flag) {
        return typeDao.getCountsByFlag(flag);
    }
}
