package dagger.hilt.processor.internal.aggregateddeps;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
@Retention(RetentionPolicy.CLASS)
public @interface AggregatedDeps {
    String[] componentEntryPoints() default {};

    String[] components();

    String[] entryPoints() default {};

    String[] modules() default {};

    String[] replaces() default {};

    String test() default "";
}
