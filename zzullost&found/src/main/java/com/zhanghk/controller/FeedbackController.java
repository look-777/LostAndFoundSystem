package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Feedback;
import com.zhanghk.service.FeedbackService;
import com.zhanghk.util.JWTUtil;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * (Feedback)表控制层
 *
 * @author makejava
 * @since 2021-03-23 09:34:18
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    /**
     * 服务对象
     */
    @Resource
    private FeedbackService feedbackService;

    @RequestMapping("/queryAll")
    public JsonObject<Feedback> queryAll(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         Feedback feedback){
        //创建返回的json对象
        JsonObject<Feedback> jsonObject = new JsonObject<>();
        //查询结果列表
        PageInfo<Feedback> feedbackPageInfo = feedbackService.findAll(page,pageSize,feedback);
        //填充返回的前端数据对象
        jsonObject.setCode(0);
        jsonObject.setMsg("ok");
        jsonObject.setCount(feedbackPageInfo.getTotal());
        jsonObject.setData(feedbackPageInfo.getList());

        return jsonObject;
    }

    /**
     * 删除反馈信息（支持批量和单个删除）
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){//ids为传入的参数  /deleteByIds?id=ids
        //将id字符串（"1,2,3"）转为集合对象
        List<String> idslist = Arrays.asList(ids.split(","));
        // 遍历集合对象，逐个删除
        for (String id : idslist){
            feedbackService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }


    /**
     * 用户端 添加一个反馈
     */
    @RequestMapping("/addfeeback")
    public R addFeedback(@RequestBody Feedback feedback, HttpServletRequest request){

        String token = request.getHeader("token");
        String uname = JWTUtil.getUserName(token);
        feedback.setUname(uname);
        int flag = feedbackService.insert(feedback);
        if (flag>=0){
            return R.ok();
        }
        return R.fail("反馈失败！！");
    }
}