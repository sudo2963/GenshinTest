package com.example.demo.response;

import org.apache.commons.lang.StringUtils;

public class ResponseSuccess extends ResponseResult {

    public ResponseSuccess()
    {
        super(0,"", StringUtils.EMPTY);
    }
    public ResponseSuccess(Object data)
    {
        super(0,"",data);
    }
    public ResponseSuccess(String message)
    {
        super(0,message,StringUtils.EMPTY);
    }
    public ResponseSuccess(Object data,String message)
    {
        super(0,message,data);
    }
}
