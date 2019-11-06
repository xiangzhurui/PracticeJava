package com.xiangzhurui.core.ext.jackson.api;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xiangzhurui
 * @version 2018/3/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class ApiRequest<T> implements Serializable {
    private static final long serialVersionUID = -5925608867386612809L;

    private String token;
    private String appid;
    private T data;
}
