package com.sp.proxy.platform.api.domain;

import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CommonProxyReq {

    @NotNull
    private CommonProxyType proxyType;

    /**
     * http method
     */
    @NotNull
    private HttpMethod httpMethod;

    /**
     * 请求头参数集合
     */
    private Map<String, String> headers;

    /**
     * 目标uri路径,不带？以及url上的参数
     */
    @NotNull
    private String targetUri;

    /**
     * 目标，可以是域名(http)，服务名称（feign consul service name）,es地址 (ip and port)
     */
    @NotNull
    private String target;

    /**
     * url请求参数，querystring上的
     * 实际请求会用targetUri+queryParams生成真实url
     */
    private Map<String, String> queryParams;

    /**
     * 请求体
     */
    private Object body;
}
