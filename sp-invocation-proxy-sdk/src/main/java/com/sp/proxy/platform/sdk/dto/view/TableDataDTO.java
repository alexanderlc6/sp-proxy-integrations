package com.sp.proxy.platform.sdk.dto.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


public class TableDataDTO extends HashMap<String, Object> implements Serializable {
    public void setChildren(List<TableDataDTO> children) {
        this.put("children", children);
    }
}
