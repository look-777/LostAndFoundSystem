package com.zhanghk.util;

import com.zhanghk.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.models.auth.In;

import java.util.Date;

public class JWTUtil {
    private static Long expireTime = 1000L*60*60;
    //密钥
    private static String key="zhanghaokun";

    /**
     * 生成Token
     */
    public static String creatJsonWebToken(User user){
        if (user==null || user.getId()==null || user.getUsername()==null){
            return null;
        }
        String token = Jwts.builder()
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expireTime)) //失效时间
                .signWith(SignatureAlgorithm.HS256,key).compact();

        return token;
    }

    public static Claims checkJWT(String token){
        try {

            final Claims claims = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static Integer getUserId(String token){
        Claims claims = JWTUtil.checkJWT(token);
        Integer id = (Integer) claims.get("id");
        return id;
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUserName(String token){
        Claims claims = JWTUtil.checkJWT(token);
        String username = (String) claims.get("username");
        return username;
    }

    /**
     * 通过token获取用户对象
     */
    public static User getUser(String token){
        User user = new User();
        Integer userId = getUserId(token);
        String username = getUserName(token);
        if (userId!=null && username!=null){
            user.setUsername(username);
            user.setId(userId);
        }
        return user;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(10);
        user.setUsername("张浩堃");
        String token = creatJsonWebToken(user);
        System.out.println(token);

        String username = JWTUtil.getUserName(token);
        System.out.println(username);
        Integer id = JWTUtil.getUserId(token);
        System.out.println(id);


    }
}
