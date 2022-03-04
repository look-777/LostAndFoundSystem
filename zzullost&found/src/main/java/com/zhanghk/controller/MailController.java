package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Mail;
import com.zhanghk.entity.Post;
import com.zhanghk.entity.User;
import com.zhanghk.service.MailService;
import com.zhanghk.service.PostService;
import com.zhanghk.service.UserService;
import com.zhanghk.util.JWTUtil;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.MailUntil;
import com.zhanghk.util.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 发送邮件服务接口
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailService mailService;

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;


    /**
     * 前台用户触发用以发送邮件服务
     *
     */
    @RequestMapping("/sendMail")
    public void sendMail(@RequestBody Map<String,String> map) throws Exception {
        String postId = "";
        if (map.containsKey("postId")){
            postId = map.get("postId");
        }
        Post post  = postService.queryById(Integer.parseInt(postId));
        String revName = post.getUname();
        User user = userService.findUserByName(revName);
        String revMail = user.getEmail();
        String mailContent = revName+ "同学你好，你发布的标题为：《"+post.getTitle()+"》的启示已经有人认领或找回了，快到系统中查看吧~";
        Integer revId = user.getId();
        Mail mail = new Mail();
        mail.setCtime(new Date());
        mail.setRevName(revName);
        mail.setRevId(revId);
        mail.setTitle("ZZU寻物小帮手");
        mail.setContent(mailContent);
        mailService.insert(mail);
        MailUntil mailUntil =new MailUntil();
        mailUntil.SendMail("3250514239@qq.com",revMail,mailContent);
    }


    /**
     * 后台系统接口 -- 查看所有，支持高级查询
     */
    @RequestMapping("/queryAll")
    public JsonObject<Mail> queryAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     Mail mail){

        //创建返回体
        JsonObject<Mail> jsonObject = new JsonObject<>();
        PageInfo<Mail> pageInfo = mailService.queryAll(page,pageSize,mail);
        jsonObject.setCode(0);
        jsonObject.setMsg("ok");
        jsonObject.setData(pageInfo.getList());
        jsonObject.setCount(pageInfo.getTotal());

        return jsonObject;
    }
    /**
     * 后台系统接口 -- 删除，支持批量删除和单个删除
     */
    @RequestMapping("/deleteByIds")
    public R deleteById(String ids){
        //先将ids转换为集合
        List<String> idlist = Arrays.asList(ids.split(","));

        //循环遍历，逐个删除
        for (String id:
             idlist) {
            mailService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }

    /**
     * 前台 -- 个人中心收件箱接口
     */
    @RequestMapping("/userMail")
    public Map userMail(HttpServletRequest request){
        Map map = new HashMap();

        //可以设置条件为收件人
        String token = request.getHeader("token");
        String RevName = JWTUtil.getUserName(token);
        Mail mail1 = new Mail();
        mail1.setRevName(RevName);


        PageInfo<Mail> mail =  mailService.queryAll(1,50,mail1);
        List<Mail> mailList = mail.getList();
        map.put("mymail",mailList);
        return map;

    }

    /**
     * 用户端删除接口
     */
    @RequestMapping("/deleteMailById")
    public R deleteMailById(@RequestBody Map<String,String> map){
        String mailId = map.get("mailId");
        Boolean flag = mailService.deleteById(Integer.parseInt(mailId));
        if (flag==true){
            return R.ok();
        }
        return R.fail("删除失败");
    }

}
