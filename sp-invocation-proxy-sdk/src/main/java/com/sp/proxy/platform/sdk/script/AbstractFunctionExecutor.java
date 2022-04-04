package com.sp.proxy.platform.sdk.script;

import com.sp.proxy.platform.sdk.dto.SherlockReq;
import com.sp.proxy.platform.sdk.dto.biz.SpUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class AbstractFunctionExecutor implements Executor {

    @Autowired
    public CommonScriptApi scriptApi;

    @Autowired
    public SpUserCenterApi idaasApi;

    public SpUser getUser(SherlockReq req) {
        return idaasApi.getUser(req);
    }

    public SpUser getUserByUserCode(String userCode, String env) {
        return idaasApi.getUserByUserCode(userCode, env);
    }

    public SpUser getUserByPhone(String phone, String env) {
        return idaasApi.getUserByPhone(phone, env);
    }

    /**
     * idaas 自用
     *
     * @param content
     * @param params
     * @param env
     * @return
     */
    public List<Map<String, Object>> requestSingle(String content, Map<String, String> params, String env) {
        return idaasApi.requestSingle(content, params, env);
    }

    public List<List<Map<String, Object>>> request(String content, Map<String, String> params, String env) {
        return idaasApi.request(content, params, env);
    }
}
