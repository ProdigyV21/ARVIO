package androidx.compose.ui.util;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u001e\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u001e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0007"}, d2 = {"lerp", "", TtmlNode.START, "stop", "fraction", "", "", "ui-util_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MathHelpersKt {
    public static final float lerp(float f10, float f11, float f12) {
        return (f12 * f11) + ((1 - f12) * f10);
    }

    public static final int lerp(int i10, int i11, float f10) {
        return a.L(((double) (i11 - i10)) * ((double) f10)) + i10;
    }

    public static final long lerp(long j10, long j11, float f10) {
        return a.N((j11 - j10) * ((double) f10)) + j10;
    }
}
