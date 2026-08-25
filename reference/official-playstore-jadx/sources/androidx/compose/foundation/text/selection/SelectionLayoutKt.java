package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aJ\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0015*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0017\u001a\u00020\f*\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u0000\u001a\u0014\u0010\u001a\u001a\u00020\f*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"UNASSIGNED_SLOT", "", "getTextFieldSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "rawStartHandleOffset", "rawEndHandleOffset", "rawPreviousHandleOffset", "previousSelectionRange", "Landroidx/compose/ui/text/TextRange;", "isStartOfSelection", "", "isStartHandle", "getTextFieldSelectionLayout-RcvT-LA", "(Landroidx/compose/ui/text/TextLayoutResult;IIIJZZ)Landroidx/compose/foundation/text/selection/SelectionLayout;", "resolve2dDirection", "Landroidx/compose/foundation/text/selection/Direction;", "x", "y", "getTextDirectionForOffset", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "offset", "isCollapsed", "Landroidx/compose/foundation/text/selection/Selection;", TtmlNode.TAG_LAYOUT, "isOffsetAnEmptyLine", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionLayoutKt {
    public static final int UNASSIGNED_SLOT = -1;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Direction.values().length];
            try {
                iArr[Direction.BEFORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Direction.ON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Direction.AFTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionLayoutKt$isCollapsed$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectableInfo;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/selection/SelectableInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<SelectableInfo, t0> {
        final /* synthetic */ f0 $allTextsEmpty;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(f0 f0Var) {
            super(1);
            this.$allTextsEmpty = f0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SelectableInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(SelectableInfo selectableInfo) {
            if (selectableInfo.getInputText().length() > 0) {
                this.$allTextsEmpty.f19738i = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ResolvedTextDirection getTextDirectionForOffset(TextLayoutResult textLayoutResult, int i10) {
        return isOffsetAnEmptyLine(textLayoutResult, i10) ? textLayoutResult.getParagraphDirection(i10) : textLayoutResult.getBidiRunDirection(i10);
    }

    /* JADX INFO: renamed from: getTextFieldSelectionLayout-RcvT-LA, reason: not valid java name */
    public static final SelectionLayout m977getTextFieldSelectionLayoutRcvTLA(TextLayoutResult textLayoutResult, int i10, int i11, int i12, long j10, boolean z, boolean z5) {
        return new SingleSelectionLayout(z5, 1, 1, z ? null : new Selection(new Selection.AnchorInfo(getTextDirectionForOffset(textLayoutResult, TextRange.m5192getStartimpl(j10)), TextRange.m5192getStartimpl(j10), 1L), new Selection.AnchorInfo(getTextDirectionForOffset(textLayoutResult, TextRange.m5187getEndimpl(j10)), TextRange.m5187getEndimpl(j10), 1L), TextRange.m5191getReversedimpl(j10)), new SelectableInfo(1L, 1, i10, i11, i12, textLayoutResult));
    }

    public static final boolean isCollapsed(Selection selection, SelectionLayout selectionLayout) {
        if (selection == null || selectionLayout == null) {
            return true;
        }
        if (selection.getStart().getSelectableId() == selection.getEnd().getSelectableId()) {
            return selection.getStart().getOffset() == selection.getEnd().getOffset();
        }
        if ((selection.getHandlesCrossed() ? selection.getStart() : selection.getEnd()).getOffset() != 0) {
            return false;
        }
        if (selectionLayout.getFirstInfo().getTextLength() != (selection.getHandlesCrossed() ? selection.getEnd() : selection.getStart()).getOffset()) {
            return false;
        }
        f0 f0Var = new f0();
        f0Var.f19738i = true;
        selectionLayout.forEachMiddleInfo(new AnonymousClass1(f0Var));
        return f0Var.f19738i;
    }

    private static final boolean isOffsetAnEmptyLine(TextLayoutResult textLayoutResult, int i10) {
        if (textLayoutResult.getLayoutInput().getText().length() != 0) {
            int lineForOffset = textLayoutResult.getLineForOffset(i10);
            if (i10 != 0 && lineForOffset == textLayoutResult.getLineForOffset(i10 - 1)) {
                return false;
            }
            if (i10 != textLayoutResult.getLayoutInput().getText().length() && lineForOffset == textLayoutResult.getLineForOffset(i10 + 1)) {
                return false;
            }
        }
        return true;
    }

    public static final Direction resolve2dDirection(Direction direction, Direction direction2) {
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i10 = iArr[direction2.ordinal()];
        if (i10 == 1) {
            return Direction.BEFORE;
        }
        if (i10 != 2) {
            if (i10 == 3) {
                return Direction.AFTER;
            }
            throw new NoWhenBranchMatchedException();
        }
        int i11 = iArr[direction.ordinal()];
        if (i11 == 1) {
            return Direction.BEFORE;
        }
        if (i11 == 2) {
            return Direction.ON;
        }
        if (i11 == 3) {
            return Direction.AFTER;
        }
        throw new NoWhenBranchMatchedException();
    }
}
