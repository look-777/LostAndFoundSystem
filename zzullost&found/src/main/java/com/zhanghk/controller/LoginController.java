package com.zhanghk.controller;

import com.zhanghk.entity.User;
import com.zhanghk.service.UserService;
import com.zhanghk.util.JWTUtil;
import com.zhanghk.util.MD5Util;
import com.zhanghk.util.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;


    /**
     * 用户注册
     * @param map
     * @return
     */
    @RequestMapping("/register")
    public Map register(@RequestBody Map<String,String> map){
        Map map1 = new HashMap();
        String username = "";
        String password = "";
        if (map.containsKey("username")){
            username = map.get("username");
            System.out.println(username);
        }
        if (map.containsKey("password")){
            password = map.get("password");
            System.out.println(password);
        }
        //先判断一下，看看用户表里面是否有用户名重复的
        if (userService.findUserByName(username)!=null){
            map1.put("message","该用户名已经被占用，请重新注册！");
            map1.put("code",500);
            return map1;
        }
        User user = new User();
        user.setUsername(username);
        String passwordMD5 = MD5Util.string2MD5(password);
        user.setPassword(passwordMD5);
        user.setType(0);
        int flag = userService.insert(user);
        if (flag>0){
            map1.put("message","恭喜您，注册成功，关闭窗口返回至登录页面");
            map1.put("code",200);
            return map1;
        }else {
            map1.put("message","抱歉注册失败，请重试");
            map1.put("code",500);
            return map1;
        }
    }

    @RequestMapping("/userLogin")
    public Map userLogin(@RequestBody Map<String,String> map){
        Map map1 = new HashMap();
        String username = "";
        String password = "";
        if (map.containsKey("username")){
            username = map.get("username");
        }
        if (map.containsKey("password")){
            password = map.get("password");
        }
        User user = userService.findUserByName(username);
        if (user==null){
            map1.put("code",501);//说明用户还没有进行注册
            return map1;


        }else if (!user.getPassword().equals(MD5Util.string2MD5(password))){
            map1.put("code",500);//密码不正确
            return map1;
        }else{
            map1.put("code",200);//登陆成功
            String token = JWTUtil.creatJsonWebToken(user);
//            System.out.println(token);
            map1.put("token",token);
            map1.put("username",user.getUsername());
            map1.put("type",user.getType());
            return map1;
        }
    }


}
