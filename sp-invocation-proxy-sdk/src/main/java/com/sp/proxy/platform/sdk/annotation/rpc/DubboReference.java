package com.sp.proxy.platform.sdk.annotation.rpc;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DubboReference {

    /**
     * 接口全路径
     * @return
     */
    String originInterfaceName();

    /**
     * 版本号
     * @return
     */
    String version() default "1.0";

    String group() default "";

    String url() default "";

}
