package dagger.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.TYPE})
public @interface ComponentDefinitionType {
    Class<?> value();
}
