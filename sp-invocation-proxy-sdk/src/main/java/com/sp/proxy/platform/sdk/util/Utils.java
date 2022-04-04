package com.sp.proxy.platform.sdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.sp.framework.common.utils.JsonFormatter;
import com.sp.framework.common.vo.ResponseVO;
import com.sp.proxy.platform.sdk.dto.SherlockReq;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static final TypeReference<Map<String, String>> MAP_R = new TypeReference<Map<String, String>>() {
    };

    public static final TypeReference<ResponseVO<String>> R_STRING = new TypeReference<ResponseVO<String>>() {
    };

    public static final TypeReference<String> STRING_REF = new TypeReference<String>() {
    };

    public static Map<String, String> convertToMap(String tmp) {
        if (StringUtils.isBlank(tmp)) {
            return Collections.EMPTY_MAP;
        }
        return JsonFormatter.toObject(tmp, HashMap.class);
    }

    public static String getByEnv(String source, String env) {
        return convertToMap(source).get(env);
    }


    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }

    public static <T, R> List<R> streamAndMapToList(Collection<T> collection, Function<T, R> mapper) {
        return stream(collection).map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> streamFilterAndMapToSet(Collection<T> collection, Predicate<? super T> predicate, Function<T, R> mapper) {
        return stream(collection).filter(predicate).map(mapper).collect(Collectors.toSet());
    }

    public static <T, R> List<R> streamFilterAndMapToList(Collection<T> collection, Predicate<? super T> predicate, Function<T, R> mapper) {
        return stream(collection).filter(predicate).map(mapper).collect(Collectors.toList());
    }

    public static <T, R> Set<R> streamAndMapToSet(Collection<T> collection, Function<T, R> mapper) {
        return stream(collection).map(mapper).collect(Collectors.toSet());
    }

    public static <T, K, R> Map<K, R> streamToMap(Collection<T> collection, Function<T, K> keyFun, Function<T, R> valueFunc) {
        return stream(collection).collect(Collectors.toMap(keyFun, valueFunc));
    }

    public static <T, K> Map<K, T> streamToMap(Collection<T> collection, Function<T, K> keyFun) {
        return stream(collection).collect(Collectors.toMap(keyFun, T -> T));
    }

    public static <T, K> Map<K, List<T>> streamToMapList(Collection<T> collection, Function<T, K> keyFun) {
        return stream(collection).collect(Collectors.groupingBy(keyFun));
    }

    public static String ifNullEmpty(String tmp) {
        return StringUtils.isBlank(tmp) ? StringUtils.EMPTY : tmp;
    }

    public static String jsonPretty(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }

    public static <T> List<T> parseExcelData(SherlockReq req, Class<T> clazz) {
        if (null == req || req.getParams() == null || req.getParams().isEmpty() || !req.getParams().containsKey("file")) {
            //无数据
            return Lists.newArrayList();
        }
        return JSON.parseArray(req.getParams().get("file"), clazz);
    }
}
