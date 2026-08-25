package androidx.compose.foundation.text2.input.internal;

import a0.c;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0000\u0018\u0000 D2\u00020\u0001:\u0001DB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\b\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\tJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\nH\u0086\u0002¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\u001a\u0010\u000fJ\u001d\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\u001b\u0010\u000fJ\u001d\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\u001c\u0010\u000fJ\r\u0010\u001d\u001a\u00020\r¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0002¢\u0006\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0017\u0010'\u001a\u00020&8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R*\u0010,\u001a\u00020\n2\u0006\u0010+\u001a\u00020\n8\u0006@BX\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R*\u00102\u001a\u00020\n2\u0006\u0010+\u001a\u00020\n8\u0006@BX\u0086\u000e¢\u0006\u0012\n\u0004\b2\u0010-\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R$\u00106\u001a\u00020\n2\u0006\u00105\u001a\u00020\n8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b6\u0010-\u001a\u0004\b7\u0010/R$\u00108\u001a\u00020\n2\u0006\u00105\u001a\u00020\n8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b8\u0010-\u001a\u0004\b9\u0010/R\u0019\u0010<\u001a\u0004\u0018\u00010\u00048Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0017\u0010\u0005\u001a\u00020\u00048Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b=\u0010>R$\u0010?\u001a\u00020\n2\u0006\u0010?\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b@\u0010/\"\u0004\bA\u00101R\u0011\u0010C\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bB\u0010/\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006E"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "", "Landroidx/compose/ui/text/AnnotatedString;", "text", "Landroidx/compose/ui/text/TextRange;", "selection", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;JLkotlin/jvm/internal/h;)V", "", "(Ljava/lang/String;JLkotlin/jvm/internal/h;)V", "", TtmlNode.START, TtmlNode.END, "Lx6/t0;", "checkRange", "(II)V", "", "hasComposition", "()Z", "index", "", "get", "(I)C", "", "replace", "(IILjava/lang/CharSequence;)V", "delete", "setSelection", "setComposition", "commitComposition", "()V", "toString", "()Ljava/lang/String;", "toAnnotatedString", "()Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/foundation/text2/input/internal/PartialGapBuffer;", "gapBuffer", "Landroidx/compose/foundation/text2/input/internal/PartialGapBuffer;", "Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "changeTracker", "Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "getChangeTracker", "()Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "value", "selectionStart", "I", "getSelectionStart", "()I", "setSelectionStart", "(I)V", "selectionEnd", "getSelectionEnd", "setSelectionEnd", "<set-?>", "compositionStart", "getCompositionStart", "compositionEnd", "getCompositionEnd", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "composition", "getSelection-d9O1mEE", "()J", "cursor", "getCursor", "setCursor", "getLength", "length", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EditingBuffer {
    public static final int NOWHERE = -1;
    private final ChangeTracker changeTracker;
    private int compositionEnd;
    private int compositionStart;
    private final PartialGapBuffer gapBuffer;
    private int selectionEnd;
    private int selectionStart;
    public static final int $stable = 8;

    public /* synthetic */ EditingBuffer(AnnotatedString annotatedString, long j10, h hVar) {
        this(annotatedString, j10);
    }

    private final void checkRange(int start, int end) {
        if (start < 0 || start > this.gapBuffer.length()) {
            StringBuilder sbS = c.s(start, "start (", ") offset is outside of text region ");
            sbS.append(this.gapBuffer.length());
            throw new IndexOutOfBoundsException(sbS.toString());
        }
        if (end < 0 || end > this.gapBuffer.length()) {
            StringBuilder sbS2 = c.s(end, "end (", ") offset is outside of text region ");
            sbS2.append(this.gapBuffer.length());
            throw new IndexOutOfBoundsException(sbS2.toString());
        }
    }

    private final void setSelectionEnd(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "Cannot set selectionEnd to a negative value: ").toString());
        }
        this.selectionEnd = i10;
    }

    private final void setSelectionStart(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "Cannot set selectionStart to a negative value: ").toString());
        }
        this.selectionStart = i10;
    }

    public final void commitComposition() {
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public final void delete(int start, int end) {
        checkRange(start, end);
        long jTextRange = TextRangeKt.TextRange(start, end);
        this.changeTracker.trackChange(start, end, 0);
        PartialGapBuffer.replace$default(this.gapBuffer, TextRange.m5190getMinimpl(jTextRange), TextRange.m5189getMaximpl(jTextRange), "", 0, 0, 24, null);
        long jM1085updateRangeAfterDeletepWDy79M = EditingBufferKt.m1085updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.selectionStart, this.selectionEnd), jTextRange);
        setSelectionStart(TextRange.m5192getStartimpl(jM1085updateRangeAfterDeletepWDy79M));
        setSelectionEnd(TextRange.m5187getEndimpl(jM1085updateRangeAfterDeletepWDy79M));
        if (hasComposition()) {
            long jM1085updateRangeAfterDeletepWDy79M2 = EditingBufferKt.m1085updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.compositionStart, this.compositionEnd), jTextRange);
            if (TextRange.m5186getCollapsedimpl(jM1085updateRangeAfterDeletepWDy79M2)) {
                commitComposition();
            } else {
                this.compositionStart = TextRange.m5190getMinimpl(jM1085updateRangeAfterDeletepWDy79M2);
                this.compositionEnd = TextRange.m5189getMaximpl(jM1085updateRangeAfterDeletepWDy79M2);
            }
        }
    }

    public final char get(int index) {
        return this.gapBuffer.charAt(index);
    }

    public final ChangeTracker getChangeTracker() {
        return this.changeTracker;
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m1083getCompositionMzsxiRA() {
        if (hasComposition()) {
            return TextRange.m5180boximpl(TextRangeKt.TextRange(this.compositionStart, this.compositionEnd));
        }
        return null;
    }

    public final int getCompositionEnd() {
        return this.compositionEnd;
    }

    public final int getCompositionStart() {
        return this.compositionStart;
    }

    public final int getCursor() {
        int i10 = this.selectionStart;
        int i11 = this.selectionEnd;
        if (i10 == i11) {
            return i11;
        }
        return -1;
    }

    public final int getLength() {
        return this.gapBuffer.length();
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name */
    public final long m1084getSelectiond9O1mEE() {
        return TextRangeKt.TextRange(this.selectionStart, this.selectionEnd);
    }

    public final int getSelectionEnd() {
        return this.selectionEnd;
    }

    public final int getSelectionStart() {
        return this.selectionStart;
    }

    public final boolean hasComposition() {
        return this.compositionStart != -1;
    }

    public final void replace(int start, int end, CharSequence text) {
        checkRange(start, end);
        int iMin = Math.min(start, end);
        int iMax = Math.max(start, end);
        int i10 = 0;
        int i11 = iMin;
        while (i11 < iMax && i10 < text.length() && text.charAt(i10) == this.gapBuffer.charAt(i11)) {
            i10++;
            i11++;
        }
        int length = text.length();
        int i12 = iMax;
        while (i12 > iMin && length > i10 && text.charAt(length - 1) == this.gapBuffer.charAt(i12 - 1)) {
            length--;
            i12--;
        }
        this.changeTracker.trackChange(i11, i12, length - i10);
        PartialGapBuffer.replace$default(this.gapBuffer, iMin, iMax, text, 0, 0, 24, null);
        setSelectionStart(text.length() + iMin);
        setSelectionEnd(text.length() + iMin);
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public final void setComposition(int start, int end) {
        if (start < 0 || start > this.gapBuffer.length()) {
            StringBuilder sbS = c.s(start, "start (", ") offset is outside of text region ");
            sbS.append(this.gapBuffer.length());
            throw new IndexOutOfBoundsException(sbS.toString());
        }
        if (end < 0 || end > this.gapBuffer.length()) {
            StringBuilder sbS2 = c.s(end, "end (", ") offset is outside of text region ");
            sbS2.append(this.gapBuffer.length());
            throw new IndexOutOfBoundsException(sbS2.toString());
        }
        if (start >= end) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(start, end, "Do not set reversed or empty range: ", " > "));
        }
        this.compositionStart = start;
        this.compositionEnd = end;
    }

    public final void setCursor(int i10) {
        setSelection(i10, i10);
    }

    public final void setSelection(int start, int end) {
        int iN = d.n(start, 0, getLength());
        int iN2 = d.n(end, 0, getLength());
        setSelectionStart(iN);
        setSelectionEnd(iN2);
    }

    public final AnnotatedString toAnnotatedString() {
        return new AnnotatedString(toString(), null, null, 6, null);
    }

    public String toString() {
        return this.gapBuffer.toString();
    }

    public /* synthetic */ EditingBuffer(String str, long j10, h hVar) {
        this(str, j10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EditingBuffer(AnnotatedString annotatedString, long j10) {
        this.gapBuffer = new PartialGapBuffer(annotatedString.getText());
        this.changeTracker = new ChangeTracker(null, 1, 0 == true ? 1 : 0);
        this.selectionStart = TextRange.m5192getStartimpl(j10);
        this.selectionEnd = TextRange.m5187getEndimpl(j10);
        this.compositionStart = -1;
        this.compositionEnd = -1;
        checkRange(TextRange.m5192getStartimpl(j10), TextRange.m5187getEndimpl(j10));
    }

    private EditingBuffer(String str, long j10) {
        this(new AnnotatedString(str, null, null, 6, null), j10, (h) null);
    }
}
