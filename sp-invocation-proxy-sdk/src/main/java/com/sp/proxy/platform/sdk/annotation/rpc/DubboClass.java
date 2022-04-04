package com.sp.proxy.platform.sdk.annotation.rpc;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DubboClass {

    String originClass() default "";

}
