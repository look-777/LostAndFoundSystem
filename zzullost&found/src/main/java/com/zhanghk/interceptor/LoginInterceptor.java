package com.zhanghk.interceptor;

import com.zhanghk.entity.User;
import com.zhanghk.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {


        //获取token
        String token = request.getHeader("token");

        //解决跨域与拦截器冲突
        if ("OPTIONS".equals(request.getMethod().toUpperCase())){
            return true;
        }

        if (token!=null){
            //获取用户id
            Integer userId = JWTUtil.getUserId(token);
            String userName = JWTUtil.getUserName(token);
            //把上面用户信息塞入对象
            User user = new User();
            user.setId(userId);
            user.setUsername(userName);
            //生成新的token
            String newToken = JWTUtil.creatJsonWebToken(user);
            response.setHeader("token",newToken);
            response.setHeader("Access-control-Expose-Header","token");
            request.setAttribute("user",user);
            return true;
        }
        return false;
    }
}
