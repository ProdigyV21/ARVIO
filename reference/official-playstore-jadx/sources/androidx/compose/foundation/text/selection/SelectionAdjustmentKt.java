package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.media3.extractor.text.ttml.TtmlNode;
import f4.f;
import kotlin.Metadata;
import x6.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a,\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u001c\u0010\u000e\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\rH\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001c\u0010\u0013\u001a\u00020\n*\u00020\b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a4\u0010\u0015\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u001c\u0010\u0019\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¨\u0006\u001b²\u0006\n\u0010\u001c\u001a\u00020\rX\u008a\u0084\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"adjustToBoundaries", "Landroidx/compose/foundation/text/selection/Selection;", TtmlNode.TAG_LAYOUT, "Landroidx/compose/foundation/text/selection/SelectionLayout;", "boundaryFunction", "Landroidx/compose/foundation/text/selection/BoundaryFunction;", "anchorOnBoundary", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "Landroidx/compose/foundation/text/selection/SelectableInfo;", "crossed", "", "isStart", "slot", "", "changeOffset", "info", "newOffset", "ensureAtLeastOneChar", "expandOneChar", "isExpanding", "currentRawOffset", "snapToWordBoundary", "currentLine", "currentOffset", "otherOffset", "updateSelectionBoundary", "previousSelectionAnchor", "foundation_release", "currentRawLine", "anchorSnappedToWordBoundary"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionAdjustmentKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection adjustToBoundaries(SelectionLayout selectionLayout, BoundaryFunction boundaryFunction) {
        boolean z = selectionLayout.getCrossStatus() == CrossStatus.CROSSED;
        return new Selection(anchorOnBoundary(selectionLayout.getStartInfo(), z, true, selectionLayout.getStartSlot(), boundaryFunction), anchorOnBoundary(selectionLayout.getEndInfo(), z, false, selectionLayout.getEndSlot(), boundaryFunction), z);
    }

    private static final Selection.AnchorInfo anchorOnBoundary(SelectableInfo selectableInfo, boolean z, boolean z5, int i10, BoundaryFunction boundaryFunction) {
        int rawStartHandleOffset = z5 ? selectableInfo.getRawStartHandleOffset() : selectableInfo.getRawEndHandleOffset();
        if (i10 != selectableInfo.getSlot()) {
            return selectableInfo.anchorForOffset(rawStartHandleOffset);
        }
        long jMo958getBoundaryfzxv0v0 = boundaryFunction.mo958getBoundaryfzxv0v0(selectableInfo, rawStartHandleOffset);
        return selectableInfo.anchorForOffset(z ^ z5 ? TextRange.m5192getStartimpl(jMo958getBoundaryfzxv0v0) : TextRange.m5187getEndimpl(jMo958getBoundaryfzxv0v0));
    }

    private static final Selection.AnchorInfo changeOffset(Selection.AnchorInfo anchorInfo, SelectableInfo selectableInfo, int i10) {
        return Selection.AnchorInfo.copy$default(anchorInfo, selectableInfo.getTextLayoutResult().getBidiRunDirection(i10), i10, 0L, 4, null);
    }

    public static final Selection ensureAtLeastOneChar(Selection selection, SelectionLayout selectionLayout) {
        if (SelectionLayoutKt.isCollapsed(selection, selectionLayout)) {
            return (selectionLayout.getSize() > 1 || selectionLayout.getPreviousSelection() == null || selectionLayout.getInfo().getInputText().length() == 0) ? selection : expandOneChar(selection, selectionLayout);
        }
        return selection;
    }

    private static final Selection expandOneChar(Selection selection, SelectionLayout selectionLayout) {
        SelectableInfo currentInfo = selectionLayout.getInfo();
        String inputText = currentInfo.getInputText();
        int rawStartHandleOffset = currentInfo.getRawStartHandleOffset();
        int length = inputText.length();
        if (rawStartHandleOffset == 0) {
            int iFindFollowingBreak = StringHelpers_androidKt.findFollowingBreak(inputText, 0);
            return selectionLayout.getIsStartHandle() ? Selection.copy$default(selection, changeOffset(selection.getStart(), currentInfo, iFindFollowingBreak), null, true, 2, null) : Selection.copy$default(selection, null, changeOffset(selection.getEnd(), currentInfo, iFindFollowingBreak), false, 1, null);
        }
        if (rawStartHandleOffset == length) {
            int iFindPrecedingBreak = StringHelpers_androidKt.findPrecedingBreak(inputText, length);
            return selectionLayout.getIsStartHandle() ? Selection.copy$default(selection, changeOffset(selection.getStart(), currentInfo, iFindPrecedingBreak), null, false, 2, null) : Selection.copy$default(selection, null, changeOffset(selection.getEnd(), currentInfo, iFindPrecedingBreak), true, 1, null);
        }
        Selection previousSelection = selectionLayout.getPreviousSelection();
        boolean z = previousSelection != null && previousSelection.getHandlesCrossed();
        int iFindPrecedingBreak2 = selectionLayout.getIsStartHandle() ^ z ? StringHelpers_androidKt.findPrecedingBreak(inputText, rawStartHandleOffset) : StringHelpers_androidKt.findFollowingBreak(inputText, rawStartHandleOffset);
        return selectionLayout.getIsStartHandle() ? Selection.copy$default(selection, changeOffset(selection.getStart(), currentInfo, iFindPrecedingBreak2), null, z, 2, null) : Selection.copy$default(selection, null, changeOffset(selection.getEnd(), currentInfo, iFindPrecedingBreak2), z, 1, null);
    }

    private static final boolean isExpanding(SelectableInfo selectableInfo, int i10, boolean z) {
        if (selectableInfo.getRawPreviousHandleOffset() == -1) {
            return true;
        }
        if (i10 == selectableInfo.getRawPreviousHandleOffset()) {
            return false;
        }
        return z ^ (selectableInfo.getRawCrossStatus() == CrossStatus.CROSSED) ? i10 < selectableInfo.getRawPreviousHandleOffset() : i10 > selectableInfo.getRawPreviousHandleOffset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection.AnchorInfo snapToWordBoundary(SelectableInfo selectableInfo, int i10, int i11, int i12, boolean z, boolean z5) {
        long jM5166getWordBoundaryjx7JFs = selectableInfo.getTextLayoutResult().m5166getWordBoundaryjx7JFs(i11);
        int iM5192getStartimpl = selectableInfo.getTextLayoutResult().getLineForOffset(TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs)) == i10 ? TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs) : i10 >= selectableInfo.getTextLayoutResult().getLineCount() ? selectableInfo.getTextLayoutResult().getLineStart(selectableInfo.getTextLayoutResult().getLineCount() - 1) : selectableInfo.getTextLayoutResult().getLineStart(i10);
        int iM5187getEndimpl = selectableInfo.getTextLayoutResult().getLineForOffset(TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs)) == i10 ? TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs) : i10 >= selectableInfo.getTextLayoutResult().getLineCount() ? TextLayoutResult.getLineEnd$default(selectableInfo.getTextLayoutResult(), selectableInfo.getTextLayoutResult().getLineCount() - 1, false, 2, null) : TextLayoutResult.getLineEnd$default(selectableInfo.getTextLayoutResult(), i10, false, 2, null);
        if (iM5192getStartimpl == i12) {
            return selectableInfo.anchorForOffset(iM5187getEndimpl);
        }
        if (iM5187getEndimpl == i12) {
            return selectableInfo.anchorForOffset(iM5192getStartimpl);
        }
        if (!(z ^ z5) ? i11 >= iM5192getStartimpl : i11 > iM5187getEndimpl) {
            iM5192getStartimpl = iM5187getEndimpl;
        }
        return selectableInfo.anchorForOffset(iM5192getStartimpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection.AnchorInfo updateSelectionBoundary(SelectionLayout selectionLayout, SelectableInfo selectableInfo, Selection.AnchorInfo anchorInfo) {
        int rawStartHandleOffset = selectionLayout.getIsStartHandle() ? selectableInfo.getRawStartHandleOffset() : selectableInfo.getRawEndHandleOffset();
        if ((selectionLayout.getIsStartHandle() ? selectionLayout.getStartSlot() : selectionLayout.getEndSlot()) != selectableInfo.getSlot()) {
            return selectableInfo.anchorForOffset(rawStartHandleOffset);
        }
        s sVarO = f.o(3, new SelectionAdjustmentKt$updateSelectionBoundary$currentRawLine$2(selectableInfo, rawStartHandleOffset));
        s sVarO2 = f.o(3, new SelectionAdjustmentKt$updateSelectionBoundary$anchorSnappedToWordBoundary$2(selectableInfo, rawStartHandleOffset, selectionLayout.getIsStartHandle() ? selectableInfo.getRawEndHandleOffset() : selectableInfo.getRawStartHandleOffset(), selectionLayout, sVarO));
        if (selectableInfo.getSelectableId() != anchorInfo.getSelectableId()) {
            return updateSelectionBoundary$lambda$1(sVarO2);
        }
        int rawPreviousHandleOffset = selectableInfo.getRawPreviousHandleOffset();
        if (rawStartHandleOffset == rawPreviousHandleOffset) {
            return anchorInfo;
        }
        if (updateSelectionBoundary$lambda$0(sVarO) != selectableInfo.getTextLayoutResult().getLineForOffset(rawPreviousHandleOffset)) {
            return updateSelectionBoundary$lambda$1(sVarO2);
        }
        int offset = anchorInfo.getOffset();
        long jM5166getWordBoundaryjx7JFs = selectableInfo.getTextLayoutResult().m5166getWordBoundaryjx7JFs(offset);
        return !isExpanding(selectableInfo, rawStartHandleOffset, selectionLayout.getIsStartHandle()) ? selectableInfo.anchorForOffset(rawStartHandleOffset) : (offset == TextRange.m5192getStartimpl(jM5166getWordBoundaryjx7JFs) || offset == TextRange.m5187getEndimpl(jM5166getWordBoundaryjx7JFs)) ? updateSelectionBoundary$lambda$1(sVarO2) : selectableInfo.anchorForOffset(rawStartHandleOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int updateSelectionBoundary$lambda$0(s<Integer> sVar) {
        return ((Number) sVar.getValue()).intValue();
    }

    private static final Selection.AnchorInfo updateSelectionBoundary$lambda$1(s<Selection.AnchorInfo> sVar) {
        return (Selection.AnchorInfo) sVar.getValue();
    }
}
