package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.c;
import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 _2\u00020\u0001:\u0001_B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u0010\u001a\u00020\u000f2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0013J\r\u0010\u0015\u001a\u00020\u0000¢\u0006\u0004\b\u0015\u0010\u0013J\r\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0013J\r\u0010\u0017\u001a\u00020\u0000¢\u0006\u0004\b\u0017\u0010\u0013J\r\u0010\u0018\u001a\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0013J&\u0010\u001c\u001a\u00020\u00002\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ&\u0010\u001e\u001a\u00020\u00002\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001a¢\u0006\u0004\b\u001e\u0010\u001dJ\r\u0010 \u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u001f¢\u0006\u0004\b\"\u0010!J\r\u0010#\u001a\u00020\u0000¢\u0006\u0004\b#\u0010\u0013J\r\u0010$\u001a\u00020\u0000¢\u0006\u0004\b$\u0010\u0013J\r\u0010%\u001a\u00020\u0000¢\u0006\u0004\b%\u0010\u0013J\r\u0010&\u001a\u00020\u0000¢\u0006\u0004\b&\u0010\u0013J\r\u0010'\u001a\u00020\u001f¢\u0006\u0004\b'\u0010!J\r\u0010(\u001a\u00020\u001f¢\u0006\u0004\b(\u0010!J\r\u0010)\u001a\u00020\u0000¢\u0006\u0004\b)\u0010\u0013J\r\u0010*\u001a\u00020\u0000¢\u0006\u0004\b*\u0010\u0013J\r\u0010+\u001a\u00020\u0000¢\u0006\u0004\b+\u0010\u0013J\r\u0010,\u001a\u00020\u0000¢\u0006\u0004\b,\u0010\u0013J\r\u0010-\u001a\u00020\u001f¢\u0006\u0004\b-\u0010!J\r\u0010.\u001a\u00020\u0000¢\u0006\u0004\b.\u0010\u0013J\r\u0010/\u001a\u00020\u001f¢\u0006\u0004\b/\u0010!J\r\u00100\u001a\u00020\u0000¢\u0006\u0004\b0\u0010\u0013J\r\u00101\u001a\u00020\u0000¢\u0006\u0004\b1\u0010\u0013J\r\u00102\u001a\u00020\u0000¢\u0006\u0004\b2\u0010\u0013J\r\u00103\u001a\u00020\u0000¢\u0006\u0004\b3\u0010\u0013J\u0017\u00105\u001a\u00020\u001f2\u0006\u00104\u001a\u00020\u001fH\u0002¢\u0006\u0004\b5\u00106J3\u00109\u001a\u00020\u00002\b\b\u0002\u00108\u001a\u0002072\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000f0\u0019¢\u0006\u0002\b\u001aH\u0082\b¢\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020\u001fH\u0002¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\u0000H\u0002¢\u0006\u0004\b>\u0010\u0013J\u000f\u0010?\u001a\u00020\u0000H\u0002¢\u0006\u0004\b?\u0010\u0013J\u000f\u0010@\u001a\u00020\u0000H\u0002¢\u0006\u0004\b@\u0010\u0013J\u000f\u0010A\u001a\u00020\u0000H\u0002¢\u0006\u0004\bA\u0010\u0013J\u000f\u0010B\u001a\u000207H\u0002¢\u0006\u0004\bB\u0010CJ\u001e\u0010E\u001a\u00020\u001f*\u00020\u00042\b\b\u0002\u0010D\u001a\u00020\u001fH\u0082\u0010¢\u0006\u0004\bE\u0010FJ\u001e\u0010G\u001a\u00020\u001f*\u00020\u00042\b\b\u0002\u0010D\u001a\u00020\u001fH\u0082\u0010¢\u0006\u0004\bG\u0010FJ\u001d\u0010H\u001a\u00020\u001f*\u00020\u00042\b\b\u0002\u0010D\u001a\u00020\u001fH\u0002¢\u0006\u0004\bH\u0010FJ\u001d\u0010I\u001a\u00020\u001f*\u00020\u00042\b\b\u0002\u0010D\u001a\u00020\u001fH\u0002¢\u0006\u0004\bI\u0010FJ\u001b\u0010K\u001a\u00020\u001f*\u00020\u00042\u0006\u0010J\u001a\u00020\u001fH\u0002¢\u0006\u0004\bK\u0010FJ\u0017\u0010L\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u001fH\u0002¢\u0006\u0004\bL\u00106R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010MR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010NR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010OR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010PR\u0017\u0010R\u001a\u00020Q8\u0006¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010UR(\u0010V\u001a\u00020\r8\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bV\u0010W\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006`"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelection;", "", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "state", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "", "visibleTextLayoutHeight", "Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelectionState;", "textPreparedSelectionState", "<init>", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextLayoutResult;FLandroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelectionState;)V", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextRange;", "block", "Lx6/t0;", "deleteIfSelectedOr", "(Lr7/a;)V", "moveCursorUpByPage", "()Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelection;", "moveCursorDownByPage", "selectAll", "deselect", "moveCursorLeft", "moveCursorRight", "Lkotlin/Function1;", "Lx6/n;", "or", "collapseLeftOr", "(Lr7/l;)Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelection;", "collapseRightOr", "", "getPrecedingCharacterIndex", "()I", "getNextCharacterIndex", "moveCursorToHome", "moveCursorToEnd", "moveCursorLeftByWord", "moveCursorRightByWord", "getNextWordOffset", "getPreviousWordOffset", "moveCursorPrevByParagraph", "moveCursorNextByParagraph", "moveCursorUpByLine", "moveCursorDownByLine", "getLineStartByOffset", "moveCursorToLineStart", "getLineEndByOffset", "moveCursorToLineEnd", "moveCursorToLineLeftSide", "moveCursorToLineRightSide", "selectMovement", "pagesAmount", "jumpByPagesOffset", "(I)I", "", "resetCachedX", "applyIfNotEmpty", "(ZLr7/l;)Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelection;", "offset", "setCursor", "(I)V", "moveCursorPrev", "moveCursorNext", "moveCursorNextByWord", "moveCursorPrevByWord", "isLtr", "()Z", "currentOffset", "getNextWordOffsetForLayout", "(Landroidx/compose/ui/text/TextLayoutResult;I)I", "getPrevWordOffsetForLayout", "getLineStartByOffsetForLayout", "getLineEndByOffsetForLayout", "linesAmount", "jumpByLinesOffset", "charOffset", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/ui/text/TextLayoutResult;", "F", "Landroidx/compose/foundation/text2/input/internal/TextFieldPreparedSelectionState;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "initialValue", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "getInitialValue", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "selection", "J", "getSelection-d9O1mEE", "()J", "setSelection-5zc-tL8", "(J)V", "", "text", "Ljava/lang/String;", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldPreparedSelection {
    public static final int NoCharacterFound = -1;
    private final TextFieldCharSequence initialValue;
    private long selection;
    private final TransformedTextFieldState state;
    private final String text;
    private final TextLayoutResult textLayoutResult;
    private final TextFieldPreparedSelectionState textPreparedSelectionState;
    private final float visibleTextLayoutHeight;
    public static final int $stable = 8;

    public TextFieldPreparedSelection(TransformedTextFieldState transformedTextFieldState, TextLayoutResult textLayoutResult, float f10, TextFieldPreparedSelectionState textFieldPreparedSelectionState) {
        this.state = transformedTextFieldState;
        this.textLayoutResult = textLayoutResult;
        this.visibleTextLayoutHeight = f10;
        this.textPreparedSelectionState = textFieldPreparedSelectionState;
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                TextFieldCharSequence text = transformedTextFieldState.getText();
                snapshotCreateNonObservableSnapshot.dispose();
                this.initialValue = text;
                this.selection = text.getSelectionInChars();
                this.text = text.toString();
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } catch (Throwable th) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th;
        }
    }

    private final TextFieldPreparedSelection applyIfNotEmpty(boolean resetCachedX, l<? super TextFieldPreparedSelection, t0> block) {
        if (resetCachedX) {
            this.textPreparedSelectionState.resetCachedX();
        }
        if (this.text.length() > 0) {
            block.invoke(this);
        }
        return this;
    }

    public static /* synthetic */ TextFieldPreparedSelection applyIfNotEmpty$default(TextFieldPreparedSelection textFieldPreparedSelection, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        if (z) {
            textFieldPreparedSelection.textPreparedSelectionState.resetCachedX();
        }
        if (textFieldPreparedSelection.text.length() > 0) {
            lVar.invoke(textFieldPreparedSelection);
        }
        return textFieldPreparedSelection;
    }

    private final int charOffset(int offset) {
        int length = this.text.length() - 1;
        return offset > length ? length : offset;
    }

    private final int getLineEndByOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        return textLayoutResult.getLineEnd(textLayoutResult.getLineForOffset(i10), true);
    }

    public static /* synthetic */ int getLineEndByOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = TextRange.m5189getMaximpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getLineEndByOffsetForLayout(textLayoutResult, i10);
    }

    private final int getLineStartByOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        return textLayoutResult.getLineStart(textLayoutResult.getLineForOffset(i10));
    }

    public static /* synthetic */ int getLineStartByOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = TextRange.m5190getMinimpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getLineStartByOffsetForLayout(textLayoutResult, i10);
    }

    private final int getNextWordOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        while (i10 < this.initialValue.length()) {
            long jM5166getWordBoundaryjx7JFs = textLayoutResult.m5166getWordBoundaryjx7JFs(charOffset(i10));
            if (TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs) > i10) {
                return TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs);
            }
            i10++;
        }
        return this.initialValue.length();
    }

    public static /* synthetic */ int getNextWordOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = TextRange.m5187getEndimpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getNextWordOffsetForLayout(textLayoutResult, i10);
    }

    private final int getPrevWordOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        while (i10 > 0) {
            long jM5166getWordBoundaryjx7JFs = textLayoutResult.m5166getWordBoundaryjx7JFs(charOffset(i10));
            if (TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs) < i10) {
                return TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs);
            }
            i10--;
        }
        return 0;
    }

    public static /* synthetic */ int getPrevWordOffsetForLayout$default(TextFieldPreparedSelection textFieldPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = TextRange.m5187getEndimpl(textFieldPreparedSelection.selection);
        }
        return textFieldPreparedSelection.getPrevWordOffsetForLayout(textLayoutResult, i10);
    }

    private final boolean isLtr() {
        return this.textLayoutResult.getParagraphDirection(TextRange.m5187getEndimpl(this.selection)) == ResolvedTextDirection.Ltr;
    }

    private final int jumpByLinesOffset(TextLayoutResult textLayoutResult, int i10) {
        int iM5187getEndimpl = TextRange.m5187getEndimpl(this.selection);
        if (Float.isNaN(this.textPreparedSelectionState.getCachedX())) {
            this.textPreparedSelectionState.setCachedX(textLayoutResult.getCursorRect(iM5187getEndimpl).getLeft());
        }
        int lineForOffset = textLayoutResult.getLineForOffset(iM5187getEndimpl) + i10;
        if (lineForOffset < 0) {
            return 0;
        }
        if (lineForOffset >= textLayoutResult.getLineCount()) {
            return this.text.length();
        }
        float lineBottom = textLayoutResult.getLineBottom(lineForOffset) - 1;
        float cachedX = this.textPreparedSelectionState.getCachedX();
        return ((!isLtr() || cachedX < textLayoutResult.getLineRight(lineForOffset)) && (isLtr() || cachedX > textLayoutResult.getLineLeft(lineForOffset))) ? textLayoutResult.m5164getOffsetForPositionk4lQ0M(OffsetKt.Offset(cachedX, lineBottom)) : textLayoutResult.getLineEnd(lineForOffset, true);
    }

    private final int jumpByPagesOffset(int pagesAmount) {
        Rect rectTranslate = this.textLayoutResult.getCursorRect(TextRange.m5187getEndimpl(this.initialValue.getSelectionInChars())).translate(0.0f, this.visibleTextLayoutHeight * pagesAmount);
        float lineBottom = this.textLayoutResult.getLineBottom(this.textLayoutResult.getLineForVerticalPosition(rectTranslate.getTop()));
        return Math.abs(rectTranslate.getTop() - lineBottom) > Math.abs(rectTranslate.getBottom() - lineBottom) ? this.textLayoutResult.m5164getOffsetForPositionk4lQ0M(rectTranslate.m3271getTopLeftF1C5BW0()) : this.textLayoutResult.m5164getOffsetForPositionk4lQ0M(rectTranslate.m3264getBottomLeftF1C5BW0());
    }

    private final TextFieldPreparedSelection moveCursorNext() {
        int nextCharacterIndex;
        if (c.e(this, this) > 0 && (nextCharacterIndex = getNextCharacterIndex()) != -1) {
            setCursor(nextCharacterIndex);
        }
        return this;
    }

    private final TextFieldPreparedSelection moveCursorNextByWord() {
        if (c.e(this, this) > 0) {
            setCursor(getNextWordOffset());
        }
        return this;
    }

    private final TextFieldPreparedSelection moveCursorPrev() {
        int precedingCharacterIndex;
        if (c.e(this, this) > 0 && (precedingCharacterIndex = getPrecedingCharacterIndex()) != -1) {
            setCursor(precedingCharacterIndex);
        }
        return this;
    }

    private final TextFieldPreparedSelection moveCursorPrevByWord() {
        if (c.e(this, this) > 0) {
            setCursor(getPreviousWordOffset());
        }
        return this;
    }

    private final void setCursor(int offset) {
        this.selection = TextRangeKt.TextRange(offset, offset);
    }

    public final TextFieldPreparedSelection collapseLeftOr(l<? super TextFieldPreparedSelection, t0> or) {
        if (c.e(this, this) > 0) {
            if (TextRange.m5186getCollapsedimpl(this.selection)) {
                or.invoke(this);
                return this;
            }
            if (isLtr()) {
                setCursor(TextRange.m5190getMinimpl(this.selection));
                return this;
            }
            setCursor(TextRange.m5189getMaximpl(this.selection));
        }
        return this;
    }

    public final TextFieldPreparedSelection collapseRightOr(l<? super TextFieldPreparedSelection, t0> or) {
        if (c.e(this, this) > 0) {
            if (TextRange.m5186getCollapsedimpl(this.selection)) {
                or.invoke(this);
                return this;
            }
            if (isLtr()) {
                setCursor(TextRange.m5189getMaximpl(this.selection));
                return this;
            }
            setCursor(TextRange.m5190getMinimpl(this.selection));
        }
        return this;
    }

    public final void deleteIfSelectedOr(r7.a<TextRange> block) {
        if (!TextRange.m5186getCollapsedimpl(getSelection())) {
            TransformedTextFieldState.m1135replaceTextSbBc2M$default(this.state, "", getSelection(), null, 4, null);
            return;
        }
        TextRange textRange = (TextRange) block.invoke();
        if (textRange != null) {
            TransformedTextFieldState.m1135replaceTextSbBc2M$default(this.state, "", textRange.getPackedValue(), null, 4, null);
        }
    }

    public final TextFieldPreparedSelection deselect() {
        if (c.e(this, this) > 0) {
            setCursor(TextRange.m5187getEndimpl(this.selection));
        }
        return this;
    }

    public final TextFieldCharSequence getInitialValue() {
        return this.initialValue;
    }

    public final int getLineEndByOffset() {
        return getLineEndByOffsetForLayout$default(this, this.textLayoutResult, 0, 1, null);
    }

    public final int getLineStartByOffset() {
        return getLineStartByOffsetForLayout$default(this, this.textLayoutResult, 0, 1, null);
    }

    public final int getNextCharacterIndex() {
        return StringHelpers_androidKt.findFollowingBreak(this.text, TextRange.m5187getEndimpl(this.selection));
    }

    public final int getNextWordOffset() {
        return getNextWordOffsetForLayout$default(this, this.textLayoutResult, 0, 1, null);
    }

    public final int getPrecedingCharacterIndex() {
        return StringHelpers_androidKt.findPrecedingBreak(this.text, TextRange.m5187getEndimpl(this.selection));
    }

    public final int getPreviousWordOffset() {
        return getPrevWordOffsetForLayout$default(this, this.textLayoutResult, 0, 1, null);
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelection() {
        return this.selection;
    }

    public final TextFieldPreparedSelection moveCursorDownByLine() {
        if (this.text.length() > 0) {
            setCursor(jumpByLinesOffset(this.textLayoutResult, 1));
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorDownByPage() {
        if (this.text.length() > 0) {
            setCursor(jumpByPagesOffset(1));
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorLeft() {
        if (c.e(this, this) > 0) {
            if (isLtr()) {
                moveCursorPrev();
                return this;
            }
            moveCursorNext();
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorLeftByWord() {
        if (c.e(this, this) > 0) {
            if (isLtr()) {
                moveCursorPrevByWord();
                return this;
            }
            moveCursorNextByWord();
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorNextByParagraph() {
        if (c.e(this, this) > 0) {
            int iFindParagraphEnd = StringHelpersKt.findParagraphEnd(this.text, TextRange.m5189getMaximpl(this.selection));
            if (iFindParagraphEnd == TextRange.m5189getMaximpl(this.selection) && iFindParagraphEnd != this.text.length()) {
                iFindParagraphEnd = StringHelpersKt.findParagraphEnd(this.text, iFindParagraphEnd + 1);
            }
            setCursor(iFindParagraphEnd);
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorPrevByParagraph() {
        if (c.e(this, this) > 0) {
            int iFindParagraphStart = StringHelpersKt.findParagraphStart(this.text, TextRange.m5190getMinimpl(this.selection));
            if (iFindParagraphStart == TextRange.m5190getMinimpl(this.selection) && iFindParagraphStart != 0) {
                iFindParagraphStart = StringHelpersKt.findParagraphStart(this.text, iFindParagraphStart - 1);
            }
            setCursor(iFindParagraphStart);
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorRight() {
        if (c.e(this, this) > 0) {
            if (isLtr()) {
                moveCursorNext();
                return this;
            }
            moveCursorPrev();
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorRightByWord() {
        if (c.e(this, this) > 0) {
            if (isLtr()) {
                moveCursorNextByWord();
                return this;
            }
            moveCursorPrevByWord();
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToEnd() {
        if (c.e(this, this) > 0) {
            setCursor(this.text.length());
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToHome() {
        if (c.e(this, this) > 0) {
            setCursor(0);
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToLineEnd() {
        if (c.e(this, this) > 0) {
            setCursor(getLineEndByOffset());
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToLineLeftSide() {
        if (c.e(this, this) > 0) {
            if (isLtr()) {
                moveCursorToLineStart();
                return this;
            }
            moveCursorToLineEnd();
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToLineRightSide() {
        if (c.e(this, this) > 0) {
            if (isLtr()) {
                moveCursorToLineEnd();
                return this;
            }
            moveCursorToLineStart();
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorToLineStart() {
        if (c.e(this, this) > 0) {
            setCursor(getLineStartByOffset());
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorUpByLine() {
        if (this.text.length() > 0) {
            setCursor(jumpByLinesOffset(this.textLayoutResult, -1));
        }
        return this;
    }

    public final TextFieldPreparedSelection moveCursorUpByPage() {
        if (this.text.length() > 0) {
            setCursor(jumpByPagesOffset(-1));
        }
        return this;
    }

    public final TextFieldPreparedSelection selectAll() {
        if (c.e(this, this) > 0) {
            this.selection = TextRangeKt.TextRange(0, this.text.length());
        }
        return this;
    }

    public final TextFieldPreparedSelection selectMovement() {
        if (this.text.length() > 0) {
            this.selection = TextRangeKt.TextRange(TextRange.m5192getStartimpl(this.initialValue.getSelectionInChars()), TextRange.m5187getEndimpl(this.selection));
        }
        return this;
    }

    /* JADX INFO: renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m1122setSelection5zctL8(long j10) {
        this.selection = j10;
    }
}
