package androidx.media3.common.text;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class TextAnnotation {
    public static final int POSITION_AFTER = 2;
    public static final int POSITION_BEFORE = 1;
    public static final int POSITION_UNKNOWN = -1;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }

    private TextAnnotation() {
    }
}
