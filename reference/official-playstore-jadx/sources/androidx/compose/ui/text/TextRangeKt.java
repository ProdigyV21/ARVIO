package androidx.compose.ui.text;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0002\b\u0004\u001a\u0013\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u001b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001a$\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"TextRange", "Landroidx/compose/ui/text/TextRange;", "index", "", "(I)J", TtmlNode.START, TtmlNode.END, "(II)J", "packWithCheck", "", "coerceIn", "minimumValue", "maximumValue", "coerceIn-8ffj60Q", "(JII)J", "substring", "", "", "range", "substring-FDrldGo", "(Ljava/lang/CharSequence;J)Ljava/lang/String;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextRangeKt {
    public static final long TextRange(int i10, int i11) {
        return TextRange.m5181constructorimpl(packWithCheck(i10, i11));
    }

    /* JADX INFO: renamed from: coerceIn-8ffj60Q, reason: not valid java name */
    public static final long m5198coerceIn8ffj60Q(long j10, int i10, int i11) {
        int iN = d.n(TextRange.m5192getStartimpl(j10), i10, i11);
        int iN2 = d.n(TextRange.m5187getEndimpl(j10), i10, i11);
        return (iN == TextRange.m5192getStartimpl(j10) && iN2 == TextRange.m5187getEndimpl(j10)) ? j10 : TextRange(iN, iN2);
    }

    private static final long packWithCheck(int i10, int i11) {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.r("start cannot be negative. [start: ", i10, ", end: ", i11, ']').toString());
        }
        if (i11 < 0) {
            throw new IllegalArgumentException(c.r("end cannot be negative. [start: ", i10, ", end: ", i11, ']').toString());
        }
        return (((long) i11) & 4294967295L) | (((long) i10) << 32);
    }

    /* JADX INFO: renamed from: substring-FDrldGo, reason: not valid java name */
    public static final String m5199substringFDrldGo(CharSequence charSequence, long j10) {
        return charSequence.subSequence(TextRange.m5190getMinimpl(j10), TextRange.m5189getMaximpl(j10)).toString();
    }

    public static final long TextRange(int i10) {
        return TextRange(i10, i10);
    }
}
