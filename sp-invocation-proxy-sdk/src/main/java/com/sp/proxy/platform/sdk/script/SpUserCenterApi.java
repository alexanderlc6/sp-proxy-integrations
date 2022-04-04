package com.sp.proxy.platform.sdk.script;


import com.sp.proxy.platform.sdk.dto.SherlockReq;
import com.sp.proxy.platform.sdk.dto.biz.SpUser;

import java.util.List;
import java.util.Map;

public interface SpUserCenterApi {

    /**
     * 1、取params里工号：userCode
     * 2、取params里姓名：realName
     * 3、取params里手机号：phone
     * 获取用户信息
     *
     * @param req
     * @return
     */
    SpUser getUser(SherlockReq req);

    SpUser getUserByUserCode(String userCode, String env);

    SpUser getUserByPhone(String phone, String env);


    /**
     * 用户中心 自用
     * @param content
     * @param params
     * @param env
     * @return
     */
    List<Map<String, Object>> requestSingle(String content, Map<String, String> params, String env);

    List<List<Map<String, Object>>> request(String content, Map<String, String> params, String env);

}
