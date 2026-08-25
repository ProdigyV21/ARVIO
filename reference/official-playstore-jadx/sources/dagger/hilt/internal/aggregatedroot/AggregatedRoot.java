package dagger.hilt.internal.aggregatedroot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface AggregatedRoot {
    String originatingRoot();

    String originatingRootPackage();

    String[] originatingRootSimpleNames();

    String root();

    Class<?> rootAnnotation();

    String rootComponentPackage();

    String[] rootComponentSimpleNames();

    String rootPackage();

    String[] rootSimpleNames();
}
