package com.reflect;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();
	String type();
	int	length() default 0;
	boolean isnull() default false;
	String comment() default "";
}
