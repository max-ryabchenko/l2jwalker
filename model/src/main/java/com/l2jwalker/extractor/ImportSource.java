package com.l2jwalker.extractor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ImportSource {

    java.lang.String name() default "";

}
