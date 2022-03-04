package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Notice;
import com.zhanghk.entity.User;
import com.zhanghk.service.NoticeService;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * (Notice)表控制层
 *
 * @author makejava
 * @since 2021-03-23 11:29:56
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    @RequestMapping("/queryAll")
    public JsonObject<Notice> findAll(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Notice notice){
        //创建返回对象
        JsonObject<Notice> jsonObject = new JsonObject<>();
        //查询公告信息
        PageInfo<Notice> pageInfo = noticeService.queryAll(page,pageSize,notice);

        //设置返回值
        jsonObject.setCode(0);
        jsonObject.setMsg("ok");
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return jsonObject;
    }

    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
        //将id字符串（"1,2,3"）转为集合对象
        List<String> idslist = Arrays.asList(ids.split(","));
        // 遍历集合对象，逐个删除
        for (String id : idslist){
            noticeService.deleteById(Integer.parseInt(id));
        }
        return R.ok();

    }

    @RequestMapping("/add")
    public R add(@RequestBody Notice notice) {
        int flag = noticeService.insert(notice);
        if (flag > 0) {
            return R.ok();
        }
        return R.fail("添加公告失败");
    }
}