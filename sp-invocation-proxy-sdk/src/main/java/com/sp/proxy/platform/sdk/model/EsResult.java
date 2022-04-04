package com.sp.proxy.platform.sdk.model;

import lombok.Data;

import java.util.List;

@Data
public class EsResult<T extends EsModel> {

    private long total;
    private List<T> data;

}
