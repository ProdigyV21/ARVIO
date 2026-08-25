package dagger.hilt.internal.aliasof;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface AliasOfPropagatedData {
    Class<? extends Annotation> alias();

    Class<? extends Annotation>[] defineComponentScopes();
}
