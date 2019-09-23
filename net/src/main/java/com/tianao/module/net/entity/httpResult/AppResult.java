package com.tianao.module.net.entity.httpResult;

public class AppResult<B> {

    /**
     * status : 200
     * data : {}
     * msg : 视频可缓存
     * returnType : JSON
     */
    public int status;
    public B data;
    public String msg;
    public String returnType;

    @Override
    public String toString() {
        return "AppResult{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
