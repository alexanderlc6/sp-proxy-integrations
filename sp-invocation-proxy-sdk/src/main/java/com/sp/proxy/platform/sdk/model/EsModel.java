package com.sp.proxy.platform.sdk.model;

import java.util.HashMap;

public class EsModel extends HashMap<String,Object> {

    private String index;
    private String type;
    private String id;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
