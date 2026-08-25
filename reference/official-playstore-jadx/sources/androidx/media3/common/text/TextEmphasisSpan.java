package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class TextEmphasisSpan implements LanguageFeatureSpan {
    public static final int MARK_FILL_FILLED = 1;
    public static final int MARK_FILL_OPEN = 2;
    public static final int MARK_FILL_UNKNOWN = 0;
    public static final int MARK_SHAPE_CIRCLE = 1;
    public static final int MARK_SHAPE_DOT = 2;
    public static final int MARK_SHAPE_NONE = 0;
    public static final int MARK_SHAPE_SESAME = 3;
    public int markFill;
    public int markShape;
    public final int position;
    private static final String FIELD_MARK_SHAPE = Util.intToStringMaxRadix(0);
    private static final String FIELD_MARK_FILL = Util.intToStringMaxRadix(1);
    private static final String FIELD_POSITION = Util.intToStringMaxRadix(2);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface MarkFill {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface MarkShape {
    }

    public TextEmphasisSpan(int i10, int i11, int i12) {
        this.markShape = i10;
        this.markFill = i11;
        this.position = i12;
    }

    public static TextEmphasisSpan fromBundle(Bundle bundle) {
        return new TextEmphasisSpan(bundle.getInt(FIELD_MARK_SHAPE), bundle.getInt(FIELD_MARK_FILL), bundle.getInt(FIELD_POSITION));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_MARK_SHAPE, this.markShape);
        bundle.putInt(FIELD_MARK_FILL, this.markFill);
        bundle.putInt(FIELD_POSITION, this.position);
        return bundle;
    }
}
