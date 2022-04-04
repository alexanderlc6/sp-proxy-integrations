package com.sp.proxy.platform.api.reader;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.catalog.CatalogService;
import com.orbitz.consul.option.ImmutableQueryOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ConsulServiceReader {

    private static final List<Consul> CONSUL_LIST = Lists.newArrayList();

    /**
     * 缓存各Consul服务的所有节点地址
     */
    private static final Cache<String, List<String>> SERVICE_ADDR_CACHE = CacheBuilder.newBuilder()
            .maximumSize(20000)
            .expireAfterWrite(600, TimeUnit.SECONDS)
            .build();

    /**
     * 初始化consul 支持多套
     * @param pairs
     */
    public void init(List<Pair<String, Integer>> pairs) {
        pairs.forEach(pair -> {
            Consul consul = Consul.builder()
                    .withHostAndPort(HostAndPort.fromParts(pair.getKey(), pair.getValue()))
                    .build();
            CONSUL_LIST.add(consul);
            log.info("Init consul:{}", JSON.toJSONString(pair));
        });
    }

    /**
     * 获取服务下所有的节点地址列表
     * @param service
     * @return
     */
    public List<String> getServices(String service) {
        List<String> addrs = SERVICE_ADDR_CACHE.getIfPresent(service);
        if (CollectionUtils.isEmpty(addrs)) {
            addrs = getServicesFromConsul(service);
            if (!CollectionUtils.isEmpty(addrs)) {
                SERVICE_ADDR_CACHE.put(service, addrs);
            }
        }
        return addrs;
    }

    /**
     * 从所有的Consul中找到服务
     * @param service
     * @return
     */
    private List<String> getServicesFromConsul(String service) {
        List<String> ipAndPorts = Lists.newArrayList();
        CONSUL_LIST.forEach(consul -> {
            ConsulResponse<List<CatalogService>> response = consul.catalogClient().getService(service, ImmutableQueryOptions.builder().enable(true).build());
            log.info("get service:{},response:{}", service, JSON.toJSONString(response));
            if (null != response && !CollectionUtils.isEmpty(response.getResponse())) {
                List<CatalogService> catalogServices = response.getResponse();
                if (!CollectionUtils.isEmpty(catalogServices)) {
                    catalogServices.forEach(catalogService -> {
                        ipAndPorts.add("http://".concat(catalogService.getServiceAddress()).concat(":").concat(String.valueOf(catalogService.getServicePort())));
                    });
                }
            }
        });
        return ipAndPorts;
    }

}
