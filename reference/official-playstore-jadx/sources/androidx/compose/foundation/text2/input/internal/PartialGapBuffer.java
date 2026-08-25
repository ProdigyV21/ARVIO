package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J9\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0001¢\u0006\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0002\u001a\u00020\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0002\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010%\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006'"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/PartialGapBuffer;", "", "text", "<init>", "(Ljava/lang/CharSequence;)V", "", TtmlNode.START, TtmlNode.END, "textStart", "textEnd", "Lx6/t0;", "replace", "(IILjava/lang/CharSequence;II)V", "index", "", "get", "(I)C", "startIndex", "endIndex", "subSequence", "(II)Ljava/lang/CharSequence;", "", "toString", "()Ljava/lang/String;", "other", "", "contentEquals", "(Ljava/lang/CharSequence;)Z", "Ljava/lang/CharSequence;", "Landroidx/compose/foundation/text2/input/internal/GapBuffer;", "buffer", "Landroidx/compose/foundation/text2/input/internal/GapBuffer;", "bufStart", "I", "bufEnd", "getLength", "()I", "length", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PartialGapBuffer implements CharSequence {
    public static final int BUF_SIZE = 255;
    public static final int NOWHERE = -1;
    public static final int SURROUNDING_SIZE = 64;
    private GapBuffer buffer;
    private CharSequence text;
    public static final int $stable = 8;
    private int bufStart = -1;
    private int bufEnd = -1;

    public PartialGapBuffer(CharSequence charSequence) {
        this.text = charSequence;
    }

    public static /* synthetic */ void replace$default(PartialGapBuffer partialGapBuffer, int i10, int i11, CharSequence charSequence, int i12, int i13, int i14, Object obj) {
        if ((i14 & 8) != 0) {
            i12 = 0;
        }
        int i15 = i12;
        if ((i14 & 16) != 0) {
            i13 = charSequence.length();
        }
        partialGapBuffer.replace(i10, i11, charSequence, i15, i13);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i10) {
        return get(i10);
    }

    public final boolean contentEquals(CharSequence other) {
        return p.a(toString(), other.toString());
    }

    public char get(int index) {
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            return this.text.charAt(index);
        }
        if (index < this.bufStart) {
            return this.text.charAt(index);
        }
        int length = gapBuffer.length();
        int i10 = this.bufStart;
        return index < length + i10 ? gapBuffer.get(index - i10) : this.text.charAt(index - ((length - this.bufEnd) + i10));
    }

    public int getLength() {
        GapBuffer gapBuffer = this.buffer;
        return gapBuffer == null ? this.text.length() : (this.text.length() - (this.bufEnd - this.bufStart)) + gapBuffer.length();
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    public final void replace(int start, int end, CharSequence text, int textStart, int textEnd) {
        if (start > end) {
            throw new IllegalArgumentException(c.n(start, end, "start=", " > end=").toString());
        }
        if (textStart > textEnd) {
            throw new IllegalArgumentException(c.n(textStart, textEnd, "textStart=", " > textEnd=").toString());
        }
        if (start < 0) {
            throw new IllegalArgumentException(a0.c.i(start, "start must be non-negative, but was ").toString());
        }
        if (textStart < 0) {
            throw new IllegalArgumentException(a0.c.i(textStart, "textStart must be non-negative, but was ").toString());
        }
        GapBuffer gapBuffer = this.buffer;
        int i10 = textEnd - textStart;
        if (gapBuffer != null) {
            int i11 = this.bufStart;
            int i12 = start - i11;
            int i13 = end - i11;
            if (i12 >= 0 && i13 <= gapBuffer.length()) {
                gapBuffer.replace(i12, i13, text, textStart, textEnd);
                return;
            }
            this.text = toString();
            this.buffer = null;
            this.bufStart = -1;
            this.bufEnd = -1;
            replace(start, end, text, textStart, textEnd);
            return;
        }
        int iMax = Math.max(255, i10 + 128);
        char[] cArr = new char[iMax];
        int iMin = Math.min(start, 64);
        int iMin2 = Math.min(this.text.length() - end, 64);
        int i14 = start - iMin;
        ToCharArray_androidKt.toCharArray(this.text, cArr, 0, i14, start);
        int i15 = iMax - iMin2;
        int i16 = iMin2 + end;
        ToCharArray_androidKt.toCharArray(this.text, cArr, i15, end, i16);
        ToCharArray_androidKt.toCharArray(text, cArr, iMin, textStart, textEnd);
        this.buffer = new GapBuffer(cArr, iMin + i10, i15);
        this.bufStart = i14;
        this.bufEnd = i16;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int startIndex, int endIndex) {
        return toString().subSequence(startIndex, endIndex);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            return this.text.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.text, 0, this.bufStart);
        gapBuffer.append(sb2);
        CharSequence charSequence = this.text;
        sb2.append(charSequence, this.bufEnd, charSequence.length());
        return sb2.toString();
    }
}
