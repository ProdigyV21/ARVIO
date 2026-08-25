package com.squareup.moshi;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface j {
    boolean ignore() default false;

    String name() default "\u0000";
}
