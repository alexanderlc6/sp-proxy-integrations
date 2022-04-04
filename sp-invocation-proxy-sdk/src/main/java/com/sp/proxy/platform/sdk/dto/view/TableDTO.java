package com.sp.proxy.platform.sdk.dto.view;

import lombok.Data;

import java.util.List;

@Data
public class TableDTO {

    public TableDTO() {

    }

    public TableDTO(String name, List<ColumnDTO> columns, List<TableDataDTO> rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
        this.total = rows.size();
    }

    /**
     * 列定义
     */
    private List<ColumnDTO> columns;

    /**
     * 数据
     */
    private List<TableDataDTO> rows;

    /**
     * 表格名称
     */
    private String name;

    /**
     * Table 的高度，默认为自动高度。如果 height 为 number 类型，单位 px；如果 height 为 string 类型，则这个高度会设置为 Table 的 style.height 的值，Table 的高度会受控于外部样式。
     */
    private String height;

    /**
     * Table 的最大高度。合法的值为数字或者单位为 px 的高度。
     */
    private String maxHeight;

    /**
     * 是否为斑马纹 table  default false
     */
    private boolean stripe;

    /**
     * 是否带有纵向边框 default false
     */
    private boolean border;

    /**
     * Table 的尺寸	string	medium / small / mini
     */
    private String size = "medium";

    /**
     * 列的宽度是否自撑开
     */
    private boolean fit = true;

    /**
     * 是否显示表头
     */
    private boolean showHeader = true;

    /**
     * 是否要高亮当前行
     */
    private boolean highlightCurrentRow;

    /**
     * 当前行的 key，只写属性
     */
    private String currentRowKey;

    /**
     * empty-text	空数据时显示的文本内容，也可以通过 slot="empty" 设置 暂无数据
     */
    private String emptyText = "暂无数据";

    /**
     * default-expand-all	是否默认展开所有行，当 Table 包含展开行存在或者为树形表格时有效	Boolean	—	false
     */
    private boolean defaultExpandAll = false;

    /**
     * expand-row-keys	可以通过该属性设置 Table 目前的展开行，需要设置 row-key 属性才能使用，该属性为展开行的 keys 数组。	Array	—
     */
    private String expandRowKeys;

    /**
     * default-sort	默认的排序列的 prop 和顺序。它的prop属性指定默认的排序的列，order指定默认排序的顺序	Object	order: ascending, descending	如果只指定了prop, 没有指定order, 则默认顺序是ascending
     */
    private String defaultSort;

    /**
     * tooltip-effect	tooltip effect 属性	String	dark/light
     */
    private String tooltipEffect;

    /**
     * show-summary	是否在表尾显示合计行	Boolean	—	false
     */
    private boolean showSummary;

    /**
     * sum-text	合计行第一列的文本	String	—	合计
     */
    private String sumText;

    /**
     * 对应row-key
     * 支持树类型的数据的显示。当 row 中包含 children 字段时，被视为树形数据。渲染树形数据时，必须要指定 row-key。支持子节点数据异步加载。设置 Table 的 lazy 属性为 true 与加载函数 load 。通过指定 row 中的 hasChildren 字段来指定哪些行是包含子节点。children 与 hasChildren 都可以通过 tree-props 配置。
     */
    private String rowKey;

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 每页数量
     */
    private int pageSize = 100;

    /**
     * 记录总数
     */
    private long total;

    /**
     * 页码总数
     */
    private int pages = 1;

}