package com.sp.proxy.platform.api.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: Dubbo泛化调用器
 * @author: luchao
 * @date: Created in 4/3/22 1:04 AM
 */
@Slf4j
@Component
public class GenericInvoker {

    @Autowired
    ApplicationConfig applicationConfig;

    private Map<String, ReferenceConfig<GenericService>> cachedConfig = new ConcurrentHashMap();


    public Object genericInvoke(String interfaceName, String group, String version,String methodName,
                                String[] paramTypes, Object[] paramObjs){
        ReferenceConfig<GenericService> referenceConfig = addNewReferenceConfig(interfaceName, group, version);
        try {
            GenericService service = referenceConfig.get();
            log.info("Dubbo generic invocation: service:{}, method: {}, paramTypes:{}, paramObjs:{}",
                        interfaceName, methodName, paramTypes, paramObjs);
            if(service == null){
                String providerUrl = interfaceName + ifNullOrEmpty(group) + ifNullOrEmpty(version);
                throw new RuntimeException("No provider for " + providerUrl);
            }

            return service.$invoke(methodName, paramTypes, paramObjs);
        }catch (Exception e){
            log.error("Dubbo generic invocation failed!", e);
            throw e;
        }
    }

    private ReferenceConfig<GenericService> addNewReferenceConfig(String interfaceName, String group, String version) {
        ReferenceConfig<GenericService> refConfig;
        String cacheKey = interfaceName + ifNullOrEmpty(group) + ifNullOrEmpty(version);
        refConfig = cachedConfig.get(cacheKey);
        if(refConfig == null){
            ReferenceConfig<GenericService> newRefConfig = initRefConfig(interfaceName, group, version);
            ReferenceConfig<GenericService> oldRefConfig = cachedConfig.putIfAbsent(cacheKey, newRefConfig);
            if(oldRefConfig != null){
                refConfig = oldRefConfig;
            }else {
                refConfig = newRefConfig;
            }
        }

        return refConfig;
    }

    /**
     * 初始化ReferenceConfig新对象
     * @param interfaceName
     * @param group
     * @param version
     * @return
     */
    private ReferenceConfig<GenericService> initRefConfig(String interfaceName, String group, String version) {
        ReferenceConfig<GenericService> refConfig = new ReferenceConfig<>();
        refConfig.setGeneric(true);
        refConfig.setApplication(applicationConfig);
        refConfig.setInterface(interfaceName);
        refConfig.setGroup(group);
        refConfig.setVersion(version);
        return refConfig;
    }

    private String ifNullOrEmpty(String value){
        return StringUtils.isBlank(value) ? Strings.EMPTY : value;
    }

}
