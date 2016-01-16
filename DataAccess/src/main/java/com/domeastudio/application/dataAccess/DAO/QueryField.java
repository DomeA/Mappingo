package com.domeastudio.application.dataAccess.DAO;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by domea on 16-1-16.
 *
 * type表示查询类似，默认为equals
 * attribute表示要查询的属性，默认为空串，如果为空则为字段名称
 */
@Target(ElementType.FIELD)
public @interface QueryField {
    QueryType type() default QueryType.equals;
    String attribute() default "";
}
