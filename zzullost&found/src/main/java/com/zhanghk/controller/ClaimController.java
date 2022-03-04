package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Claim;
import com.zhanghk.entity.Post;
import com.zhanghk.service.ClaimService;
import com.zhanghk.util.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Claim)表控制层
 *
 * @author makejava
 * @since 2021-06-05 14:09:56
 */
@RestController
@RequestMapping("claim")
public class ClaimController {
    /**
     * 服务对象
     */
    @Resource
    private ClaimService claimService;

    /**
     * 分页查询
     */
    @RequestMapping("/queryAll")
    public JsonObject<Claim> queryAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     Claim claim){
        //创建返回的对象
        JsonObject<Claim> jsonObject = new JsonObject<>();
        //查询结果列表
        PageInfo<Claim> claimPageInfo = claimService.findAll(page,pageSize,claim);
        //填充返回的前端数据对象
        jsonObject.setCode(0);
        jsonObject.setMsg("ok");
        jsonObject.setData(claimPageInfo.getList());
        jsonObject.setCount(claimPageInfo.getTotal());

        return jsonObject;
    }
}