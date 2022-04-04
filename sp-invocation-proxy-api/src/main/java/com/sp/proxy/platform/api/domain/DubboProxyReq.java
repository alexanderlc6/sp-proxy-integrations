package com.sp.proxy.platform.api.domain;

import lombok.Data;

import java.util.Map;

@Data
public class DubboProxyReq {
    private String group;
    private String version;
    private String interfaceName;
    private String methodName;
    private String[] paramTypes;
    private Object[] paramValues;
    private Map<String, String> attachments;
}
