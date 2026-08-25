package androidx.compose.foundation.lazy.grid;

import a0.c;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutNearestRangeState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u0006J\u0015\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0006J\u001d\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015R+\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00028F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u0010R+\u0010\b\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00028F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u0018\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u0010R\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(¨\u0006)"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridScrollPosition;", "", "", "initialIndex", "initialScrollOffset", "<init>", "(II)V", "index", "scrollOffset", "Lx6/t0;", "update", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "measureResult", "updateFromMeasureResult", "(Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;)V", "updateScrollOffset", "(I)V", "requestPosition", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "itemProvider", "updateScrollPositionIfTheFirstItemWasMoved", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;I)I", "<set-?>", "index$delegate", "Landroidx/compose/runtime/MutableIntState;", "getIndex", "()I", "setIndex", "scrollOffset$delegate", "getScrollOffset", "setScrollOffset", "", "hadFirstNotEmptyLayout", "Z", "lastKnownFirstItemKey", "Ljava/lang/Object;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "nearestRangeState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "getNearestRangeState", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyGridScrollPosition {
    public static final int $stable = 8;
    private boolean hadFirstNotEmptyLayout;

    /* JADX INFO: renamed from: index$delegate, reason: from kotlin metadata */
    private final MutableIntState index;
    private Object lastKnownFirstItemKey;
    private final LazyLayoutNearestRangeState nearestRangeState;

    /* JADX INFO: renamed from: scrollOffset$delegate, reason: from kotlin metadata */
    private final MutableIntState scrollOffset;

    /* JADX WARN: Illegal instructions before constructor call */
    public LazyGridScrollPosition() {
        int i10 = 0;
        this(i10, i10, 3, null);
    }

    private final void setIndex(int i10) {
        this.index.setIntValue(i10);
    }

    private final void setScrollOffset(int i10) {
        this.scrollOffset.setIntValue(i10);
    }

    private final void update(int index, int scrollOffset) {
        if (index < 0.0f) {
            throw new IllegalArgumentException(c.k("Index should be non-negative (", index, ')').toString());
        }
        setIndex(index);
        this.nearestRangeState.update(index);
        setScrollOffset(scrollOffset);
    }

    public final int getIndex() {
        return this.index.getIntValue();
    }

    public final LazyLayoutNearestRangeState getNearestRangeState() {
        return this.nearestRangeState;
    }

    public final int getScrollOffset() {
        return this.scrollOffset.getIntValue();
    }

    public final void requestPosition(int index, int scrollOffset) {
        update(index, scrollOffset);
        this.lastKnownFirstItemKey = null;
    }

    public final void updateFromMeasureResult(LazyGridMeasureResult measureResult) {
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        LazyGridMeasuredItem[] items2;
        LazyGridMeasuredItem lazyGridMeasuredItem2;
        LazyGridMeasuredLine firstVisibleLine = measureResult.getFirstVisibleLine();
        this.lastKnownFirstItemKey = (firstVisibleLine == null || (items2 = firstVisibleLine.getItems()) == null || (lazyGridMeasuredItem2 = (LazyGridMeasuredItem) r.Z(items2)) == null) ? null : lazyGridMeasuredItem2.getKey();
        if (this.hadFirstNotEmptyLayout || measureResult.getTotalItemsCount() > 0) {
            this.hadFirstNotEmptyLayout = true;
            int firstVisibleLineScrollOffset = measureResult.getFirstVisibleLineScrollOffset();
            if (firstVisibleLineScrollOffset < 0.0f) {
                throw new IllegalStateException(c.k("scrollOffset should be non-negative (", firstVisibleLineScrollOffset, ')').toString());
            }
            LazyGridMeasuredLine firstVisibleLine2 = measureResult.getFirstVisibleLine();
            update((firstVisibleLine2 == null || (items = firstVisibleLine2.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) r.Z(items)) == null) ? 0 : lazyGridMeasuredItem.getIndex(), firstVisibleLineScrollOffset);
        }
    }

    public final void updateScrollOffset(int scrollOffset) {
        if (scrollOffset < 0.0f) {
            throw new IllegalStateException(c.k("scrollOffset should be non-negative (", scrollOffset, ')').toString());
        }
        setScrollOffset(scrollOffset);
    }

    public final int updateScrollPositionIfTheFirstItemWasMoved(LazyGridItemProvider itemProvider, int index) {
        int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(itemProvider, this.lastKnownFirstItemKey, index);
        if (index != iFindIndexByKey) {
            setIndex(iFindIndexByKey);
            this.nearestRangeState.update(index);
        }
        return iFindIndexByKey;
    }

    public LazyGridScrollPosition(int i10, int i11) {
        this.index = SnapshotIntStateKt.mutableIntStateOf(i10);
        this.scrollOffset = SnapshotIntStateKt.mutableIntStateOf(i11);
        this.nearestRangeState = new LazyLayoutNearestRangeState(i10, 90, 200);
    }

    public /* synthetic */ LazyGridScrollPosition(int i10, int i11, int i12, h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11);
    }
}
