package com.sp.proxy.platform.api.service.impl;

import com.sp.proxy.platform.api.domain.DubboProxyReq;
import com.sp.proxy.platform.api.service.DubboProxyService;
import com.sp.proxy.platform.api.service.GenericInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: luchao
 * @date: Created in 4/3/22 1:04 AM
 */
@Service
public class DubboProxyServiceImpl implements DubboProxyService {
    @Autowired
    GenericInvoker genericInvoker;


    @Override
    public Object doProxy(DubboProxyReq proxyReq) {
        return genericInvoker.genericInvoke(proxyReq.getInterfaceName(), proxyReq.getGroup(), proxyReq.getVersion(),
                proxyReq.getMethodName(), proxyReq.getParamTypes(), proxyReq.getParamValues());
    }
}
