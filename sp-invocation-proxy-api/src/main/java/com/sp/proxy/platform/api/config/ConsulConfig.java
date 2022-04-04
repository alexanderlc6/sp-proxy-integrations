package com.sp.proxy.platform.api.config;

import cn.hutool.core.lang.Pair;
import com.google.common.collect.Lists;
import com.sp.proxy.platform.api.reader.ConsulServiceReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @description: Consul配置类
 * @author: luchao
 * @date: Created in 4/2/22 8:02 PM
 */
@Component
public class ConsulConfig {
    @Autowired
    private ConsulServiceReader consulServiceReader;

    @Value("${sp.consul.clusters}")
    private String consulClusters;

    @PostConstruct
    public void init() {
        List<Pair<String, Integer>> pairs = Lists.newArrayList();
        if (StringUtils.isNotBlank(consulClusters)) {
            String[] consulIpTmps = consulClusters.split(";");
            for (String consul : consulIpTmps) {
                pairs.add(Pair.of(consul.split(":")[0].trim(), Integer.parseInt(consul.split(":")[1].trim())));
            }
        }

        consulServiceReader.init(pairs);
    }
}
