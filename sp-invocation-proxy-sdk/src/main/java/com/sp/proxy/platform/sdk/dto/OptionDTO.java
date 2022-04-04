package com.sp.proxy.platform.sdk.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionDTO implements Serializable {

    /**
     * 展示的label
     */
    private String label;

    /**
     * 值，用户请求传输
     */
    private String value;

}
