package com.sp.proxy.platform.sdk.dto;

import com.sp.proxy.platform.sdk.dto.view.TableDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SherlockResp implements Serializable {

    /**
     * 返回的查询条件列表
     * 当reqType = 1时，返回的autocomplete形式的label和value
     */
    private List<OptionDTO> options = new ArrayList();

    /**
     * 返回的Table数据集合
     */
    private List<TableDTO> tables = new ArrayList();

}
