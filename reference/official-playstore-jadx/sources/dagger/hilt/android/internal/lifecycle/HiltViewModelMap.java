package dagger.hilt.android.internal.lifecycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Qualifier
@Retention(RetentionPolicy.CLASS)
public @interface HiltViewModelMap {

    @Target({ElementType.METHOD, ElementType.PARAMETER})
    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    public @interface KeySet {
    }
}
