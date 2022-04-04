package com.sp.proxy.platform.api.controller;

import com.alibaba.fastjson.JSON;
import com.sp.framework.common.utils.ResponseUtil;
import com.sp.framework.common.vo.ResponseVO;
import com.sp.proxy.platform.api.domain.CommonProxyReq;
import com.sp.proxy.platform.api.domain.DubboProxyReq;
import com.sp.proxy.platform.api.reader.ConsulServiceReader;
import com.sp.proxy.platform.api.service.CommonProxyService;
import com.sp.proxy.platform.api.service.DubboProxyService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

/**
 * @description: 代理调用Controller
 * @author: luchao
 * @date: Created in 4/2/22 8:02 PM
 */
@RestController
@RequestMapping(path = "/proxy")
@Slf4j
public class ProxyController {
    @Autowired
    private DubboProxyService dubboProxyService;

    @Autowired
    private ConsulServiceReader consulServiceReader;

    @Autowired
    private CommonProxyService commonProxyService;

    /**
     * 执行公共代理API
     * 请求示例：
     * {
     *     "proxyType": "FEIGN",
     *     "httpMethod": "GET",
     *     "targetUri": "/demo/testFeignLink",
     *     "target": "full-link-dye-a"
     * }
     * @param req
     * @return
     */
    @PostMapping(value = "/common/exec")
    public Object exec(@RequestBody @Valid CommonProxyReq req) {
        try {
            return commonProxyService.doProxy(req);
        } catch (HttpServerErrorException e){
            log.error("Common proxy execute timeout. Request param:{}", JSON.toJSONString(req), e);
            return ResponseUtil.getFailure("Common proxy execute timeout, please check downstream service.");
        } catch (Exception e) {
            log.error("Common proxy execute failed! Request param:{}", JSON.toJSONString(req), e);
            return ExceptionUtils.getStackTrace(e);
        }
    }


    @GetMapping(value = "/consul/addrs")
    public ResponseVO<Object> addrs(@RequestParam("service") String service) {
        return ResponseUtil.getFromData(consulServiceReader.getServices(service));
    }

    /**
     * dubbo代理调用方法
     * @param req
     * @return
     */
    @ApiOperation("dubbo代理")
    @PostMapping(value = "/dubbo")
    public Object dubboProxy(@RequestBody DubboProxyReq req) {
        try {
            return dubboProxyService.doProxy(req);
        } catch (Exception e) {
            log.error("proxy error,req:{}", JSON.toJSONString(req), e);
            return ExceptionUtils.getStackTrace(e);
        }
    }
}
