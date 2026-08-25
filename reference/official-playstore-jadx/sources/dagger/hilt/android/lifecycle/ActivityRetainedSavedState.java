package dagger.hilt.android.lifecycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Qualifier
@Retention(RetentionPolicy.CLASS)
public @interface ActivityRetainedSavedState {
}
