layui.define([], function (exports) {
    exports("store", store ={
        setToken:function (token) {
            localStorage.setItem("token",token);
        },
        getToken:function () {
            return localStorage.getItem("token");
        },
        clear:function () {
            return localStorage.removeItem("token");
        },
        setLoginInfo:function (obj,type) {
            if(obj){
                localStorage.setItem("userName",obj.name);
                localStorage.setItem("type",type);
            }
        },
        getLoginInfo:function () {
            return localStorage.getItem("userName");
        },
        getLoginType:function () {
          return localStorage.getItem("type");
        }
    });
});
