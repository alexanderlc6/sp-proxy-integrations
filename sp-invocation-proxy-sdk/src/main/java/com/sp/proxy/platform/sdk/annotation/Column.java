package com.sp.proxy.platform.sdk.annotation;

import java.lang.annotation.*;

/**
 * 定义列
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    /**
     * 表头名
     *
     * @return
     */
    String label();

    int order() default 999;

    boolean isTree() default false;

    boolean ignore() default false;
}
