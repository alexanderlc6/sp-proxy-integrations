package com.sp.proxy.platform.sdk.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class SherlockReq implements Serializable {

    /**
     * 具体参数以及值
     */
    private Map<String, String> params;

    /**
     * 请求类型，
     * 默认0 表示 功能查询或执行
     * 1为字段搜索条件 autocomplete形式
     * 其他待扩充
     */
    private int reqType = 0;

    /**
     * 当reqType = 1时，搜索 输入建议的字段
     */
    private String searchFieldName;

    /**
     * 功能编码
     */
    private String functionCode;

    /**
     * 一页条数
     */
    private int size = 100;

    /**
     * 启始页
     */
    private int startPage = 0;

    /**
     * 租户编码
     */
    private String tenantCode;

    /**
     * 当前用户工号
     */
    private String currentUserCode;


    /**
     * 环境，一般不需要处理
     */
    private String env;

}
