package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.unit.IntSize;
import java.util.Comparator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a6\u0010\u000b\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a;\u0010\u0013\u001a\u00020\u0011*\u00020\f2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\rj\b\u0012\u0004\u0012\u00020\u0006`\u000e2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\"\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\"\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001a\u001a#\u0010\u001f\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001f\u0010 \u001a\u001f\u0010$\u001a\u00020#2\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0002¢\u0006\u0004\b$\u0010%\u001a\"\u0010(\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionLayoutBuilder;", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "Landroidx/compose/ui/geometry/Offset;", "localPosition", "previousHandlePosition", "", "selectableId", "Lx6/t0;", "appendSelectableInfo-Parwq6A", "(Landroidx/compose/foundation/text/selection/SelectionLayoutBuilder;Landroidx/compose/ui/text/TextLayoutResult;JJJ)V", "appendSelectableInfo", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "selectableIdOrderingComparator", "currentSelectableId", "", "currentTextLength", "getPreviousAdjustedOffset", "(Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;Ljava/util/Comparator;JI)I", "position", "Landroidx/compose/ui/geometry/Rect;", "bounds", "Landroidx/compose/foundation/text/selection/Direction;", "getXDirection-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)Landroidx/compose/foundation/text/selection/Direction;", "getXDirection", "getYDirection-3MmeM6k", "getYDirection", "anchorSelectableId", "getDirectionById", "(Landroidx/compose/foundation/text/selection/SelectionLayoutBuilder;JJ)Landroidx/compose/foundation/text/selection/Direction;", "currentDirection", "otherDirection", "", "isSelected", "(Landroidx/compose/foundation/text/selection/Direction;Landroidx/compose/foundation/text/selection/Direction;)Z", "getOffsetForPosition-3MmeM6k", "(JLandroidx/compose/ui/text/TextLayoutResult;)I", "getOffsetForPosition", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultiWidgetSelectionDelegateKt {
    /* JADX INFO: renamed from: appendSelectableInfo-Parwq6A, reason: not valid java name */
    public static final void m962appendSelectableInfoParwq6A(SelectionLayoutBuilder selectionLayoutBuilder, TextLayoutResult textLayoutResult, long j10, long j11, long j12) {
        Direction direction;
        Direction direction2;
        long j13;
        Direction directionAppendSelectableInfo_Parwq6A$otherDirection;
        Direction direction3;
        Direction direction4;
        Direction direction5;
        Direction direction6;
        int iM963getOffsetForPosition3MmeM6k;
        int previousAdjustedOffset;
        Selection.AnchorInfo start;
        Selection.AnchorInfo end;
        Rect rect = new Rect(0.0f, 0.0f, IntSize.m5844getWidthimpl(textLayoutResult.getSize()), IntSize.m5843getHeightimpl(textLayoutResult.getSize()));
        Direction directionM964getXDirection3MmeM6k = m964getXDirection3MmeM6k(j10, rect);
        Direction directionM965getYDirection3MmeM6k = m965getYDirection3MmeM6k(j10, rect);
        if (selectionLayoutBuilder.getIsStartHandle()) {
            Selection previousSelection = selectionLayoutBuilder.getPreviousSelection();
            direction = directionM965getYDirection3MmeM6k;
            direction2 = directionM964getXDirection3MmeM6k;
            Direction directionAppendSelectableInfo_Parwq6A$otherDirection2 = appendSelectableInfo_Parwq6A$otherDirection(direction2, direction, selectionLayoutBuilder, j12, previousSelection != null ? previousSelection.getEnd() : null);
            j13 = j12;
            direction5 = direction2;
            direction4 = directionAppendSelectableInfo_Parwq6A$otherDirection2;
            direction3 = direction4;
            direction6 = direction3;
            directionAppendSelectableInfo_Parwq6A$otherDirection = direction;
        } else {
            direction = directionM965getYDirection3MmeM6k;
            direction2 = directionM964getXDirection3MmeM6k;
            Selection previousSelection2 = selectionLayoutBuilder.getPreviousSelection();
            j13 = j12;
            directionAppendSelectableInfo_Parwq6A$otherDirection = appendSelectableInfo_Parwq6A$otherDirection(direction2, direction, selectionLayoutBuilder, j13, previousSelection2 != null ? previousSelection2.getStart() : null);
            direction3 = direction2;
            direction4 = direction;
            direction5 = directionAppendSelectableInfo_Parwq6A$otherDirection;
            direction6 = direction5;
        }
        if (isSelected(SelectionLayoutKt.resolve2dDirection(direction2, direction), direction6)) {
            int length = textLayoutResult.getLayoutInput().getText().length();
            if (selectionLayoutBuilder.getIsStartHandle()) {
                previousAdjustedOffset = m963getOffsetForPosition3MmeM6k(j10, textLayoutResult);
                Selection previousSelection3 = selectionLayoutBuilder.getPreviousSelection();
                iM963getOffsetForPosition3MmeM6k = (previousSelection3 == null || (end = previousSelection3.getEnd()) == null) ? previousAdjustedOffset : getPreviousAdjustedOffset(end, selectionLayoutBuilder.getSelectableIdOrderingComparator(), j13, length);
            } else {
                iM963getOffsetForPosition3MmeM6k = m963getOffsetForPosition3MmeM6k(j10, textLayoutResult);
                Selection previousSelection4 = selectionLayoutBuilder.getPreviousSelection();
                previousAdjustedOffset = (previousSelection4 == null || (start = previousSelection4.getStart()) == null) ? iM963getOffsetForPosition3MmeM6k : getPreviousAdjustedOffset(start, selectionLayoutBuilder.getSelectableIdOrderingComparator(), j13, length);
            }
            selectionLayoutBuilder.appendInfo(j13, previousAdjustedOffset, direction5, directionAppendSelectableInfo_Parwq6A$otherDirection, iM963getOffsetForPosition3MmeM6k, direction3, direction4, OffsetKt.m3257isUnspecifiedk4lQ0M(j11) ? -1 : m963getOffsetForPosition3MmeM6k(j11, textLayoutResult), textLayoutResult);
        }
    }

    private static final Direction appendSelectableInfo_Parwq6A$otherDirection(Direction direction, Direction direction2, SelectionLayoutBuilder selectionLayoutBuilder, long j10, Selection.AnchorInfo anchorInfo) {
        Direction directionById;
        return (anchorInfo == null || (directionById = getDirectionById(selectionLayoutBuilder, anchorInfo.getSelectableId(), j10)) == null) ? SelectionLayoutKt.resolve2dDirection(direction, direction2) : directionById;
    }

    private static final Direction getDirectionById(SelectionLayoutBuilder selectionLayoutBuilder, long j10, long j11) {
        int iCompare = selectionLayoutBuilder.getSelectableIdOrderingComparator().compare(Long.valueOf(j10), Long.valueOf(j11));
        return iCompare < 0 ? Direction.BEFORE : iCompare > 0 ? Direction.AFTER : Direction.ON;
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k, reason: not valid java name */
    private static final int m963getOffsetForPosition3MmeM6k(long j10, TextLayoutResult textLayoutResult) {
        if (Offset.m3237getYimpl(j10) <= 0.0f) {
            return 0;
        }
        return Offset.m3237getYimpl(j10) >= textLayoutResult.getMultiParagraph().getHeight() ? textLayoutResult.getLayoutInput().getText().length() : textLayoutResult.m5164getOffsetForPositionk4lQ0M(j10);
    }

    private static final int getPreviousAdjustedOffset(Selection.AnchorInfo anchorInfo, Comparator<Long> comparator, long j10, int i10) {
        int iCompare = comparator.compare(Long.valueOf(anchorInfo.getSelectableId()), Long.valueOf(j10));
        if (iCompare < 0) {
            return 0;
        }
        return iCompare > 0 ? i10 : anchorInfo.getOffset();
    }

    /* JADX INFO: renamed from: getXDirection-3MmeM6k, reason: not valid java name */
    private static final Direction m964getXDirection3MmeM6k(long j10, Rect rect) {
        return Offset.m3236getXimpl(j10) < rect.getLeft() ? Direction.BEFORE : Offset.m3236getXimpl(j10) > rect.getRight() ? Direction.AFTER : Direction.ON;
    }

    /* JADX INFO: renamed from: getYDirection-3MmeM6k, reason: not valid java name */
    private static final Direction m965getYDirection3MmeM6k(long j10, Rect rect) {
        return Offset.m3237getYimpl(j10) < rect.getTop() ? Direction.BEFORE : Offset.m3237getYimpl(j10) > rect.getBottom() ? Direction.AFTER : Direction.ON;
    }

    private static final boolean isSelected(Direction direction, Direction direction2) {
        return direction == Direction.ON || direction != direction2;
    }
}
