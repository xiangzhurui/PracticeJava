package com.xiangzhurui.rest.api;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 响应
 *
 * @author xiangzhurui
 * @version 2018/3/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 8789246656912962983L;

    private Integer code;
    private String msg;
    private Date time;
    private T data;
}
