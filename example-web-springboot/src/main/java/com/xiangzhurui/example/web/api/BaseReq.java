package com.xiangzhurui.example.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiangzhurui
 * @version 2018/3/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseReq<T> implements Serializable {
    private static final long serialVersionUID = -5925608867386612809L;

    private String token;
    private String appid;
    private T data;
}
