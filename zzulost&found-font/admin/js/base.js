//打开添加获取编辑页面
function openPage(content,title,width,heigth){

    var index = layer.open({
        title: title,
        type: 1,
        shade: 0.2,
        maxmin:true,
        shadeClose: true,
        area: [width, heigth],
        content: content,
    });
    return index;
}


/**
 * 获取删除id的集合
 */
function getCheckId(data){
    var arr=new Array();
    for(var i=0;i<data.length;i++){
        arr.push(data[i].id);
    }
    return arr.join(",");
}


/**
 * 删除记录信息
 */
function deleteByIds($,url,ids,index){
    $.ajax({
        url:url,
        type:"post",
        data:{'ids':ids},
        success:function (result) {
            if(result.code==200){
                layer.msg('删除成功',{
                    iocn:6,
                    time:500
                },function () {
                    //重载父窗口 关闭当前窗口
                    parent.window.location.reload();
                    var iframeIndex=parent.layer.getFrameIndex(window.name);
                    parent.layer.close(iframeIndex);
                })
            }else{
                layer.msg("删除失败");
            }
        }
    })

};

/*
添加或者删除信息实现
* * */
function addOrUpdateSubmit($,url,datas){
    $.ajax({
        url:url,
        contentType:'application/json',
        type:"post",
        data:JSON.stringify(datas),
        success:function (result) {
            if(result.code==200){
                layer.msg('成功',{
                    iocn:6,
                    time:500
                },function () {
                    //重载父窗口 关闭当前窗口
                    parent.window.location.reload();
                    var iframeIndex=parent.layer.getFrameIndex(window.name);
                    parent.layer.close(iframeIndex);
                })
            }else{
                layer.msg("失败");
            }
        }
    })
}



