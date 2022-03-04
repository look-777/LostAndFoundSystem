package com.zhanghk.config;

import com.zhanghk.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowCredentials(true)
                        .allowedOriginPatterns("*")
                        .maxAge(3600);
            }
        };
    }

//    @Bean
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
//        //连接配置
//        registration.addPathPatterns("/**");         //所有路径都被拦截
//        registration.excludePathPatterns(                         //添加不拦截路径
//                "/*/login.html",            //登录
//                "/*/foundhall.html",            //html静态资源
//                "/*/*.js",              //js静态资源
//                "/*/*.css",             //css静态资源
//                "/*/*.woff",
//                "/*/*.ttf",
//                "/*/*.png",
//                "/login/userLogin",
//                "post/queryAll"
//        );
//    }
}
