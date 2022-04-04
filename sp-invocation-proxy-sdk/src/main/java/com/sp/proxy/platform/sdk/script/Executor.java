package com.sp.proxy.platform.sdk.script;

import com.sp.proxy.platform.sdk.dto.SherlockReq;
import com.sp.proxy.platform.sdk.dto.SherlockResp;
import com.sp.proxy.platform.sdk.dto.view.ColorDTO;
import com.sp.proxy.platform.sdk.dto.view.SpanDTO;
import com.sp.proxy.platform.sdk.dto.view.TableDTO;
import com.sp.proxy.platform.sdk.util.SherlockTableGenerator;

import java.util.List;

public interface Executor {

    SherlockResp execute(SherlockReq req);

    default TableDTO generateTableByListMap(String tableName, List data) {
        return SherlockTableGenerator.generateTableByListMap(tableName, data);
    }

    default <T> TableDTO generateTable(String tableName, List<T> data) {
        return SherlockTableGenerator.generateTable(tableName, data);
    }

    default TableDTO buildErrorTable(String msg) {
        return SherlockTableGenerator.buildErrorTable(msg);
    }

    default TableDTO buildSingleColumnTable(String lable, String msg) {
        return SherlockTableGenerator.buildSingleColumnTable(lable, msg);
    }

    default String toSpan(String str) {
        return toSpan(str, ColorDTO.NORMAL);
    }

    default String toSpan(String str, ColorDTO colorDTO) {
        return new SpanDTO(str, colorDTO).toString();
    }

    default String toALink(String label, String href) {
        return "<a href='" + href + "'>" + label + "</a>";
    }
}
