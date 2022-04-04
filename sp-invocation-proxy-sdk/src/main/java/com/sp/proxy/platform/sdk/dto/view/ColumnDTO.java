package com.sp.proxy.platform.sdk.dto.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColumnDTO implements Serializable {

    public ColumnDTO() {
    }

    public ColumnDTO(String label, String prop) {
        this.label = label;
        this.prop = prop;
    }

    /**
     * label	显示的标题	string	—	—
     */
    private String label;

    /**
     * prop	对应列内容的字段名，也可以使用 property 属性	string	—	—
     */
    private String prop;

    /**
     * type	对应列的类型。如果设置了 selection 则显示多选框；如果设置了 index 则显示该行的索引（从 1 开始计算）；如果设置了 expand 则显示为一个可展开的按钮	string	selection/index/expand	—
     */
    private String type;

    /**
     * index	如果设置了 type=index，可以通过传递 index 属性来自定义索引	number, Function(index)	-	-
     */
    private String index;

    /**
     * width	对应列的宽度	string	—	—
     */
    private String width;

    /**
     * min-width	对应列的最小宽度，与 width 的区别是 width 是固定的，min-width 会把剩余宽度按比例分配给设置了 min-width 的列	string	—	—
     */
    private String minWidth;

    /**
     * fixed	列是否固定在左侧或者右侧，true 表示固定在左侧	string, boolean	true, left, right	—
     */
    private String fixed;

    /**
     * sortable	对应列是否可以排序，如果设置为 'custom'，则代表用户希望远程排序，需要监听 Table 的 sort-change 事件	boolean, string	true, false, 'custom'	false
     */
    private boolean sortable;

    /**
     * sort-by	指定数据按照哪个属性进行排序，仅当 sortable 设置为 true 且没有设置 sort-method 的时候有效。如果 sort-by 为数组，则先按照第 1 个属性排序，如果第 1 个相等，再按照第 2 个排序，以此类推	String/Array/Function(row, index)	—	—
     */
    private String sortBy;

    /**
     * 列的 className
     */
    private String className;

    /**
     * label-class-name	当前列标题的自定义类名	string	—	—
     */
    private String labelClassName;

    private int order;

}
