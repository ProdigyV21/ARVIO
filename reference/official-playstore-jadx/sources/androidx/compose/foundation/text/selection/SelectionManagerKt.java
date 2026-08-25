package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.IntSize;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.x;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a%\u0010\u0003\u001a\u0004\u0018\u00010\u00002\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a1\u0010\u000f\u001a\u00020\u000e2\u0018\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00000\t0\u00062\u0006\u0010\r\u001a\u00020\fH\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a\"\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a*\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u001e\u001a\u00020\u000e*\u00020\fH\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001e\u0010$\u001a\u00020!*\u00020\u000e2\u0006\u0010 \u001a\u00020\u0015H\u0000ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\"\u0014\u0010%\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/foundation/text/selection/Selection;", "lhs", "rhs", "merge", "(Landroidx/compose/foundation/text/selection/Selection;Landroidx/compose/foundation/text/selection/Selection;)Landroidx/compose/foundation/text/selection/Selection;", "T", "", "firstAndLast", "(Ljava/util/List;)Ljava/util/List;", "Lx6/x;", "Landroidx/compose/foundation/text/selection/Selectable;", "selectableSubSelectionPairs", "Landroidx/compose/ui/layout/LayoutCoordinates;", "containerCoordinates", "Landroidx/compose/ui/geometry/Rect;", "getSelectedRegionRect", "(Ljava/util/List;Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/foundation/text/selection/SelectionManager;", "manager", "Landroidx/compose/ui/unit/IntSize;", "magnifierSize", "Landroidx/compose/ui/geometry/Offset;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/SelectionManager;J)J", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", LinkHeader.Parameters.Anchor, "getMagnifierCenter-JVtK1S4", "(Landroidx/compose/foundation/text/selection/SelectionManager;JLandroidx/compose/foundation/text/selection/Selection$AnchorInfo;)J", "getMagnifierCenter", "visibleBounds", "(Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/geometry/Rect;", "offset", "", "containsInclusive-Uv8p0NA", "(Landroidx/compose/ui/geometry/Rect;J)Z", "containsInclusive", "invertedInfiniteRect", "Landroidx/compose/ui/geometry/Rect;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionManagerKt {
    private static final Rect invertedInfiniteRect = new Rect(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.SelectionStart.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Handle.Cursor.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    public static final long m1013calculateSelectionMagnifierCenterAndroidO0kMr_c(SelectionManager selectionManager, long j10) {
        Selection selection = selectionManager.getSelection();
        if (selection == null) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        Handle draggingHandle = selectionManager.getDraggingHandle();
        int i10 = draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()];
        if (i10 == -1) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        if (i10 == 1) {
            return m1015getMagnifierCenterJVtK1S4(selectionManager, j10, selection.getStart());
        }
        if (i10 == 2) {
            return m1015getMagnifierCenterJVtK1S4(selectionManager, j10, selection.getEnd());
        }
        if (i10 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        throw new IllegalStateException("SelectionContainer does not support cursor");
    }

    /* JADX INFO: renamed from: containsInclusive-Uv8p0NA, reason: not valid java name */
    public static final boolean m1014containsInclusiveUv8p0NA(Rect rect, long j10) {
        float left = rect.getLeft();
        float right = rect.getRight();
        float fM3236getXimpl = Offset.m3236getXimpl(j10);
        if (left > fM3236getXimpl || fM3236getXimpl > right) {
            return false;
        }
        float top = rect.getTop();
        float bottom = rect.getBottom();
        float fM3237getYimpl = Offset.m3237getYimpl(j10);
        return top <= fM3237getYimpl && fM3237getYimpl <= bottom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> firstAndLast(List<? extends T> list) {
        int size = list.size();
        return (size == 0 || size == 1) ? list : t7.a.E(x.m0(list), x.w0(list));
    }

    /* JADX INFO: renamed from: getMagnifierCenter-JVtK1S4, reason: not valid java name */
    private static final long m1015getMagnifierCenterJVtK1S4(SelectionManager selectionManager, long j10, Selection.AnchorInfo anchorInfo) {
        float fM;
        Selectable anchorSelectable$foundation_release = selectionManager.getAnchorSelectable$foundation_release(anchorInfo);
        if (anchorSelectable$foundation_release == null) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        LayoutCoordinates containerLayoutCoordinates = selectionManager.getContainerLayoutCoordinates();
        if (containerLayoutCoordinates == null) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        LayoutCoordinates layoutCoordinates = anchorSelectable$foundation_release.getLayoutCoordinates();
        if (layoutCoordinates == null) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        int offset = anchorInfo.getOffset();
        if (offset > anchorSelectable$foundation_release.getLastVisibleOffset()) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        float fM3236getXimpl = Offset.m3236getXimpl(layoutCoordinates.mo4639localPositionOfR5De75A(containerLayoutCoordinates, selectionManager.m999getCurrentDragPosition_m7T9E().getPackedValue()));
        long jMo961getRangeOfLineContainingjx7JFs = anchorSelectable$foundation_release.mo961getRangeOfLineContainingjx7JFs(offset);
        if (TextRange.m5186getCollapsedimpl(jMo961getRangeOfLineContainingjx7JFs)) {
            fM = anchorSelectable$foundation_release.getLineLeft(offset);
        } else {
            float lineLeft = anchorSelectable$foundation_release.getLineLeft(TextRange.m5192getStartimpl(jMo961getRangeOfLineContainingjx7JFs));
            float lineRight = anchorSelectable$foundation_release.getLineRight(TextRange.m5187getEndimpl(jMo961getRangeOfLineContainingjx7JFs) - 1);
            fM = d.m(fM3236getXimpl, Math.min(lineLeft, lineRight), Math.max(lineLeft, lineRight));
        }
        if (fM == -1.0f) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        if (Math.abs(fM3236getXimpl - fM) > IntSize.m5844getWidthimpl(j10) / 2) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        float centerYForOffset = anchorSelectable$foundation_release.getCenterYForOffset(offset);
        return centerYForOffset == -1.0f ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : containerLayoutCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates, OffsetKt.Offset(fM, centerYForOffset));
    }

    public static final Rect getSelectedRegionRect(List<? extends x6.x> list, LayoutCoordinates layoutCoordinates) {
        int i10;
        LayoutCoordinates layoutCoordinates2;
        int[] iArr;
        if (list.isEmpty()) {
            return invertedInfiniteRect;
        }
        Rect rect = invertedInfiniteRect;
        float left = rect.getLeft();
        float top = rect.getTop();
        float right = rect.getRight();
        float bottom = rect.getBottom();
        int size = list.size();
        char c10 = 0;
        int i11 = 0;
        while (i11 < size) {
            x6.x xVar = list.get(i11);
            Selectable selectable = (Selectable) xVar.f22608i;
            Selection selection = (Selection) xVar.f22609l;
            int offset = selection.getStart().getOffset();
            int offset2 = selection.getEnd().getOffset();
            if (offset == offset2 || (layoutCoordinates2 = selectable.getLayoutCoordinates()) == null) {
                i10 = size;
            } else {
                int iMin = Math.min(offset, offset2);
                int iMax = Math.max(offset, offset2) - 1;
                if (iMin == iMax) {
                    iArr = new int[1];
                    iArr[c10] = iMin;
                } else {
                    int[] iArr2 = new int[2];
                    iArr2[c10] = iMin;
                    iArr2[1] = iMax;
                    iArr = iArr2;
                }
                Rect rect2 = invertedInfiniteRect;
                float left2 = rect2.getLeft();
                float top2 = rect2.getTop();
                float right2 = rect2.getRight();
                float bottom2 = rect2.getBottom();
                int length = iArr.length;
                i10 = size;
                int i12 = 0;
                while (i12 < length) {
                    int i13 = i12;
                    Rect boundingBox = selectable.getBoundingBox(iArr[i13]);
                    left2 = Math.min(left2, boundingBox.getLeft());
                    top2 = Math.min(top2, boundingBox.getTop());
                    right2 = Math.max(right2, boundingBox.getRight());
                    bottom2 = Math.max(bottom2, boundingBox.getBottom());
                    i12 = i13 + 1;
                }
                long jOffset = OffsetKt.Offset(left2, top2);
                long jOffset2 = OffsetKt.Offset(right2, bottom2);
                long jMo4639localPositionOfR5De75A = layoutCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates2, jOffset);
                long jMo4639localPositionOfR5De75A2 = layoutCoordinates.mo4639localPositionOfR5De75A(layoutCoordinates2, jOffset2);
                left = Math.min(left, Offset.m3236getXimpl(jMo4639localPositionOfR5De75A));
                top = Math.min(top, Offset.m3237getYimpl(jMo4639localPositionOfR5De75A));
                right = Math.max(right, Offset.m3236getXimpl(jMo4639localPositionOfR5De75A2));
                bottom = Math.max(bottom, Offset.m3237getYimpl(jMo4639localPositionOfR5De75A2));
            }
            i11++;
            size = i10;
            c10 = 0;
        }
        return new Rect(left, top, right, bottom);
    }

    public static final Selection merge(Selection selection, Selection selection2) {
        Selection selectionMerge;
        return (selection == null || (selectionMerge = selection.merge(selection2)) == null) ? selection2 : selectionMerge;
    }

    public static final Rect visibleBounds(LayoutCoordinates layoutCoordinates) {
        Rect rectBoundsInWindow = LayoutCoordinatesKt.boundsInWindow(layoutCoordinates);
        return RectKt.m3274Rect0a9Yr6o(layoutCoordinates.mo4643windowToLocalMKHz9U(rectBoundsInWindow.m3271getTopLeftF1C5BW0()), layoutCoordinates.mo4643windowToLocalMKHz9U(rectBoundsInWindow.m3265getBottomRightF1C5BW0()));
    }
}
