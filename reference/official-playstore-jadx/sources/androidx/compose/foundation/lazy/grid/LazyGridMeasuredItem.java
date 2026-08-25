package androidx.compose.foundation.lazy.grid;

import a0.c;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0000\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J+\u0010\u001d\u001a\u00020\u0012*\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0019H\u0082\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u001e\u0010\u001fJA\u0010'\u001a\u00020&2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00022\b\b\u0002\u0010$\u001a\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u0002¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020&2\u0006\u0010)\u001a\u00020\u0002¢\u0006\u0004\b*\u0010+J\u0015\u0010.\u001a\u00020&2\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b.\u0010/R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u00100\u001a\u0004\b1\u00102R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u00103\u001a\u0004\b4\u00105R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00106\u001a\u0004\b\u0007\u00107R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u00100\u001a\u0004\b8\u00102R\u0014\u0010\n\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u00106R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u00109R\u0014\u0010\r\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u00100R\u0014\u0010\u000e\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u00100R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010:R\u001a\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0013\u0010;R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u00103\u001a\u0004\b<\u00105R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010=R\u0017\u0010>\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b>\u00100\u001a\u0004\b?\u00102R\u0017\u0010@\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b@\u00100\u001a\u0004\bA\u00102R\u0016\u0010B\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u00100R\u0016\u0010C\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u00100R\u0016\u0010D\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u00100R \u0010F\u001a\u00020E8\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bF\u0010;\u001a\u0004\bG\u0010HR*\u0010J\u001a\u00020\u00122\u0006\u0010I\u001a\u00020\u00128\u0016@RX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bJ\u0010;\u001a\u0004\bK\u0010HR$\u0010$\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00028\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b$\u00100\u001a\u0004\bL\u00102R$\u0010%\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00028\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b%\u00100\u001a\u0004\bM\u00102R\"\u0010N\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u00106\u001a\u0004\bO\u00107\"\u0004\bP\u0010QR\u0018\u0010T\u001a\u00020\u0002*\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0018\u0010>\u001a\u00020\u0002*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b?\u0010UR\u0011\u0010W\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bV\u00102R\u0011\u0010!\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bX\u00102\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Y"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemInfo;", "", "index", "", "key", "", "isVertical", "crossAxisSize", "mainAxisSpacing", "reverseLayout", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "beforeContentPadding", "afterContentPadding", "", "Landroidx/compose/ui/layout/Placeable;", "placeables", "Landroidx/compose/ui/unit/IntOffset;", "visualOffset", "contentType", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "animator", "<init>", "(ILjava/lang/Object;ZIIZLandroidx/compose/ui/unit/LayoutDirection;IILjava/util/List;JLjava/lang/Object;Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;Lkotlin/jvm/internal/h;)V", "Lkotlin/Function1;", "mainAxisMap", "copy-4Tuh3kE", "(JLr7/l;)J", "copy", "getParentData", "(I)Ljava/lang/Object;", "mainAxisOffset", "crossAxisOffset", "layoutWidth", "layoutHeight", "row", "column", "Lx6/t0;", "position", "(IIIIII)V", "delta", "applyScrollDelta", "(I)V", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "scope", "place", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "I", "getIndex", "()I", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "Z", "()Z", "getCrossAxisSize", "Landroidx/compose/ui/unit/LayoutDirection;", "Ljava/util/List;", "J", "getContentType", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "mainAxisSize", "getMainAxisSize", "mainAxisSizeWithSpacings", "getMainAxisSizeWithSpacings", "mainAxisLayoutSize", "minMainAxisOffset", "maxMainAxisOffset", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "getSize-YbymL2g", "()J", "<set-?>", "offset", "getOffset-nOcc-ac", "getRow", "getColumn", "nonScrollableItem", "getNonScrollableItem", "setNonScrollableItem", "(Z)V", "getMainAxis--gyyYBs", "(J)I", "mainAxis", "(Landroidx/compose/ui/layout/Placeable;)I", "getPlaceablesCount", "placeablesCount", "getCrossAxisOffset", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyGridMeasuredItem implements LazyGridItemInfo {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final LazyGridItemPlacementAnimator animator;
    private final int beforeContentPadding;
    private int column;
    private final Object contentType;
    private final int crossAxisSize;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private final int mainAxisSize;
    private final int mainAxisSizeWithSpacings;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private long offset;
    private final List<Placeable> placeables;
    private final boolean reverseLayout;
    private int row;
    private final long size;
    private final long visualOffset;

    public /* synthetic */ LazyGridMeasuredItem(int i10, Object obj, boolean z, int i11, int i12, boolean z5, LayoutDirection layoutDirection, int i13, int i14, List list, long j10, Object obj2, LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator, h hVar) {
        this(i10, obj, z, i11, i12, z5, layoutDirection, i13, i14, list, j10, obj2, lazyGridItemPlacementAnimator);
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m666copy4Tuh3kE(long j10, l<? super Integer, Integer> lVar) {
        int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(j10) : ((Number) lVar.invoke(Integer.valueOf(IntOffset.m5802getXimpl(j10)))).intValue();
        boolean z = this.isVertical;
        int iM5803getYimpl = IntOffset.m5803getYimpl(j10);
        if (z) {
            iM5803getYimpl = ((Number) lVar.invoke(Integer.valueOf(iM5803getYimpl))).intValue();
        }
        return IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m667getMainAxisgyyYBs(long j10) {
        return this.isVertical ? IntOffset.m5803getYimpl(j10) : IntOffset.m5802getXimpl(j10);
    }

    public static /* synthetic */ void position$default(LazyGridMeasuredItem lazyGridMeasuredItem, int i10, int i11, int i12, int i13, int i14, int i15, int i16, Object obj) {
        if ((i16 & 16) != 0) {
            i14 = -1;
        }
        if ((i16 & 32) != 0) {
            i15 = -1;
        }
        lazyGridMeasuredItem.position(i10, i11, i12, i13, i14, i15);
    }

    public final void applyScrollDelta(int delta) {
        if (this.nonScrollableItem) {
            return;
        }
        long offset = getOffset();
        int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(offset) : IntOffset.m5802getXimpl(offset) + delta;
        boolean z = this.isVertical;
        int iM5803getYimpl = IntOffset.m5803getYimpl(offset);
        if (z) {
            iM5803getYimpl += delta;
        }
        this.offset = IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
        int placeablesCount = getPlaceablesCount();
        for (int i10 = 0; i10 < placeablesCount; i10++) {
            LazyLayoutAnimation animation = this.animator.getAnimation(getKey(), i10);
            if (animation != null) {
                long rawOffset = animation.getRawOffset();
                int iM5802getXimpl2 = this.isVertical ? IntOffset.m5802getXimpl(rawOffset) : Integer.valueOf(IntOffset.m5802getXimpl(rawOffset) + delta).intValue();
                boolean z5 = this.isVertical;
                int iM5803getYimpl2 = IntOffset.m5803getYimpl(rawOffset);
                if (z5) {
                    iM5803getYimpl2 += delta;
                }
                animation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(iM5802getXimpl2, iM5803getYimpl2));
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getColumn() {
        return this.column;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    public final int getCrossAxisOffset() {
        return this.isVertical ? IntOffset.m5802getXimpl(getOffset()) : IntOffset.m5803getYimpl(getOffset());
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public Object getKey() {
        return this.key;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    public final boolean getNonScrollableItem() {
        return this.nonScrollableItem;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    /* JADX INFO: renamed from: getOffset-nOcc-ac, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    public final Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    public final int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    public int getRow() {
        return this.row;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridItemInfo
    /* JADX INFO: renamed from: getSize-YbymL2g, reason: from getter */
    public long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public final void place(Placeable.PlacementScope scope) {
        Placeable.PlacementScope placementScope;
        int iM5802getXimpl;
        int iM5803getYimpl;
        if (this.mainAxisLayoutSize == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("position() should be called first");
        }
        int placeablesCount = getPlaceablesCount();
        int i10 = 0;
        while (i10 < placeablesCount) {
            Placeable placeable = this.placeables.get(i10);
            int mainAxisSize = this.minMainAxisOffset - getMainAxisSize(placeable);
            int i11 = this.maxMainAxisOffset;
            long offset = getOffset();
            LazyLayoutAnimation animation = this.animator.getAnimation(getKey(), i10);
            if (animation != null) {
                long jM677getPlacementDeltanOccac = animation.m677getPlacementDeltanOccac();
                long jC = c.c(IntOffset.m5803getYimpl(offset), IntOffset.m5802getXimpl(jM677getPlacementDeltanOccac) + IntOffset.m5802getXimpl(offset), jM677getPlacementDeltanOccac);
                if ((m667getMainAxisgyyYBs(offset) <= mainAxisSize && m667getMainAxisgyyYBs(jC) <= mainAxisSize) || (m667getMainAxisgyyYBs(offset) >= i11 && m667getMainAxisgyyYBs(jC) >= i11)) {
                    animation.cancelPlacementAnimation();
                }
                offset = jC;
            }
            if (this.reverseLayout) {
                if (this.isVertical) {
                    iM5802getXimpl = IntOffset.m5802getXimpl(offset);
                } else {
                    iM5802getXimpl = (this.mainAxisLayoutSize - IntOffset.m5802getXimpl(offset)) - getMainAxisSize(placeable);
                }
                if (this.isVertical) {
                    iM5803getYimpl = (this.mainAxisLayoutSize - IntOffset.m5803getYimpl(offset)) - getMainAxisSize(placeable);
                } else {
                    iM5803getYimpl = IntOffset.m5803getYimpl(offset);
                }
                offset = IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
            }
            long j10 = this.visualOffset;
            long jC2 = c.c(IntOffset.m5803getYimpl(offset), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(offset), j10);
            if (this.isVertical) {
                placementScope = scope;
                Placeable.PlacementScope.m4672placeWithLayeraW9wM$default(placementScope, placeable, jC2, 0.0f, null, 6, null);
            } else {
                placementScope = scope;
                Placeable.PlacementScope.m4671placeRelativeWithLayeraW9wM$default(placementScope, placeable, jC2, 0.0f, null, 6, null);
            }
            i10++;
            scope = placementScope;
        }
    }

    public final void position(int mainAxisOffset, int crossAxisOffset, int layoutWidth, int layoutHeight, int row, int column) {
        boolean z = this.isVertical;
        this.mainAxisLayoutSize = z ? layoutHeight : layoutWidth;
        if (!z) {
            layoutWidth = layoutHeight;
        }
        if (z && this.layoutDirection == LayoutDirection.Rtl) {
            crossAxisOffset = (layoutWidth - crossAxisOffset) - this.crossAxisSize;
        }
        this.offset = z ? IntOffsetKt.IntOffset(crossAxisOffset, mainAxisOffset) : IntOffsetKt.IntOffset(mainAxisOffset, crossAxisOffset);
        this.row = row;
        this.column = column;
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.mainAxisLayoutSize + this.afterContentPadding;
    }

    public final void setNonScrollableItem(boolean z) {
        this.nonScrollableItem = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyGridMeasuredItem(int i10, Object obj, boolean z, int i11, int i12, boolean z5, LayoutDirection layoutDirection, int i13, int i14, List<? extends Placeable> list, long j10, Object obj2, LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator) {
        this.index = i10;
        this.key = obj;
        this.isVertical = z;
        this.crossAxisSize = i11;
        this.reverseLayout = z5;
        this.layoutDirection = layoutDirection;
        this.beforeContentPadding = i13;
        this.afterContentPadding = i14;
        this.placeables = list;
        this.visualOffset = j10;
        this.contentType = obj2;
        this.animator = lazyGridItemPlacementAnimator;
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
        int size = list.size();
        int iMax = 0;
        for (int i15 = 0; i15 < size; i15++) {
            Placeable placeable = (Placeable) list.get(i15);
            iMax = Math.max(iMax, this.isVertical ? placeable.getHeight() : placeable.getWidth());
        }
        this.mainAxisSize = iMax;
        int i16 = i12 + iMax;
        this.mainAxisSizeWithSpacings = i16 >= 0 ? i16 : 0;
        this.size = this.isVertical ? IntSizeKt.IntSize(this.crossAxisSize, iMax) : IntSizeKt.IntSize(iMax, this.crossAxisSize);
        this.offset = IntOffset.INSTANCE.m5812getZeronOccac();
        this.row = -1;
        this.column = -1;
    }

    private final int getMainAxisSize(Placeable placeable) {
        return this.isVertical ? placeable.getHeight() : placeable.getWidth();
    }
}
