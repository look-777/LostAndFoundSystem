package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Type;
import com.zhanghk.service.TypeService;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 类型查询接口
     */
    @RequestMapping("/queryAll")
    public JsonObject queryAll(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               String typeName){
        //创建返回的对象
        JsonObject object = new JsonObject();
        //查询类型列表信息
        PageInfo<Type> pageInfo = typeService.findAll(page,pageSize,typeName);
        //填充返回的前端数据对象
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());

        return object;
    }

    /**
     * 添加类型接口
     */
    @RequestMapping("/add")
    public R add(@RequestBody Type type){
        int flag = typeService.insertType(type);
        if (flag>0){
            return R.ok();
        }
        return R.fail("添加类型失败");
    }
    /**
     * 类型删除接口，支持单个删除和批量删除
     */
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){//批量删除，前台可以传入“1，2，3，4”这样的id组
        //将传入的id组字符串转为集合对象
        List<String> list =  Arrays.asList(ids.split(","));
        //遍历
        for (String id:list) {
            typeService.deleteTypeByID(Integer.parseInt(id));
        }
        return R.ok();
    }

    /**
     * 启示发布页面，物品类型初始化渲染。
     * @return
     */
    @RequestMapping("/queryTypeList")
    public List<Type> queryTypeList(){
        PageInfo<Type> typePageInfo = typeService.findAll(1,15,null);
        return typePageInfo.getList();
    }
}
