package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Meta {
    public int code;
    public String message;

    public Meta(){
        code=200;
        message= "";
    }
}
