package com.sp.proxy.platform.sdk.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ColorDTO {

    NORMAL("el-tag el-tag--big el-tag--primary"),
    SUCCESS("el-tag el-tag--big el-tag--success el-tag--dark"),
    INFO("el-tag el-tag--big el-tag--info el-tag--dark"),
    WARNING("el-tag el-tag--big el-tag--warning el-tag--dark"),
    DANGER("el-tag el-tag--big el-tag--danger el-tag--dark"),
    ;

    String cssClass;

}
