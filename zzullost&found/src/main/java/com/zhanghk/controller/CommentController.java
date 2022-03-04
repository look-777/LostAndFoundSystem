package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Comment;
import com.zhanghk.entity.Feedback;
import com.zhanghk.service.CommentService;
import com.zhanghk.util.JWTUtil;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2021-03-23 21:00:12
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 分页查询
     */
    @RequestMapping("/queryAll")
    public JsonObject<Comment> queryAll(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        Comment comment) {

        //创建返回的json对象
        JsonObject<Comment> jsonObject = new JsonObject<>();
        //查询结果列表
        PageInfo<Comment> commentPageInfo = commentService.queryAll(page,pageSize,comment);
        //填充返回的前端数据对象
        jsonObject.setCode(0);
        jsonObject.setMsg("ok");
        jsonObject.setCount(commentPageInfo.getTotal());
        jsonObject.setData(commentPageInfo.getList());
        return jsonObject;
    }

    /**
     * 删除功能的实现，支持单个删除和批量删除
     */
    @RequestMapping("/deleteByIds")
    public R deleteCommentByIds(String ids){
        //将id字符串（"1,2,3"）转为集合对象
        List<String> idsList = Arrays.asList(ids.split(","));
        // 遍历集合对象，逐个删除
        for (String id: idsList) {
            commentService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }

    /**
     * 用户端 登录用户 评论回复功能
     */
    @RequestMapping("/addComment")
    public R addComment(@RequestBody Map<String,String> map, HttpServletRequest request){
        String postId = "";
        String commentContent = "";
        Integer secretOption = 0;
        if (map.containsKey("postId")){
            postId = map.get("postId");
        }
        if (map.containsKey("comment")){
            commentContent = map.get("comment");
        }
        if (map.containsKey("secretOption")){
            secretOption = Integer.valueOf(map.get("secretOption"));
        }
        if (secretOption==0){//不匿名,从token中得到用户
            String token = request.getHeader("token");
            String uname = JWTUtil.getUserName(token);
            Comment comment = new Comment();
            comment.setUname(uname);
            comment.setPostId(Integer.parseInt(postId));
            comment.setContent(commentContent);
            commentService.insert(comment);
            return R.ok();
        }else {
            Comment comment = new Comment();
            comment.setUname("匿名用户");
            comment.setPostId(Integer.parseInt(postId));
            comment.setContent(commentContent);
            commentService.insert(comment);
            return R.ok();
        }
    }

}