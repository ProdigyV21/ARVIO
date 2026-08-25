package androidx.media3.common.text;

import android.text.Spannable;
import android.text.style.RelativeSizeSpan;

/* JADX INFO: loaded from: classes3.dex */
public final class SpanUtil {
    private SpanUtil() {
    }

    public static void addInheritedRelativeSizeSpan(Spannable spannable, float f10, int i10, int i11, int i12) {
        for (RelativeSizeSpan relativeSizeSpan : (RelativeSizeSpan[]) spannable.getSpans(i10, i11, RelativeSizeSpan.class)) {
            if (spannable.getSpanStart(relativeSizeSpan) <= i10 && spannable.getSpanEnd(relativeSizeSpan) >= i11) {
                f10 = relativeSizeSpan.getSizeChange() * f10;
            }
            removeIfStartEndAndFlagsMatch(spannable, relativeSizeSpan, i10, i11, i12);
        }
        spannable.setSpan(new RelativeSizeSpan(f10), i10, i11, i12);
    }

    public static void addOrReplaceSpan(Spannable spannable, Object obj, int i10, int i11, int i12) {
        for (Object obj2 : spannable.getSpans(i10, i11, obj.getClass())) {
            removeIfStartEndAndFlagsMatch(spannable, obj2, i10, i11, i12);
        }
        spannable.setSpan(obj, i10, i11, i12);
    }

    private static void removeIfStartEndAndFlagsMatch(Spannable spannable, Object obj, int i10, int i11, int i12) {
        if (spannable.getSpanStart(obj) == i10 && spannable.getSpanEnd(obj) == i11 && spannable.getSpanFlags(obj) == i12) {
            spannable.removeSpan(obj);
        }
    }
}
