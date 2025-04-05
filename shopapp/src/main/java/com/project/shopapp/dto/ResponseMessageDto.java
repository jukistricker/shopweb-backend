package com.project.shopapp.dto;


import com.project.shopapp.constants.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.shopapp.constants.ApiConstants.*;

@Data
@AllArgsConstructor
public class ResponseMessageDto {

    private Meta meta;
    private Object data;

    public ResponseMessageDto()
    {
        meta = new Meta();
        data = null;

    }

    public ResponseMessageDto Success(Object data)
    {
        this.data = data;
        this.meta.code = StatusCode.Success200;
        this.meta.message = MessageResource.ACCTION_SUCCESS;

        return this;
    }

    public ResponseMessageDto Success(Object data, int code)
    {
        this.data = data;
        this.meta.code = code;
        this.meta.message = MessageResource.ACCTION_SUCCESS;

        return this;
    }

    public ResponseMessageDto Success(Object data, String msg)
    {
        this.data = data;
        
        this.meta.code = StatusCode.Success200;
        this.meta.message = msg;

        return this;
    }

    public ResponseMessageDto Success(Object data, String msg, int code)
    {
        this.data = data;
        
        this.meta.code = code;
        this.meta.message = msg;

        return this;
    }

    public ResponseMessageDto Success(Object data, int metadata, String msg, int code)
    {
        this.data = data;
        this.meta.code = code;
        this.meta.message = msg;

        return this;
    }

    public ResponseMessageDto Success(Object data, String msg, int code, int count)
    {
        this.data = data;
        this.meta.code = code;
        this.meta.message = msg;

        return this;
    }

    public ResponseMessageDto Error(String msg)
    {
        meta.code = -500;
        meta.message = msg;
        data = null;

        return this;
    }

    public ResponseMessageDto Error(String msg, int code)
    {
        this.data = null;
        this.meta.code = code;
        this.meta.message = msg;

        return this;
    }
}
