package dagger.hilt;

import dagger.hilt.internal.definecomponent.DefineComponentNoParent;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface DefineComponent {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    public @interface Builder {
    }

    Class<?> parent() default DefineComponentNoParent.class;
}
