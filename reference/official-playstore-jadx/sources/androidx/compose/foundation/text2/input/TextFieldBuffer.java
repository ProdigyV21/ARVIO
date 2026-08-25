package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.c;
import androidx.compose.foundation.text2.input.internal.ChangeTracker;
import androidx.compose.foundation.text2.input.internal.PartialGapBuffer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001iB'\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ%\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J;\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\rH\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001a\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ-\u0010\u001a\u001a\u00060\u0001j\u0002`\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001a\u0010\u001cJ\u001b\u0010\u001a\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001a\u0010\u001fJ\u0015\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\n¢\u0006\u0004\b!\u0010\"J\u000f\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\r¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\u000f¢\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\n¢\u0006\u0004\b*\u0010+J\u0015\u0010,\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\n¢\u0006\u0004\b,\u0010+J\u0015\u0010-\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\n¢\u0006\u0004\b-\u0010+J\u0015\u0010.\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\n¢\u0006\u0004\b.\u0010+J\u0018\u00103\u001a\u00020\u000f2\u0006\u00100\u001a\u00020/ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u0018\u00105\u001a\u00020\u000f2\u0006\u00100\u001a\u00020/ø\u0001\u0000¢\u0006\u0004\b4\u00102J\u001e\u00109\u001a\u00020\u00032\n\b\u0002\u00106\u001a\u0004\u0018\u00010/H\u0000ø\u0001\u0000¢\u0006\u0004\b7\u00108J'\u0010=\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020\n2\u0006\u0010<\u001a\u00020\nH\u0002¢\u0006\u0004\b=\u0010>J\u000f\u0010?\u001a\u00020\u000fH\u0002¢\u0006\u0004\b?\u0010)J/\u0010D\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\n2\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020@H\u0002¢\u0006\u0004\bD\u0010EJ\"\u0010H\u001a\u00020\u000f2\u0006\u00100\u001a\u00020/2\u0006\u0010C\u001a\u00020@H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001a\u0010K\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001a\u0010M\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\bL\u0010JJ\u0017\u0010N\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002¢\u0006\u0004\bN\u0010OJ\u0017\u0010P\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002¢\u0006\u0004\bP\u0010OR\u0014\u0010\u0007\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010U\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR*\u0010X\u001a\u00020/2\u0006\u0010W\u001a\u00020/8\u0006@BX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[R\u0011\u0010^\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0011\u0010`\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b_\u0010]R\u0011\u0010d\u001a\u00020a8F¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0011\u0010e\u001a\u00020@8G¢\u0006\u0006\u001a\u0004\be\u0010fR\u0017\u0010h\u001a\u00020/8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bg\u0010[\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006j"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "initialValue", "Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "initialChanges", "sourceValue", "<init>", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/internal/ChangeTracker;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;)V", "", TtmlNode.START, TtmlNode.END, "", "text", "Lx6/t0;", "replace", "(IILjava/lang/CharSequence;)V", "textStart", "textEnd", "replace$foundation_release", "(IILjava/lang/CharSequence;II)V", "newText", "setTextIfChanged$foundation_release", "(Ljava/lang/CharSequence;)V", "setTextIfChanged", "append", "(Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "(Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "", "char", "(C)Ljava/lang/Appendable;", "index", "charAt", "(I)C", "", "toString", "()Ljava/lang/String;", "asCharSequence", "()Ljava/lang/CharSequence;", "revertAllChanges", "()V", "placeCursorBeforeCodepointAt", "(I)V", "placeCursorBeforeCharAt", "placeCursorAfterCodepointAt", "placeCursorAfterCharAt", "Landroidx/compose/ui/text/TextRange;", "range", "selectCodepointsIn-5zc-tL8", "(J)V", "selectCodepointsIn", "selectCharsIn-5zc-tL8", "selectCharsIn", "composition", "toTextFieldCharSequence-OEnZFl4$foundation_release", "(Landroidx/compose/ui/text/TextRange;)Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "toTextFieldCharSequence", "replaceStart", "replaceEnd", "newLength", "onTextWillChange", "(III)V", "clearChangeList", "", "startExclusive", "endExclusive", "inCodepoints", "requireValidIndex", "(IZZZ)V", "requireValidRange-72CqOWE", "(JZ)V", "requireValidRange", "codepointsToChars-GEjPoXI", "(J)J", "codepointsToChars", "charsToCodepoints-GEjPoXI", "charsToCodepoints", "codepointIndexToCharIndex", "(I)I", "charIndexToCodepointIndex", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "Landroidx/compose/foundation/text2/input/internal/PartialGapBuffer;", "buffer", "Landroidx/compose/foundation/text2/input/internal/PartialGapBuffer;", "changeTracker", "Landroidx/compose/foundation/text2/input/internal/ChangeTracker;", "<set-?>", "selectionInChars", "J", "getSelectionInChars-d9O1mEE", "()J", "getLength", "()I", "length", "getCodepointLength", "codepointLength", "Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "getChanges", "()Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "changes", "hasSelection", "()Z", "getSelectionInCodepoints-d9O1mEE", "selectionInCodepoints", "ChangeList", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldBuffer implements Appendable {
    public static final int $stable = 8;
    private final PartialGapBuffer buffer;
    private ChangeTracker changeTracker;
    private long selectionInChars;
    private final TextFieldCharSequence sourceValue;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/TextFieldBuffer$ChangeList;", "", "changeCount", "", "getChangeCount", "()I", "getOriginalRange", "Landroidx/compose/ui/text/TextRange;", "changeIndex", "getOriginalRange--jx7JFs", "(I)J", "getRange", "getRange--jx7JFs", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ChangeList {
        int getChangeCount();

        /* JADX INFO: renamed from: getOriginalRange--jx7JFs */
        long mo1053getOriginalRangejx7JFs(int changeIndex);

        /* JADX INFO: renamed from: getRange--jx7JFs */
        long mo1054getRangejx7JFs(int changeIndex);
    }

    public TextFieldBuffer(TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2) {
        this.sourceValue = textFieldCharSequence2;
        this.buffer = new PartialGapBuffer(textFieldCharSequence);
        this.changeTracker = changeTracker != null ? new ChangeTracker(changeTracker) : null;
        this.selectionInChars = textFieldCharSequence.getSelectionInChars();
    }

    private final int charIndexToCodepointIndex(int index) {
        return index;
    }

    /* JADX INFO: renamed from: charsToCodepoints-GEjPoXI, reason: not valid java name */
    private final long m1055charsToCodepointsGEjPoXI(long range) {
        return TextRangeKt.TextRange(charIndexToCodepointIndex(TextRange.m5192getStartimpl(range)), charIndexToCodepointIndex(TextRange.m5187getEndimpl(range)));
    }

    private final void clearChangeList() {
        ChangeTracker changeTracker = this.changeTracker;
        if (changeTracker != null) {
            changeTracker.clearChanges();
        }
    }

    private final int codepointIndexToCharIndex(int index) {
        return index;
    }

    /* JADX INFO: renamed from: codepointsToChars-GEjPoXI, reason: not valid java name */
    private final long m1056codepointsToCharsGEjPoXI(long range) {
        return TextRangeKt.TextRange(codepointIndexToCharIndex(TextRange.m5192getStartimpl(range)), codepointIndexToCharIndex(TextRange.m5187getEndimpl(range)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void onTextWillChange(int replaceStart, int replaceEnd, int newLength) {
        int i10;
        ChangeTracker changeTracker = this.changeTracker;
        if (changeTracker == null) {
            changeTracker = new ChangeTracker(null, 1, 0 == true ? 1 : 0);
            this.changeTracker = changeTracker;
        }
        changeTracker.trackChange(replaceStart, replaceEnd, newLength);
        int iMin = Math.min(replaceStart, replaceEnd);
        int iMax = Math.max(replaceStart, replaceEnd);
        int iM5190getMinimpl = TextRange.m5190getMinimpl(this.selectionInChars);
        int iM5189getMaximpl = TextRange.m5189getMaximpl(this.selectionInChars);
        if (iM5189getMaximpl < iMin) {
            return;
        }
        if (iM5190getMinimpl <= iMin && iMax <= iM5189getMaximpl) {
            i10 = newLength - (iMax - iMin);
            if (iM5190getMinimpl == iM5189getMaximpl) {
            }
            iMin = iM5189getMaximpl + i10;
            this.selectionInChars = TextRangeKt.TextRange(iM5190getMinimpl, iMin);
        }
        if (iM5190getMinimpl > iMin && iM5189getMaximpl < iMax) {
            iMin += newLength;
            iM5190getMinimpl = iMin;
        } else if (iM5190getMinimpl >= iMax) {
            i10 = newLength - (iMax - iMin);
        } else if (iMin < iM5190getMinimpl) {
            iM5190getMinimpl = iMin + newLength;
            iMin = (newLength - (iMax - iMin)) + iM5189getMaximpl;
        }
        this.selectionInChars = TextRangeKt.TextRange(iM5190getMinimpl, iMin);
        iM5190getMinimpl += i10;
        iMin = iM5189getMaximpl + i10;
        this.selectionInChars = TextRangeKt.TextRange(iM5190getMinimpl, iMin);
    }

    public static /* synthetic */ void replace$foundation_release$default(TextFieldBuffer textFieldBuffer, int i10, int i11, CharSequence charSequence, int i12, int i13, int i14, Object obj) {
        if ((i14 & 8) != 0) {
            i12 = 0;
        }
        int i15 = i12;
        if ((i14 & 16) != 0) {
            i13 = charSequence.length();
        }
        textFieldBuffer.replace$foundation_release(i10, i11, charSequence, i15, i13);
    }

    private final void requireValidIndex(int index, boolean startExclusive, boolean endExclusive, boolean inCodepoints) {
        int iCharIndexToCodepointIndex = startExclusive ? 0 : -1;
        int length = endExclusive ? getLength() : getLength() + 1;
        if (inCodepoints) {
            iCharIndexToCodepointIndex = charIndexToCodepointIndex(iCharIndexToCodepointIndex);
            length = charIndexToCodepointIndex(length);
        }
        if (iCharIndexToCodepointIndex > index || index >= length) {
            String str = inCodepoints ? "codepoints" : "chars";
            StringBuilder sbV = c.v("Expected ", index, " to be in [", iCharIndexToCodepointIndex, ", ");
            sbV.append(length);
            sbV.append(") ");
            sbV.append(str);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
    }

    /* JADX INFO: renamed from: requireValidRange-72CqOWE, reason: not valid java name */
    private final void m1057requireValidRange72CqOWE(long range, boolean inCodepoints) {
        long jTextRange = TextRangeKt.TextRange(0, getLength());
        if (inCodepoints) {
            jTextRange = m1055charsToCodepointsGEjPoXI(jTextRange);
        }
        if (TextRange.m5182contains5zctL8(jTextRange, range)) {
            return;
        }
        throw new IllegalArgumentException(("Expected " + ((Object) TextRange.m5195toStringimpl(range)) + " to be in " + ((Object) TextRange.m5195toStringimpl(jTextRange)) + " (" + (inCodepoints ? "codepoints" : "chars") + ')').toString());
    }

    /* JADX INFO: renamed from: toTextFieldCharSequence-OEnZFl4$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ TextFieldCharSequence m1058toTextFieldCharSequenceOEnZFl4$foundation_release$default(TextFieldBuffer textFieldBuffer, TextRange textRange, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            textRange = null;
        }
        return textFieldBuffer.m1063toTextFieldCharSequenceOEnZFl4$foundation_release(textRange);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text) {
        if (text != null) {
            onTextWillChange(getLength(), getLength(), text.length());
            PartialGapBuffer partialGapBuffer = this.buffer;
            PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), text, 0, 0, 24, null);
        }
        return this;
    }

    public final CharSequence asCharSequence() {
        return this.buffer;
    }

    public final char charAt(int index) {
        return this.buffer.charAt(index);
    }

    public final ChangeList getChanges() {
        ChangeTracker changeTracker = this.changeTracker;
        return changeTracker != null ? changeTracker : EmptyChangeList.INSTANCE;
    }

    public final int getCodepointLength() {
        return Character.codePointCount(this.buffer, 0, getLength());
    }

    public final int getLength() {
        return this.buffer.length();
    }

    /* JADX INFO: renamed from: getSelectionInChars-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelectionInChars() {
        return this.selectionInChars;
    }

    /* JADX INFO: renamed from: getSelectionInCodepoints-d9O1mEE, reason: not valid java name */
    public final long m1060getSelectionInCodepointsd9O1mEE() {
        return m1055charsToCodepointsGEjPoXI(this.selectionInChars);
    }

    public final boolean hasSelection() {
        return !TextRange.m5186getCollapsedimpl(this.selectionInChars);
    }

    public final void placeCursorAfterCharAt(int index) {
        requireValidIndex(index, false, true, false);
        int i10 = index + 1;
        int length = getLength();
        if (i10 > length) {
            i10 = length;
        }
        this.selectionInChars = TextRangeKt.TextRange(i10);
    }

    public final void placeCursorAfterCodepointAt(int index) {
        requireValidIndex(index, false, true, true);
        int i10 = index + 1;
        int codepointLength = getCodepointLength();
        if (i10 > codepointLength) {
            i10 = codepointLength;
        }
        this.selectionInChars = TextRangeKt.TextRange(codepointIndexToCharIndex(i10));
    }

    public final void placeCursorBeforeCharAt(int index) {
        requireValidIndex(index, true, false, false);
        this.selectionInChars = TextRangeKt.TextRange(index);
    }

    public final void placeCursorBeforeCodepointAt(int index) {
        requireValidIndex(index, true, false, true);
        this.selectionInChars = TextRangeKt.TextRange(codepointIndexToCharIndex(index));
    }

    public final void replace(int start, int end, CharSequence text) {
        replace$foundation_release(start, end, text, 0, text.length());
    }

    public final void replace$foundation_release(int start, int end, CharSequence text, int textStart, int textEnd) {
        if (start > end) {
            throw new IllegalArgumentException(c.n(start, end, "Expected start=", " <= end=").toString());
        }
        if (textStart > textEnd) {
            throw new IllegalArgumentException(c.n(textStart, textEnd, "Expected textStart=", " <= textEnd=").toString());
        }
        onTextWillChange(start, end, textEnd - textStart);
        this.buffer.replace(start, end, text, textStart, textEnd);
    }

    public final void revertAllChanges() {
        replace(0, getLength(), this.sourceValue.toString());
        this.selectionInChars = this.sourceValue.getSelectionInChars();
        clearChangeList();
    }

    /* JADX INFO: renamed from: selectCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1061selectCharsIn5zctL8(long range) {
        m1057requireValidRange72CqOWE(range, false);
        this.selectionInChars = range;
    }

    /* JADX INFO: renamed from: selectCodepointsIn-5zc-tL8, reason: not valid java name */
    public final void m1062selectCodepointsIn5zctL8(long range) {
        m1057requireValidRange72CqOWE(range, true);
        this.selectionInChars = m1056codepointsToCharsGEjPoXI(range);
    }

    public final void setTextIfChanged$foundation_release(CharSequence newText) {
        int i10;
        int i11;
        PartialGapBuffer partialGapBuffer = this.buffer;
        int length = partialGapBuffer.length();
        int length2 = newText.length();
        boolean z = false;
        if (partialGapBuffer.length() <= 0 || newText.length() <= 0) {
            i10 = 0;
            i11 = 0;
        } else {
            int i12 = 0;
            int i13 = 0;
            boolean z5 = false;
            while (true) {
                if (!z) {
                    if (partialGapBuffer.charAt(i12) == newText.charAt(i13)) {
                        i12++;
                        i13++;
                    } else {
                        z = true;
                    }
                }
                if (!z5) {
                    if (partialGapBuffer.charAt(length - 1) == newText.charAt(length2 - 1)) {
                        length--;
                        length2--;
                    } else {
                        z5 = true;
                    }
                }
                if (i12 >= length || i13 >= length2 || (z && z5)) {
                    break;
                }
            }
            i10 = i12;
            i11 = i13;
        }
        int i14 = length;
        int i15 = length2;
        if (i10 < i14 || i11 < i15) {
            replace$foundation_release(i10, i14, newText, i11, i15);
        }
    }

    public String toString() {
        return this.buffer.toString();
    }

    /* JADX INFO: renamed from: toTextFieldCharSequence-OEnZFl4$foundation_release, reason: not valid java name */
    public final TextFieldCharSequence m1063toTextFieldCharSequenceOEnZFl4$foundation_release(TextRange composition) {
        return TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(this.buffer.toString(), this.selectionInChars, composition);
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence text, int start, int end) {
        if (text != null) {
            onTextWillChange(getLength(), getLength(), end - start);
            PartialGapBuffer partialGapBuffer = this.buffer;
            PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), text.subSequence(start, end), 0, 0, 24, null);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(char c10) {
        onTextWillChange(getLength(), getLength(), 1);
        PartialGapBuffer partialGapBuffer = this.buffer;
        PartialGapBuffer.replace$default(partialGapBuffer, partialGapBuffer.length(), this.buffer.length(), String.valueOf(c10), 0, 0, 24, null);
        return this;
    }

    public /* synthetic */ TextFieldBuffer(TextFieldCharSequence textFieldCharSequence, ChangeTracker changeTracker, TextFieldCharSequence textFieldCharSequence2, int i10, h hVar) {
        this(textFieldCharSequence, (i10 & 2) != 0 ? null : changeTracker, (i10 & 4) != 0 ? textFieldCharSequence : textFieldCharSequence2);
    }
}
