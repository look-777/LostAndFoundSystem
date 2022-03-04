package com.zhanghk.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghk.entity.*;
import com.zhanghk.service.CommentService;
import com.zhanghk.service.NoticeService;
import com.zhanghk.service.PostService;
import com.zhanghk.service.TypeService;
import com.zhanghk.util.JWTUtil;
import com.zhanghk.util.JsonObject;
import com.zhanghk.util.R;
import com.zhanghk.vo.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * (Post)表控制层
 *
 * @author makejava
 * @since 2021-03-22 10:19:07
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostService postService;
    @Resource
    private CommentService commentService;
    @Autowired
    private TypeService typeService;
    @Resource
    private NoticeService noticeService;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param post
     * @return
     */
    @RequestMapping("/queryAll")
    public JsonObject<Post> queryAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     Post post){
        //创建返回的对象
        JsonObject<Post> jsonObject = new JsonObject<>();
        //查询结果列表
        PageInfo<Post> postPageInfo = postService.findAll(page,pageSize,post);
        //填充返回的前端数据对象
        jsonObject.setCode(0);
        jsonObject.setMsg("ok");
        jsonObject.setData(postPageInfo.getList());
        jsonObject.setCount(postPageInfo.getTotal());

        return jsonObject;
    }

    /**
     * 后台删除
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){  //ids为传入的参数  /deleteByIds?id=ids
        //将id字符串（"1,2,3"）转为集合对象
        List<String> idslist = Arrays.asList(ids.split(","));
        // 遍历集合对象，逐个删除
        for (String id : idslist){
            postService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }

    /**
     * 修改失物信息
     * @param post
     * @return
     */
    @RequestMapping("/update")
    public R updatePost(@RequestBody Post post){
        Post post1 =  postService.update(post);
        if (post1!=null){
            return R.ok();
        }
        return R.fail("修改失败");
    }



    /**
     * 用来渲染主页的功能 暂时没用
     */


    /**
     * 用以渲染失物大厅
     */
    @RequestMapping("/queryLossList")
    public Map queryLossList(@RequestBody Map<String,String>map){
        //创建map对象 用以返回前端
        Map m = new HashMap();
        Post post = new Post();
        if(map.containsKey("typeName")){
            String typeName = map.get("typeName");
            post.setTypeName(typeName);
        }
        if(map.containsKey("title")){
            String title = map.get("title");
            post.setTitle(title);
        }
        if(map.containsKey("address")){
            String address = map.get("address");
            post.setAddress(address);
        }
        if(map.containsKey("content")){
            String content = map.get("content");
            post.setContent(content);
        }
        //失物的flag为1
        post.setFlag(1);
        //从post列表查询为失物的帖子
        PageInfo<Post> postPageInfo = postService.findAll(1,100,post);
        List<Post> postList = postPageInfo.getList();
        //遍历列表 放入评论数
        for (Post p:
                postList) {
            //获取id
            Integer id = p.getId();
            int nums = commentService.getCommentCounts(id);
            p.setCounts(nums);
        }


        //将结果返回前端
        m.put("postlist",postList);
        //查询分类信息 并统计分类相关的post数量
        List<TypeVo> typeVoList = typeService.getCountsByFlag(1);
        m.put("typeVoList",typeVoList);
        //查询公告信息 （查询最新的）
        Notice newNotice = noticeService.queryByNewTime();
        m.put("newNotice",newNotice);
        //查询最新评论
        List<Comment> comments = commentService.queryByNewTime();
        m.put("comments",comments);
        //根据状态 查询具体的记录数
        //失物招领 完成 1 0
        int swzl = postService.getCountsByFlagAndStatus(1,0);
        //失物招领 未完成 1 1
        int wswzl = postService.getCountsByFlagAndStatus(1,1);
        //寻物启事 完成 0 0
        int xuqs = postService.getCountsByFlagAndStatus(0,0);
        //寻物启事 未完成 0 1
        int wxwqs = postService.getCountsByFlagAndStatus(0,1);
        m.put("swzl",swzl);
        m.put("wswzl",wswzl);
        m.put("xuqs",xuqs);
        m.put("wxwqs",wxwqs);



        return m;
    }





    /**
     * 用以渲染寻物大厅
     */
    @RequestMapping("/queryFoundList")
    public Map queryFoundList(@RequestBody Map<String,String> map){
        Map m = new HashMap();
        Post post = new Post();
        post.setFlag(0);

        if (map.containsKey("typeName")){
            String typeName = map.get("typeName");
            post.setTypeName(typeName);
        }
        if(map.containsKey("title")){
            String title = map.get("title");
            post.setTitle(title);
        }
        if(map.containsKey("address")){
            String address = map.get("address");
            post.setAddress(address);
        }
        if(map.containsKey("content")){
            String content = map.get("content");
            post.setContent(content);
        }


        PageInfo<Post> postPageInfo = postService.findAll(1,100,post);
        List<Post> postList = postPageInfo.getList();
        //遍历列表 放入评论数
        for (Post p:
                postList) {
            //获取id
            Integer id = p.getId();
            int nums = commentService.getCommentCounts(id);
            p.setCounts(nums);
        }
        m.put("postlist",postList);
        //查询分类信息 并统计分类相关的post数量
        List<TypeVo> typeVoList = typeService.getCountsByFlag(0);
        m.put("typeVoList",typeVoList);
        //查询公告信息 （查询最新的）
        Notice newNotice = noticeService.queryByNewTime();
        m.put("newNotice",newNotice);
        //查询最新评论
        List<Comment> comments = commentService.queryByNewTime();
        m.put("comments",comments);
        //根据状态 查询具体的记录数
        //失物招领 完成 1 0
        int swzl = postService.getCountsByFlagAndStatus(1,0);
        //失物招领 未完成 1 1
        int wswzl = postService.getCountsByFlagAndStatus(1,1);
        //寻物启事 完成 0 0
        int xuqs = postService.getCountsByFlagAndStatus(0,0);
        //寻物启事 未完成 0 1
        int wxwqs = postService.getCountsByFlagAndStatus(0,1);
        m.put("swzl",swzl);
        m.put("wswzl",wswzl);
        m.put("xuqs",xuqs);
        m.put("wxwqs",wxwqs);

        return m;
    }


    /**
     * 渲染启示的详情信息
     */
    @RequestMapping("/queryCommentList")
    public Post queryCommentList(@RequestBody Map<String,String> map){
//    public Post queryCommentList(){
        //创建map对象 用以返回前端
        Map m = new HashMap();
        String postId = "";
        if (map.containsKey("postId")){
            postId = map.get("postId");
        }
        /**
         * 根据id查询启示详情   queryById 找xml文件，里面封装了返回体
         * 1.根据id查询post的详情信息
         * 2.查询当前启示发布者的个人信息
         * 3.查询当前启示下的评论信息
         */
        Post post = postService.queryById(Integer.parseInt(postId));
        //点击“详情后”，浏览次数要加1
        //修改浏览次数
        int counts = post.getViewCount();
        post.setViewCount(counts+1);
        postService.update(post);

        return post;
    }

    //用户推荐
    @RequestMapping("/recommend")
    public Map recommend(@RequestBody Map<String,String> map){

        Map m = new HashMap();
        String postId = "";
        if (map.containsKey("postId")){
            postId = map.get("postId");
        }
        Post post = postService.queryById(Integer.parseInt(postId));
        String title = post.getTitle();
        if (title.contains("书")){
            title = "书";
        }
        if (title.contains("卡")){
            title = "卡";
        }
        if (title.contains("文件")){
            title = "文件";
        }
        if (title.contains("手机")){
            title = "手机";
        }
        if (title.contains("盘")){
            title = "盘";
        }
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setTitle(title);
        List<Post> postList = postService.recommend(post1);
        m.put("remlist",postList);
        return m;
    }

    /**
     * 客户端发布一条启示的接口
     *上传的图片单独处理
     * MultipartFile  这个类一般是用来接受前台传过来的文件(处理图片)
     */
    @RequestMapping("/addPost")
    public R addPost(@RequestPart("post") Post post,
                     @RequestParam("upload") MultipartFile upload, HttpServletRequest request) throws IOException {
        //设置路径信息
        String path = "d://images/";
        //判断是否存在这个目录,如果不存在则创建
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        String fileName = upload.getOriginalFilename();//得到文件名
        //防止上传的图片文件名重复，前面加个随机生成的uuid
        fileName = UUID.randomUUID().toString().replace("-","")+"_"+fileName;
        upload.transferTo(new File(path,fileName));//将文件上传至path处

        String token = request.getHeader("token");
        String uname = JWTUtil.getUserName(token);
        post.setUname(uname);
        post.setPicture("/images/"+fileName);
        post.setCtime(new Date());
        post.setViewCount(1);
        post.setStatus(1);//默认进行中
        //后期添加用户名

        int num = postService.insert(post);
        if (num!=0){
            return R.ok();
        }
        return R.fail("发布启示失败了！请重新发布");
    }

    /**
     * 个人中心删除发布的启示
     * @param map
     * @return
     */
    @RequestMapping("/deleteById")
    public R deleteById(@RequestBody Map<String,String> map){
        String postId = map.get("postId");
        Boolean flag = postService.deleteById(Integer.parseInt(postId));
        if (flag==true){
            return R.ok();
        }
        return R.fail("删除启示失败");
    }

    /**
     * 个人中心，结束启示
     * @param map
     * @return
     */
    @RequestMapping("/endPost")
    public R endPost(@RequestBody Map<String,String> map){
        String postId = "";
        if(map.containsKey("postId")){
             postId = map.get("postId");
        }
        Post post = postService.queryById(Integer.parseInt(postId));
        post.setStatus(0);
        postService.update(post);

        return R.ok();
    }


    /**
     * 用户端认领失物
     * @return
     */
    @RequestMapping("/claim")
    public R clamiPost(@RequestBody Map<String,String> map){
        Post post = new Post();
        if (map.containsKey("postId")){
            String postId = map.get("postId");
            post = postService.queryById(Integer.parseInt(postId));
            post.setStatus(0);
        }
        postService.update(post);
        return R.ok();
    }

    /**
     * 用户端启示信息编辑--数据回显
     */
    @RequestMapping("/queryInfoById")
    public Map queryInfoById(@RequestBody Map<String,String> map){
        Map  m = new HashMap<>();
        String postId = "45";
        if (map.containsKey("postId")){
            postId = map.get("postId");
        }

        Post post = postService.queryById(Integer.parseInt(postId));
        PageInfo<Type> typePageInfo = typeService.findAll(1,15,null);
        m.put("postInfo",post);
        m.put("typeList",typePageInfo.getList());
        return m;
    }

    /**
     * 用户端启示编辑--编辑接口
     */
    @RequestMapping("/editInfo")
    public R editInfo(@RequestBody Map<String,String> map){
        String postId = "";
        String title  = "";
        String content = "";
        String address="";
        String typeName="";

        if (map.containsKey("title")){
            title = map.get("title");
        }
        if (map.containsKey("content")){
            content = map.get("content");
        }
        if (map.containsKey("address")){
            address = map.get("address");
        }
        if (map.containsKey("postId")){
            postId = map.get("postId");
        }
        if (map.containsKey("typeName")){
            typeName = map.get("typeName");
        }
        Post post = postService.queryById(Integer.parseInt(postId));
        post.setAddress(address);
        post.setCtime(new Date());
        post.setTitle(title);
        post.setContent(content);
        post.setTypeName(typeName);
        postService.update(post);
        return R.ok();
    }

}