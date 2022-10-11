package com.example.demo.response;

import java.io.Serializable;
import java.util.HashMap;

public class ResponseResult extends HashMap<String,Object> implements Serializable
{
    public ResponseResult(){
        put("code",0);
        put("message","");
        put("data","");
    }

    public  ResponseResult(int code,String message,Object data)
    {
        put("code",code);
        put("message",message);
        put("data",data);
    }

    public int getCode()
    {
        return (Integer) this.get("code");
    }
    public String getMessage()
    {
        return (String) this.get("message");
    }
    public Object getData()
    {
        return  this.get("data");
    }
}

