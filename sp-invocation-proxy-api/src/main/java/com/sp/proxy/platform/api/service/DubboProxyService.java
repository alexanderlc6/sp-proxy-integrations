package com.sp.proxy.platform.api.service;

import com.sp.proxy.platform.api.domain.DubboProxyReq;

/**
 * @description:
 * @author: luchao
 * @date: Created in 4/3/22 1:02 AM
 */
public interface DubboProxyService {
    /**
     * 执行代理的方法
     * @param proxyReq
     * @return
     */
    Object doProxy(DubboProxyReq proxyReq);
}
