package com.sp.proxy.platform.api.service.impl;

import com.google.common.collect.Lists;
import com.sp.proxy.platform.api.domain.CommonProxyReq;
import com.sp.proxy.platform.api.reader.ConsulServiceReader;
import com.sp.proxy.platform.api.service.CommonProxyService;
import com.sp.proxy.platform.api.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * 公共代理调用Service实现
 */
@Service
@Slf4j
public class CommonProxyServiceImpl implements CommonProxyService {

    @Autowired
    private ConsulServiceReader consulServiceReader;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object doProxy(CommonProxyReq req) {
        String query = MapUtils.getStringByMap(req.getQueryParams());
        String url = getRealDomain(req).concat(req.getTargetUri()).concat(StringUtils.isBlank(query)
                     ? StringUtils.EMPTY : "?".concat(query));

        HttpHeaders httpHeaders = new HttpHeaders();
        if (null != req.getHeaders() && !req.getHeaders().isEmpty()) {
            req.getHeaders().forEach((k, v) -> {
                httpHeaders.put(k, Lists.newArrayList(v));
            });
        }
        HttpEntity<Object> httpEntity = new HttpEntity<>(req.getBody(), httpHeaders);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, req.getHttpMethod(), httpEntity, Object.class);
        log.info("Rest request url:{},method:{},req:{},resp:{}", url, req.getHttpMethod(), httpEntity, responseEntity);
        return responseEntity.getBody();
    }

    private String getRealDomain(CommonProxyReq req) {
        String domain = req.getTarget();
        switch (req.getProxyType()) {
            case FEIGN:
                if(!domain.contains("http")){
                    List<String> addrs = consulServiceReader.getServices(req.getTarget());
                    if (CollectionUtils.isEmpty(addrs)) {
                        throw new RuntimeException("未找到service:" + req.getTarget() + "服务IP");
                    }
                    Collections.shuffle(addrs);
                    domain = addrs.get(0);
                }
                break;
            default:
                break;
        }
        return domain;
    }
}
