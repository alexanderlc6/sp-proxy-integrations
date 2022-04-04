package com.sp.proxy.platform.sdk.script;

import com.alibaba.fastjson.TypeReference;
import com.sp.proxy.platform.sdk.model.EsModel;
import com.sp.proxy.platform.sdk.model.EsResult;
import com.sp.proxy.platform.sdk.model.SheetAndData;

import java.util.List;
import java.util.Map;

public interface CommonScriptApi {

    String getRealUrl(String appId, String env, String uri);

    //未实现
    String post(String apiCode, String env, Object req, Map<String, String> headers);

    //未实现
    <T> T post(String apiCode, String env, Object req, TypeReference<T> typeReference, Map<String, String> headers);

    //未实现
    String get(String apiCode, String env, Object req);

    //未实现
    <T> T get(String apiCode, String env, Object req, TypeReference<T> typeReference);

    String post(String url, Object req, Map<String, String> headers);

    <T> T post(String url, Object req, TypeReference<T> typeReference, Map<String, String> headers);

    String get(String url, Object req);

    <T> T get(String url, Object req, TypeReference<T> typeReference);

    void ignoreLog();

    void clearIgnoreLog();

    String generateExcel(String fileName, List<SheetAndData> sheetAndDatas);

    String sqlToEsDsl(String sql);

    /**
     * es的index和type由sql解析获取
     * es请求url组成： http://xxx:端口/${index}/${type}/_search
     *
     * @param esAddr
     * @param sql
     * @return
     */
    <T extends EsModel> EsResult<T> searchEsBySql(String esAddr, String sql, Class<T> clazz);

    <T> T getDubboInstance(Class<T> clazz);

    <T> T getFeignInstance(Class<T> clazz);
}
