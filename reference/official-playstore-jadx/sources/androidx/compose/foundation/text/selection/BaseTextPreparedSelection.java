package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.c;
import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.selection.BaseTextPreparedSelection;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\bO\n\u0002\u0010\u000e\n\u0002\b\u0005\b \u0018\u0000 l*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u0002:\u0001lB1\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ=\u0010\u0016\u001a\u00028\u0000\"\u0004\b\u0001\u0010\u000f*\u00028\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014H\u0084\b¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0018H\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0018H\u0004¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00028\u0000¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00028\u0000¢\u0006\u0004\b\"\u0010!J\r\u0010#\u001a\u00028\u0000¢\u0006\u0004\b#\u0010!J\r\u0010$\u001a\u00028\u0000¢\u0006\u0004\b$\u0010!J&\u0010&\u001a\u00028\u00002\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014¢\u0006\u0004\b&\u0010'J&\u0010(\u001a\u00028\u00002\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014¢\u0006\u0004\b(\u0010'J\r\u0010)\u001a\u00020\u0018¢\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0018¢\u0006\u0004\b+\u0010*J\r\u0010,\u001a\u00028\u0000¢\u0006\u0004\b,\u0010!J\r\u0010-\u001a\u00028\u0000¢\u0006\u0004\b-\u0010!J\r\u0010.\u001a\u00028\u0000¢\u0006\u0004\b.\u0010!J\r\u0010/\u001a\u00028\u0000¢\u0006\u0004\b/\u0010!J\u000f\u00100\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b2\u00101J\r\u00103\u001a\u00028\u0000¢\u0006\u0004\b3\u0010!J\r\u00104\u001a\u00028\u0000¢\u0006\u0004\b4\u0010!J\r\u00105\u001a\u00028\u0000¢\u0006\u0004\b5\u0010!J\r\u00106\u001a\u00028\u0000¢\u0006\u0004\b6\u0010!J\u000f\u00107\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b7\u00101J\r\u00108\u001a\u00028\u0000¢\u0006\u0004\b8\u0010!J\u000f\u00109\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b9\u00101J\r\u0010:\u001a\u00028\u0000¢\u0006\u0004\b:\u0010!J\r\u0010;\u001a\u00028\u0000¢\u0006\u0004\b;\u0010!J\r\u0010<\u001a\u00028\u0000¢\u0006\u0004\b<\u0010!J\r\u0010=\u001a\u00028\u0000¢\u0006\u0004\b=\u0010!J\u000f\u0010>\u001a\u00028\u0000H\u0002¢\u0006\u0004\b>\u0010!J\u000f\u0010?\u001a\u00028\u0000H\u0002¢\u0006\u0004\b?\u0010!J\u000f\u0010@\u001a\u00028\u0000H\u0002¢\u0006\u0004\b@\u0010!J\u000f\u0010A\u001a\u00028\u0000H\u0002¢\u0006\u0004\bA\u0010!J\u000f\u0010B\u001a\u00020\u0010H\u0002¢\u0006\u0004\bB\u0010CJ\u001e\u0010E\u001a\u00020\u0018*\u00020\u00072\b\b\u0002\u0010D\u001a\u00020\u0018H\u0082\u0010¢\u0006\u0004\bE\u0010FJ\u001e\u0010G\u001a\u00020\u0018*\u00020\u00072\b\b\u0002\u0010D\u001a\u00020\u0018H\u0082\u0010¢\u0006\u0004\bG\u0010FJ\u001d\u0010H\u001a\u00020\u0018*\u00020\u00072\b\b\u0002\u0010D\u001a\u00020\u0018H\u0002¢\u0006\u0004\bH\u0010FJ\u001d\u0010I\u001a\u00020\u0018*\u00020\u00072\b\b\u0002\u0010D\u001a\u00020\u0018H\u0002¢\u0006\u0004\bI\u0010FJ\u001b\u0010K\u001a\u00020\u0018*\u00020\u00072\u0006\u0010J\u001a\u00020\u0018H\u0002¢\u0006\u0004\bK\u0010FJ\u000f\u0010L\u001a\u00020\u0018H\u0002¢\u0006\u0004\bL\u0010*J\u000f\u0010M\u001a\u00020\u0018H\u0002¢\u0006\u0004\bM\u0010*J\u000f\u0010N\u001a\u00020\u0018H\u0002¢\u0006\u0004\bN\u0010*J\u0017\u0010O\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\bO\u0010PR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010Q\u001a\u0004\bR\u0010SR\u001d\u0010\u0006\u001a\u00020\u00058\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010T\u001a\u0004\bU\u0010VR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010W\u001a\u0004\bX\u0010YR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010Z\u001a\u0004\b[\u0010\\R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010]\u001a\u0004\b^\u0010_R(\u0010`\u001a\u00020\u00058\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b`\u0010T\u001a\u0004\ba\u0010V\"\u0004\bb\u0010cR\"\u0010d\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bd\u0010Q\u001a\u0004\be\u0010S\"\u0004\bf\u0010gR\u0014\u0010k\u001a\u00020h8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bi\u0010j\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006m"}, d2 = {"Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "T", "", "Landroidx/compose/ui/text/AnnotatedString;", "originalText", "Landroidx/compose/ui/text/TextRange;", "originalSelection", "Landroidx/compose/ui/text/TextLayoutResult;", "layoutResult", "Landroidx/compose/ui/text/input/OffsetMapping;", "offsetMapping", "Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "state", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;JLandroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;Lkotlin/jvm/internal/h;)V", "U", "", "resetCachedX", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "apply", "(Ljava/lang/Object;ZLr7/l;)Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "", "offset", "setCursor", "(I)V", TtmlNode.START, TtmlNode.END, "setSelection", "(II)V", "selectAll", "()Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "deselect", "moveCursorLeft", "moveCursorRight", "or", "collapseLeftOr", "(Lr7/l;)Landroidx/compose/foundation/text/selection/BaseTextPreparedSelection;", "collapseRightOr", "getPrecedingCharacterIndex", "()I", "getNextCharacterIndex", "moveCursorToHome", "moveCursorToEnd", "moveCursorLeftByWord", "moveCursorRightByWord", "getNextWordOffset", "()Ljava/lang/Integer;", "getPreviousWordOffset", "moveCursorPrevByParagraph", "moveCursorNextByParagraph", "moveCursorUpByLine", "moveCursorDownByLine", "getLineStartByOffset", "moveCursorToLineStart", "getLineEndByOffset", "moveCursorToLineEnd", "moveCursorToLineLeftSide", "moveCursorToLineRightSide", "selectMovement", "moveCursorPrev", "moveCursorNext", "moveCursorNextByWord", "moveCursorPrevByWord", "isLtr", "()Z", "currentOffset", "getNextWordOffsetForLayout", "(Landroidx/compose/ui/text/TextLayoutResult;I)I", "getPrevWordOffset", "getLineStartByOffsetForLayout", "getLineEndByOffsetForLayout", "linesAmount", "jumpByLinesOffset", "transformedEndOffset", "transformedMinOffset", "transformedMaxOffset", "charOffset", "(I)I", "Landroidx/compose/ui/text/AnnotatedString;", "getOriginalText", "()Landroidx/compose/ui/text/AnnotatedString;", "J", "getOriginalSelection-d9O1mEE", "()J", "Landroidx/compose/ui/text/TextLayoutResult;", "getLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "Landroidx/compose/ui/text/input/OffsetMapping;", "getOffsetMapping", "()Landroidx/compose/ui/text/input/OffsetMapping;", "Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "getState", "()Landroidx/compose/foundation/text/selection/TextPreparedSelectionState;", "selection", "getSelection-d9O1mEE", "setSelection-5zc-tL8", "(J)V", "annotatedString", "getAnnotatedString", "setAnnotatedString", "(Landroidx/compose/ui/text/AnnotatedString;)V", "", "getText$foundation_release", "()Ljava/lang/String;", "text", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class BaseTextPreparedSelection<T extends BaseTextPreparedSelection<T>> {
    public static final int NoCharacterFound = -1;
    private AnnotatedString annotatedString;
    private final TextLayoutResult layoutResult;
    private final OffsetMapping offsetMapping;
    private final long originalSelection;
    private final AnnotatedString originalText;
    private long selection;
    private final TextPreparedSelectionState state;
    public static final int $stable = 8;

    public /* synthetic */ BaseTextPreparedSelection(AnnotatedString annotatedString, long j10, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, TextPreparedSelectionState textPreparedSelectionState, h hVar) {
        this(annotatedString, j10, textLayoutResult, offsetMapping, textPreparedSelectionState);
    }

    public static /* synthetic */ BaseTextPreparedSelection apply$default(BaseTextPreparedSelection baseTextPreparedSelection, Object obj, boolean z, l lVar, int i10, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: apply");
        }
        if ((i10 & 1) != 0) {
            z = true;
        }
        if (z) {
            baseTextPreparedSelection.getState().resetCachedX();
        }
        if (baseTextPreparedSelection.getText$foundation_release().length() > 0) {
            lVar.invoke(obj);
        }
        return (BaseTextPreparedSelection) obj;
    }

    private final int charOffset(int offset) {
        int length = getText$foundation_release().length() - 1;
        return offset > length ? length : offset;
    }

    private final int getLineEndByOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        return this.offsetMapping.transformedToOriginal(textLayoutResult.getLineEnd(textLayoutResult.getLineForOffset(i10), true));
    }

    public static /* synthetic */ int getLineEndByOffsetForLayout$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLineEndByOffsetForLayout");
        }
        if ((i11 & 1) != 0) {
            i10 = baseTextPreparedSelection.transformedMaxOffset();
        }
        return baseTextPreparedSelection.getLineEndByOffsetForLayout(textLayoutResult, i10);
    }

    private final int getLineStartByOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        return this.offsetMapping.transformedToOriginal(textLayoutResult.getLineStart(textLayoutResult.getLineForOffset(i10)));
    }

    public static /* synthetic */ int getLineStartByOffsetForLayout$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLineStartByOffsetForLayout");
        }
        if ((i11 & 1) != 0) {
            i10 = baseTextPreparedSelection.transformedMinOffset();
        }
        return baseTextPreparedSelection.getLineStartByOffsetForLayout(textLayoutResult, i10);
    }

    private final int getNextWordOffsetForLayout(TextLayoutResult textLayoutResult, int i10) {
        while (i10 < this.originalText.length()) {
            long jM5166getWordBoundaryjx7JFs = textLayoutResult.m5166getWordBoundaryjx7JFs(charOffset(i10));
            if (TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs) > i10) {
                return this.offsetMapping.transformedToOriginal(TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs));
            }
            i10++;
        }
        return this.originalText.length();
    }

    public static /* synthetic */ int getNextWordOffsetForLayout$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getNextWordOffsetForLayout");
        }
        if ((i11 & 1) != 0) {
            i10 = baseTextPreparedSelection.transformedEndOffset();
        }
        return baseTextPreparedSelection.getNextWordOffsetForLayout(textLayoutResult, i10);
    }

    private final int getPrevWordOffset(TextLayoutResult textLayoutResult, int i10) {
        while (i10 > 0) {
            long jM5166getWordBoundaryjx7JFs = textLayoutResult.m5166getWordBoundaryjx7JFs(charOffset(i10));
            if (TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs) < i10) {
                return this.offsetMapping.transformedToOriginal(TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs));
            }
            i10--;
        }
        return 0;
    }

    public static /* synthetic */ int getPrevWordOffset$default(BaseTextPreparedSelection baseTextPreparedSelection, TextLayoutResult textLayoutResult, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPrevWordOffset");
        }
        if ((i11 & 1) != 0) {
            i10 = baseTextPreparedSelection.transformedEndOffset();
        }
        return baseTextPreparedSelection.getPrevWordOffset(textLayoutResult, i10);
    }

    private final boolean isLtr() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        return (textLayoutResult != null ? textLayoutResult.getParagraphDirection(transformedEndOffset()) : null) != ResolvedTextDirection.Rtl;
    }

    private final int jumpByLinesOffset(TextLayoutResult textLayoutResult, int i10) {
        int iTransformedEndOffset = transformedEndOffset();
        if (this.state.getCachedX() == null) {
            this.state.setCachedX(Float.valueOf(textLayoutResult.getCursorRect(iTransformedEndOffset).getLeft()));
        }
        int lineForOffset = textLayoutResult.getLineForOffset(iTransformedEndOffset) + i10;
        if (lineForOffset < 0) {
            return 0;
        }
        if (lineForOffset >= textLayoutResult.getLineCount()) {
            return getText$foundation_release().length();
        }
        float lineBottom = textLayoutResult.getLineBottom(lineForOffset) - 1;
        Float cachedX = this.state.getCachedX();
        float fFloatValue = cachedX.floatValue();
        if ((isLtr() && fFloatValue >= textLayoutResult.getLineRight(lineForOffset)) || (!isLtr() && fFloatValue <= textLayoutResult.getLineLeft(lineForOffset))) {
            return textLayoutResult.getLineEnd(lineForOffset, true);
        }
        return this.offsetMapping.transformedToOriginal(textLayoutResult.m5164getOffsetForPositionk4lQ0M(OffsetKt.Offset(cachedX.floatValue(), lineBottom)));
    }

    private final T moveCursorNext() {
        int nextCharacterIndex;
        if (c.d(this) > 0 && (nextCharacterIndex = getNextCharacterIndex()) != -1) {
            setCursor(nextCharacterIndex);
        }
        return this;
    }

    private final T moveCursorNextByWord() {
        Integer nextWordOffset;
        if (c.d(this) > 0 && (nextWordOffset = getNextWordOffset()) != null) {
            setCursor(nextWordOffset.intValue());
        }
        return this;
    }

    private final T moveCursorPrev() {
        int precedingCharacterIndex;
        if (c.d(this) > 0 && (precedingCharacterIndex = getPrecedingCharacterIndex()) != -1) {
            setCursor(precedingCharacterIndex);
        }
        return this;
    }

    private final T moveCursorPrevByWord() {
        Integer previousWordOffset;
        if (c.d(this) > 0 && (previousWordOffset = getPreviousWordOffset()) != null) {
            setCursor(previousWordOffset.intValue());
        }
        return this;
    }

    private final int transformedEndOffset() {
        return this.offsetMapping.originalToTransformed(TextRange.m5187getEndimpl(this.selection));
    }

    private final int transformedMaxOffset() {
        return this.offsetMapping.originalToTransformed(TextRange.m5189getMaximpl(this.selection));
    }

    private final int transformedMinOffset() {
        return this.offsetMapping.originalToTransformed(TextRange.m5190getMinimpl(this.selection));
    }

    public final <U> T apply(U u2, boolean z, l<? super U, t0> lVar) {
        if (z) {
            getState().resetCachedX();
        }
        if (getText$foundation_release().length() > 0) {
            lVar.invoke(u2);
        }
        return (T) u2;
    }

    public final T collapseLeftOr(l<? super T, t0> or) {
        if (c.d(this) > 0) {
            if (TextRange.m5186getCollapsedimpl(this.selection)) {
                or.invoke(this);
            } else if (isLtr()) {
                setCursor(TextRange.m5190getMinimpl(this.selection));
            } else {
                setCursor(TextRange.m5189getMaximpl(this.selection));
            }
        }
        return this;
    }

    public final T collapseRightOr(l<? super T, t0> or) {
        if (c.d(this) > 0) {
            if (TextRange.m5186getCollapsedimpl(this.selection)) {
                or.invoke(this);
            } else if (isLtr()) {
                setCursor(TextRange.m5189getMaximpl(this.selection));
            } else {
                setCursor(TextRange.m5190getMinimpl(this.selection));
            }
        }
        return this;
    }

    public final T deselect() {
        if (c.d(this) > 0) {
            setCursor(TextRange.m5187getEndimpl(this.selection));
        }
        return this;
    }

    public final AnnotatedString getAnnotatedString() {
        return this.annotatedString;
    }

    public final TextLayoutResult getLayoutResult() {
        return this.layoutResult;
    }

    public final Integer getLineEndByOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getLineEndByOffsetForLayout$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    public final Integer getLineStartByOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getLineStartByOffsetForLayout$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    public final int getNextCharacterIndex() {
        return StringHelpers_androidKt.findFollowingBreak(this.annotatedString.getText(), TextRange.m5187getEndimpl(this.selection));
    }

    public final Integer getNextWordOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getNextWordOffsetForLayout$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    /* JADX INFO: renamed from: getOriginalSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getOriginalSelection() {
        return this.originalSelection;
    }

    public final AnnotatedString getOriginalText() {
        return this.originalText;
    }

    public final int getPrecedingCharacterIndex() {
        return StringHelpers_androidKt.findPrecedingBreak(this.annotatedString.getText(), TextRange.m5187getEndimpl(this.selection));
    }

    public final Integer getPreviousWordOffset() {
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            return Integer.valueOf(getPrevWordOffset$default(this, textLayoutResult, 0, 1, null));
        }
        return null;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getSelection() {
        return this.selection;
    }

    public final TextPreparedSelectionState getState() {
        return this.state;
    }

    public final String getText$foundation_release() {
        return this.annotatedString.getText();
    }

    public final T moveCursorDownByLine() {
        TextLayoutResult textLayoutResult;
        if (getText$foundation_release().length() > 0 && (textLayoutResult = this.layoutResult) != null) {
            setCursor(jumpByLinesOffset(textLayoutResult, 1));
        }
        return this;
    }

    public final T moveCursorLeft() {
        if (c.d(this) > 0) {
            if (isLtr()) {
                moveCursorPrev();
            } else {
                moveCursorNext();
            }
        }
        return this;
    }

    public final T moveCursorLeftByWord() {
        if (c.d(this) > 0) {
            if (isLtr()) {
                moveCursorPrevByWord();
            } else {
                moveCursorNextByWord();
            }
        }
        return this;
    }

    public final T moveCursorNextByParagraph() {
        if (c.d(this) > 0) {
            int iFindParagraphEnd = StringHelpersKt.findParagraphEnd(getText$foundation_release(), TextRange.m5189getMaximpl(this.selection));
            if (iFindParagraphEnd == TextRange.m5189getMaximpl(this.selection) && iFindParagraphEnd != getText$foundation_release().length()) {
                iFindParagraphEnd = StringHelpersKt.findParagraphEnd(getText$foundation_release(), iFindParagraphEnd + 1);
            }
            setCursor(iFindParagraphEnd);
        }
        return this;
    }

    public final T moveCursorPrevByParagraph() {
        if (c.d(this) > 0) {
            int iFindParagraphStart = StringHelpersKt.findParagraphStart(getText$foundation_release(), TextRange.m5190getMinimpl(this.selection));
            if (iFindParagraphStart == TextRange.m5190getMinimpl(this.selection) && iFindParagraphStart != 0) {
                iFindParagraphStart = StringHelpersKt.findParagraphStart(getText$foundation_release(), iFindParagraphStart - 1);
            }
            setCursor(iFindParagraphStart);
        }
        return this;
    }

    public final T moveCursorRight() {
        if (c.d(this) > 0) {
            if (isLtr()) {
                moveCursorNext();
            } else {
                moveCursorPrev();
            }
        }
        return this;
    }

    public final T moveCursorRightByWord() {
        if (c.d(this) > 0) {
            if (isLtr()) {
                moveCursorNextByWord();
            } else {
                moveCursorPrevByWord();
            }
        }
        return this;
    }

    public final T moveCursorToEnd() {
        if (c.d(this) > 0) {
            setCursor(getText$foundation_release().length());
        }
        return this;
    }

    public final T moveCursorToHome() {
        if (c.d(this) > 0) {
            setCursor(0);
        }
        return this;
    }

    public final T moveCursorToLineEnd() {
        Integer lineEndByOffset;
        if (c.d(this) > 0 && (lineEndByOffset = getLineEndByOffset()) != null) {
            setCursor(lineEndByOffset.intValue());
        }
        return this;
    }

    public final T moveCursorToLineLeftSide() {
        if (c.d(this) > 0) {
            if (isLtr()) {
                moveCursorToLineStart();
            } else {
                moveCursorToLineEnd();
            }
        }
        return this;
    }

    public final T moveCursorToLineRightSide() {
        if (c.d(this) > 0) {
            if (isLtr()) {
                moveCursorToLineEnd();
            } else {
                moveCursorToLineStart();
            }
        }
        return this;
    }

    public final T moveCursorToLineStart() {
        Integer lineStartByOffset;
        if (c.d(this) > 0 && (lineStartByOffset = getLineStartByOffset()) != null) {
            setCursor(lineStartByOffset.intValue());
        }
        return this;
    }

    public final T moveCursorUpByLine() {
        TextLayoutResult textLayoutResult;
        if (getText$foundation_release().length() > 0 && (textLayoutResult = this.layoutResult) != null) {
            setCursor(jumpByLinesOffset(textLayoutResult, -1));
        }
        return this;
    }

    public final T selectAll() {
        if (c.d(this) > 0) {
            setSelection(0, getText$foundation_release().length());
        }
        return this;
    }

    public final T selectMovement() {
        if (getText$foundation_release().length() > 0) {
            this.selection = TextRangeKt.TextRange(TextRange.m5192getStartimpl(this.originalSelection), TextRange.m5187getEndimpl(this.selection));
        }
        return this;
    }

    public final void setAnnotatedString(AnnotatedString annotatedString) {
        this.annotatedString = annotatedString;
    }

    public final void setCursor(int offset) {
        setSelection(offset, offset);
    }

    public final void setSelection(int start, int end) {
        this.selection = TextRangeKt.TextRange(start, end);
    }

    /* JADX INFO: renamed from: setSelection-5zc-tL8, reason: not valid java name */
    public final void m957setSelection5zctL8(long j10) {
        this.selection = j10;
    }

    private BaseTextPreparedSelection(AnnotatedString annotatedString, long j10, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, TextPreparedSelectionState textPreparedSelectionState) {
        this.originalText = annotatedString;
        this.originalSelection = j10;
        this.layoutResult = textLayoutResult;
        this.offsetMapping = offsetMapping;
        this.state = textPreparedSelectionState;
        this.selection = j10;
        this.annotatedString = annotatedString;
    }
}
