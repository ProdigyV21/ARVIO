package androidx.compose.foundation.text2.input.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a*\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\u0080\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a*\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\u0080\b¢\u0006\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"", TtmlNode.RIGHT, "Lkotlin/Function0;", "defaultValue", "addExactOrElse", "(IILr7/a;)I", "subtractExactOrElse", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MathUtilsKt {
    public static final int addExactOrElse(int i10, int i11, r7.a<Integer> aVar) {
        int i12 = i10 + i11;
        return ((i10 ^ i12) & (i11 ^ i12)) < 0 ? ((Number) aVar.invoke()).intValue() : i12;
    }

    public static final int subtractExactOrElse(int i10, int i11, r7.a<Integer> aVar) {
        int i12 = i10 - i11;
        return ((i10 ^ i12) & (i11 ^ i10)) < 0 ? ((Number) aVar.invoke()).intValue() : i12;
    }
}
