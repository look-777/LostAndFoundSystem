package com.zhanghk.controller;

import com.zhanghk.service.PostService;
import com.zhanghk.service.TypeService;
import com.zhanghk.service.UserService;
import com.zhanghk.util.JsonObject;
import com.zhanghk.vo.TypeVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataCountsController {

    @Resource
    private PostService postService;
    @Resource
    private UserService userService;
    @Resource
    private TypeService typeService;

    @RequestMapping("/datacounts")
    public Map dataCounts(){

        /**
         * 注册用户数、启示数量、找回数量、丢失数量、召回率(找回/丢失)、归档（按照月份统计数量）,
         * 每个分类数量，词云渲染（拟，还没实现）
         */

        Map m = new HashMap();

        //统计找回数量
        int swzl = postService.getCountsByFlagAndStatus(1,0);//失物招领 完成 1 0
        int xuqs = postService.getCountsByFlagAndStatus(0,0);//寻物启事 完成 0 0
        m.put("swzl",swzl);
        m.put("xuqs",xuqs);
        int found = swzl+xuqs;
        //统计丢失数量
        int wswzl = postService.getCountsByFlagAndStatus(1,1);//失物招领 未完成 1 1
        int wxwqs = postService.getCountsByFlagAndStatus(0,1);//寻物启事 未完成 0 1
        m.put("wswzl",wswzl);
        m.put("wxwqs",wxwqs);
        int loss = wswzl+wxwqs;
        double rate = (double)found/loss;

        m.put("found",found);
        m.put("loss",loss);
        DecimalFormat df = new DecimalFormat("#.00");
        m.put("rate",df.format(rate*100));


        //统计用户注册数
        int userNum = userService.getUserNum();
        m.put("userNum",userNum);


        //按照月份统计
        //2021年1月
        int post_01 = postService.getCountsByMon("2021-01-01 00:00:00","2021-01-31 23:59:59");
        //2021年2月
        int post_02 = postService.getCountsByMon("2021-02-01 00:00:00","2021-02-28 23:59:59");
        //2021年3月
        int post_03 = postService.getCountsByMon("2021-03-01 00:00:00","2021-03-31 23:59:59");
        //2021年4月
        int post_04 = postService.getCountsByMon("2021-04-01 00:00:00","2021-04-30 23:59:59");
        //2021年5月
        int post_05 = postService.getCountsByMon("2021-05-01 00:00:00","2021-05-31 23:59:59");
        //2021年6月
        int post_06 = postService.getCountsByMon("2021-06-01 00:00:00","2021-06-30 23:59:59");
        m.put("post_01",post_01);
        m.put("post_02",post_02);
        m.put("post_03",post_03);
        m.put("post_04",post_04);
        m.put("post_05",post_05);
        m.put("post_06",post_06);

        //丢失物品分类信息
        //查询分类信息 并统计分类相关的post数量
        List<TypeVo> typeVoList = typeService.getCountsByType();
        List<String> nameList = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (TypeVo typevo:
             typeVoList) {
            nameList.add(typevo.getTypeName());
            counts.add(typevo.getCounts());
        }
        m.put("nameList",nameList);
        m.put("counts",counts);

        //词云

        return m;
    }
}
