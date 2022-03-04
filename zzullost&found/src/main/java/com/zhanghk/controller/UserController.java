package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.Post;
import com.zhanghk.entity.Type;
import com.zhanghk.entity.User;
import com.zhanghk.service.PostService;
import com.zhanghk.service.UserService;
import com.zhanghk.util.JWTUtil;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.sax.SAXResult;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-03-17 09:53:43
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;


    /**
     * 分页查询 高级查询
     */

    @RequestMapping("/queryAll")
    public JsonObject<User> queryAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     User user){
        //创建返回的对象
        JsonObject object = new JsonObject();

        //查询类型列表信息
        PageInfo<User> pageInfo = userService.findAll(page,pageSize,user);
        //填充返回的前端数据对象
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());

        return object;

    }


    @RequestMapping("/add")
    public R add(@RequestBody User user) {
        int flag = userService.insert(user);
        if (flag > 0) {
            return R.ok();
        }
        return R.fail("添加用户失败");
    }

    /**
     * 删除（支持单个删除和批量删除）
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){  //ids为传入的参数  /deleteByIds?id=ids
        //将id字符串（"1,2,3"）转为集合对象
        List<String> idslist = Arrays.asList(ids.split(","));
        // 遍历集合对象，逐个删除
        for (String id : idslist){
            userService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }


    /**
     * 后台更新用户密码的操作
     * @param map
     * @return
     */
    @RequestMapping("/update")
    public R updataPwd(@RequestBody Map<String,String> map){
        //获取数据
        String id = map.get("id");
        String oldpwd = map.get("oldpwd");
        String newpwd = map.get("newpwd");
        User user = userService.queryById(Integer.parseInt(id));

        if (user.getPassword().equals(oldpwd)){
            //重新设置密码
            User u = new User();
            u.setId(Integer.parseInt(id));
            u.setPassword(newpwd);
            userService.update(u);
            return R.ok();
        }else {
            return R.fail("你输入的旧密码不正确...");
        }
    }

    /**
     * 主要用来渲染个人中心，我的发布、个人信息等。 浏览最新评论
     * @return
     */
    @RequestMapping("/userCenter")
    public Map userCenter(@RequestBody Map<String,String> map,HttpServletRequest request){
        Map m = new HashMap();

        Post post = new Post();
        //从token中获取登录用户信息。
        String token = request.getHeader("token");
        System.out.println(JWTUtil.getUserName(token));
        String username = JWTUtil.getUserName(token);
        post.setUname(username);
        if(map.containsKey("title")){
            String title = map.get("title");
            post.setTitle(title);
        }

        //从登录用户的token中获取用户信息。
        User user = JWTUtil.getUser(token);

        //首先查询我的发布的所有启示
        PageInfo<Post> pageInfo = postService.findAll(1,50,post);
        List<Post> mypost = pageInfo.getList();
        m.put("mypost",mypost);

        //然后查询我的个人信息
        User myInfo = userService.queryById(user.getId());
        m.put("myInfo",myInfo);

        return m;
    }

    /**
     * 个人信息编辑 数据回显接口
     * @param map
     * @return
     */
    @RequestMapping("/queryInfoById")
    public Map queryInfoById(@RequestBody Map<String,String> map){
        Map  m = new HashMap<>();
        String userId = "45";
        if (map.containsKey("userId")){
            userId = map.get("userId");
        }
        User user = userService.queryById(Integer.parseInt(userId));
        m.put("myInfo",user);
        return m;
    }


    /**
     * 编辑用户个人信息
     * @param map
     * @return
     */
    @RequestMapping("/editInfo")
    public R editInfo(@RequestBody Map<String,String> map){
        String userId = "";
        String username  = "";
        String mail = "";
        String personalSay = "";
        String sex = "";
        String age = "";

        if (map.containsKey("username")){
            username = map.get("username");
        }
        if (map.containsKey("mail")){
            mail = map.get("mail");
        }
        if (map.containsKey("personalSay")){
            personalSay = map.get("personalSay");
        }
        if (map.containsKey("sex")){
            sex = map.get("sex");
        }
        if (map.containsKey("age")){
            age = map.get("age");
        }
        if (map.containsKey("userId")){
            userId = map.get("userId");
        }
        User user = userService.queryById(Integer.parseInt(userId));
        user.setUsername(username);
        user.setEmail(mail);
        user.setAge(Integer.parseInt(age));
        user.setPersonalSay(personalSay);
        user.setSex(sex);
        userService.update(user);
        return R.ok();
    }


}