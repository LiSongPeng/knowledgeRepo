package com.zh.framework.entity;

import java.util.List;

/**
 * created by lihuibo on 17-8-28 上午10:05
 */
public class Response {
    private int flag;//返回标志,200 出错,201 系统异常 ...
    private String message;
    private List<Object> data;

    public Response() {
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (flag != response.flag) return false;
        if (!message.equals(response.message)) return false;
        return data.equals(response.data);
    }
}
