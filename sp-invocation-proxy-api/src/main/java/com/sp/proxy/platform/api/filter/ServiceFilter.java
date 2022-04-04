package com.sp.proxy.platform.api.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @description: Provider Filter
 * @author: luchao
 * @date: Created in 4/3/22 7:06 PM
 */
@Activate(group = "provider")
public class ServiceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }
}
