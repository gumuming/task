package org.frame.aop;


import java.lang.annotation.*;

/**
 * 作者：Li.Wei
 * 时间：2018/8/2 11:00
 * 描述：Aop切面
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
}
