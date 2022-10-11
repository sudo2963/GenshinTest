package com.example.demo.response;

import org.apache.commons.lang.StringUtils;

public class ResponseError extends ResponseResult
{
    public ResponseError()
    {
        super(-1,"", StringUtils.EMPTY);
    }
    public ResponseError(String message)
    {
        super(-1,message,StringUtils.EMPTY);
    }
    public ResponseError(String message,Object data)
    {
        super(-1,message,data);
    }
}
