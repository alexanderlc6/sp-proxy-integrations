package com.sp.proxy.platform.sdk.model;

import java.io.Serializable;

public class CommonResponse implements Serializable {
    private static final long serialVersionUID = -2564445585181441109L;
    private int code;
    private String message = "";
    private static final transient CommonResponse successResponse = new CommonResponse(200000, "");

    public static CommonResponse buildSuccessCommonResponse() {
        return successResponse;
    }

    public CommonResponse() {
    }

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
