package com.sp.proxy.platform.sdk.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpanDTO {

    private String name;
    private ColorDTO colorDTO;

    @Override
    public String toString() {
        return "<span class=\"" + colorDTO.getCssClass() + "\" >" + name + "</span>";
    }
}
