package com.sp.proxy.platform.api.service;

import com.sp.proxy.platform.api.domain.CommonProxyReq;

/**
 * @description: 公共代理调用Service定义
 * @author: luchao
 * @date: Created in 4/3/22 1:02 AM
 */
public interface CommonProxyService {
    /**
     * 执行代理的方法
     * @param proxyReq
     * @return
     */
    Object doProxy(CommonProxyReq proxyReq);
}
