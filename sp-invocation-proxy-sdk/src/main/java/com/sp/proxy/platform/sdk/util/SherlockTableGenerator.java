package com.sp.proxy.platform.sdk.util;

import com.google.common.collect.Lists;
import com.sp.framework.common.utils.JsonFormatter;
import com.sp.proxy.platform.sdk.annotation.Column;
import com.sp.proxy.platform.sdk.dto.biz.UserDTO;
import com.sp.proxy.platform.sdk.dto.view.ColumnDTO;
import com.sp.proxy.platform.sdk.dto.view.TableDTO;
import com.sp.proxy.platform.sdk.dto.view.TableDataDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class SherlockTableGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SherlockTableGenerator.class);

    public static TableDTO generateTableByListMap(String tableName, List<Map> data) {
        TableDTO tableDTO = new TableDTO();
        if (CollectionUtils.isEmpty(data)) {
            tableDTO.setName(tableName);
            tableDTO.setColumns(new ArrayList<>());
            tableDTO.setRows(new ArrayList<>());
            return tableDTO;
        }
        Map<String, Object> firstMap = data.get(0);
        List<TableDataDTO> tableDataDTOS = new ArrayList<>();
        List<ColumnDTO> columnDTOList = new ArrayList<>();
        for (String key : firstMap.keySet()) {
            ColumnDTO columnDTO = new ColumnDTO();
            columnDTO.setLabel(key);
            columnDTO.setProp(key);
            columnDTOList.add(columnDTO);
        }

        data.forEach(m -> {
            TableDataDTO tableDataDTO = new TableDataDTO();
            m.forEach((k, v) -> {
                String val = "";
                if (null != v) {
                    val = v instanceof String ? (String) v : JsonFormatter.toJsonString(v);
                }
                tableDataDTO.put(k.toString(), val);
            });
            tableDataDTOS.add(tableDataDTO);
        });
        return new TableDTO(tableName, columnDTOList, tableDataDTOS);
    }

    public static <T> TableDTO generateTable(String tableName, List<T> data) {
        TableDTO tableDTO = new TableDTO();
        if (CollectionUtils.isEmpty(data)) {
            tableDTO.setName(tableName);
            tableDTO.setColumns(new ArrayList<>());
            tableDTO.setRows(new ArrayList<>());
            return tableDTO;
        }

        List<TableDataDTO> tableDataDTOS = new ArrayList<>();
        List<ColumnDTO> columnDTOList = new ArrayList<>();

        T first = data.get(0);
        Field[] fields = first.getClass().getDeclaredFields();
        //order 100开始，定义了的 和没定义的 正常情况下不会冲突
        int order = 100;
        for (Field field : fields) {
            if (field.isSynthetic()) {
                //去除编译器生成的field
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            ColumnDTO columnDTO = new ColumnDTO();
            columnDTO.setLabel(column == null ? field.getName() : column.label());
            columnDTO.setOrder(column == null ? order : column.order());
            columnDTO.setProp(field.getName());
            order++;

            if (column != null && !column.isTree() && !column.ignore()) {
                //树结构不需要此字段，自动构造children
                columnDTOList.add(columnDTO);
            }else if (column == null){
                columnDTOList.add(columnDTO);
            }
        }
        columnDTOList.sort(Comparator.comparingInt(ColumnDTO::getOrder));

        //prepare data
        for (T t : data) {
            tableDataDTOS.add(buildTableData(t, fields));
        }

        return new TableDTO(tableName, columnDTOList, tableDataDTOS);
    }

    private static <T> TableDataDTO buildTableData(T t, Field[] fields) {
        TableDataDTO tableDataDTO = new TableDataDTO();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.isSynthetic()) {
                    //去除编译器生成的field
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                Object obj = null;
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), t.getClass());
                Method getterMethod = propertyDescriptor.getReadMethod();
                if (getterMethod != null) {
                    obj = getterMethod.invoke(t);
                } else {
                    obj = field.get(t);
                }
                if (column != null && column.ignore()) {
                    continue;
                }
                if (column != null && column.isTree() && obj instanceof Collection) {
                    //树结构
                    List<TableDataDTO> children = new ArrayList<>();
                    Collection collections = (Collection) obj;
                    collections.forEach(c -> {
                        Field[] cFields = c.getClass().getDeclaredFields();
                        children.add(buildTableData(c, cFields));
                    });
                    tableDataDTO.put("children", children);
                } else {
                    String val = "";
                    if (null != obj) {
                        val = obj instanceof String ? (String) obj : JsonFormatter.toJsonString(obj);
                    }
                    tableDataDTO.put(field.getName(), val);
                }
            } catch (Exception e) {
                LOGGER.error("get field:{} info error,", field.getName(), e);
            }
        }
        return tableDataDTO;
    }

    public static TableDTO buildErrorTable(String msg) {
        msg = formatMsg(msg);
        return buildSingleColumnTable("异常信息", msg);
    }

    public static TableDTO buildErrorTable(String msg, String tableName) {
        msg = formatMsg(msg);
        return buildSingleColumnTable("异常信息", msg, tableName);
    }

    public static TableDTO buildSingleColumnTable(String lable, String msg) {
        return buildSingleColumnTable(lable, msg, "表格内容");
    }

    public static TableDTO buildSingleColumnTable(String lable, String msg, String tableName) {
        TableDTO tableDTO = new TableDTO();
        ColumnDTO columnDTO = new ColumnDTO(lable, "msg");
        tableDTO.setColumns(Lists.newArrayList(columnDTO));
        TableDataDTO tableDataDTO = new TableDataDTO();
        tableDataDTO.put("msg", msg);
        tableDTO.setRows(Lists.newArrayList(tableDataDTO));
        tableDTO.setName(tableName);
        tableDTO.setTotal(tableDTO.getRows().size());
        return tableDTO;
    }

    public static String formatMsg(String msg){
        if (StringUtils.isNotBlank(msg)){
            msg = msg.replace("\n","<br/>");
        }
        return msg;
    }

    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("test1");
        userDTO.setPassword("test2");
        userDTO.setStatus(1);

        List<UserDTO> users = new ArrayList<>();
        users.add(userDTO);
        System.out.println(JsonFormatter.toJsonString(SherlockTableGenerator.generateTable("用户信息", users)));
    }
}
