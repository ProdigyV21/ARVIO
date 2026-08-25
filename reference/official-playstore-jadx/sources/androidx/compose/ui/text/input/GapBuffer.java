package androidx.compose.ui.text.input;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001e\u001a\u00020\f2\n\u0010\u001d\u001a\u00060\u001bj\u0002`\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u0004¢\u0006\u0004\b \u0010\nJ\u000f\u0010!\u001a\u00020\u0017H\u0016¢\u0006\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010$R\u0016\u0010(\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010$¨\u0006)"}, d2 = {"Landroidx/compose/ui/text/input/GapBuffer;", "", "", "initBuffer", "", "initGapStart", "initGapEnd", "<init>", "([CII)V", "gapLength", "()I", "requestSize", "Lx6/t0;", "makeSureAvailableSpace", "(I)V", TtmlNode.START, TtmlNode.END, "delete", "(II)V", "index", "", "get", "(I)C", "", "text", "replace", "(IILjava/lang/String;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "append", "(Ljava/lang/StringBuilder;)V", "length", "toString", "()Ljava/lang/String;", "capacity", "I", "buffer", "[C", "gapStart", "gapEnd", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class GapBuffer {
    private char[] buffer;
    private int capacity;
    private int gapEnd;
    private int gapStart;

    public GapBuffer(char[] cArr, int i10, int i11) {
        this.capacity = cArr.length;
        this.buffer = cArr;
        this.gapStart = i10;
        this.gapEnd = i11;
    }

    private final void delete(int start, int end) {
        int i10 = this.gapStart;
        if (start < i10 && end <= i10) {
            int i11 = i10 - end;
            char[] cArr = this.buffer;
            r.M(cArr, cArr, this.gapEnd - i11, end, i10);
            this.gapStart = start;
            this.gapEnd -= i11;
            return;
        }
        if (start < i10 && end >= i10) {
            this.gapEnd = end + gapLength();
            this.gapStart = start;
            return;
        }
        int iGapLength = start + gapLength();
        int iGapLength2 = end + gapLength();
        int i12 = this.gapEnd;
        char[] cArr2 = this.buffer;
        r.M(cArr2, cArr2, this.gapStart, i12, iGapLength);
        this.gapStart += iGapLength - i12;
        this.gapEnd = iGapLength2;
    }

    private final int gapLength() {
        return this.gapEnd - this.gapStart;
    }

    private final void makeSureAvailableSpace(int requestSize) {
        if (requestSize <= gapLength()) {
            return;
        }
        int iGapLength = requestSize - gapLength();
        int i10 = this.capacity;
        do {
            i10 *= 2;
        } while (i10 - this.capacity < iGapLength);
        char[] cArr = new char[i10];
        r.M(this.buffer, cArr, 0, 0, this.gapStart);
        int i11 = this.capacity;
        int i12 = this.gapEnd;
        int i13 = i11 - i12;
        int i14 = i10 - i13;
        r.M(this.buffer, cArr, i14, i12, i13 + i12);
        this.buffer = cArr;
        this.capacity = i10;
        this.gapEnd = i14;
    }

    public final void append(StringBuilder builder) {
        builder.append(this.buffer, 0, this.gapStart);
        char[] cArr = this.buffer;
        int i10 = this.gapEnd;
        builder.append(cArr, i10, this.capacity - i10);
    }

    public final char get(int index) {
        int i10 = this.gapStart;
        return index < i10 ? this.buffer[index] : this.buffer[(index - i10) + this.gapEnd];
    }

    public final int length() {
        return this.capacity - gapLength();
    }

    public final void replace(int start, int end, String text) {
        makeSureAvailableSpace(text.length() - (end - start));
        delete(start, end);
        GapBufferKt.toCharArray(text, this.buffer, this.gapStart);
        this.gapStart = text.length() + this.gapStart;
    }

    public String toString() {
        return "";
    }
}
