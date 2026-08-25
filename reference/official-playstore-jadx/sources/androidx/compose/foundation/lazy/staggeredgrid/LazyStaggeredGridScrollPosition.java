package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutNearestRangeState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.n;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010 \u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006¢\u0006\u0004\b \u0010!J\u001f\u0010$\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u000e\u001a\u00020\u0002H\u0007¢\u0006\u0004\b$\u0010%J\u001f\u0010)\u001a\u00020(2\u0006\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u0002H\u0016¢\u0006\u0004\b)\u0010*RD\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010+R+\u0010\u000e\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u00028F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u0010\u001dR+\u0010\u001e\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R+\u0010\u001b\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u00028F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010.\u001a\u0004\b9\u00100\"\u0004\b:\u0010\u001dR+\u0010\u001f\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b;\u00103\u001a\u0004\b<\u00105\"\u0004\b=\u00107R\u0016\u0010>\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0017\u0010D\u001a\u00020C8\u0006¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G¨\u0006H"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScrollPosition;", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "", "initialIndices", "initialOffsets", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "targetIndex", "laneCount", "fillIndices", "<init>", "([I[ILr7/p;)V", "indices", "calculateFirstVisibleIndex", "([I)I", "offsets", "calculateFirstVisibleScrollOffset", "([I[I)I", "Lx6/t0;", "update", "([I[I)V", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "measureResult", "updateFromMeasureResult", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;)V", "scrollOffsets", "updateScrollOffset", "([I)V", "index", "scrollOffset", "requestPosition", "(II)V", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "itemProvider", "updateScrollPositionIfTheFirstItemWasMoved", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;[I)[I", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "", "equivalent", "([I[I)Z", "Lr7/p;", "<set-?>", "indices$delegate", "Landroidx/compose/runtime/MutableState;", "getIndices", "()[I", "setIndices", "index$delegate", "Landroidx/compose/runtime/MutableIntState;", "getIndex", "()I", "setIndex", "(I)V", "scrollOffsets$delegate", "getScrollOffsets", "setScrollOffsets", "scrollOffset$delegate", "getScrollOffset", "setScrollOffset", "hadFirstNotEmptyLayout", "Z", "", "lastKnownFirstItemKey", "Ljava/lang/Object;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "nearestRangeState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "getNearestRangeState", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridScrollPosition implements SnapshotMutationPolicy<int[]> {
    public static final int $stable = 8;
    private final p<Integer, Integer, int[]> fillIndices;
    private boolean hadFirstNotEmptyLayout;

    /* JADX INFO: renamed from: index$delegate, reason: from kotlin metadata */
    private final MutableIntState index;

    /* JADX INFO: renamed from: indices$delegate, reason: from kotlin metadata */
    private final MutableState indices;
    private Object lastKnownFirstItemKey;
    private final LazyLayoutNearestRangeState nearestRangeState;

    /* JADX INFO: renamed from: scrollOffset$delegate, reason: from kotlin metadata */
    private final MutableIntState scrollOffset;

    /* JADX INFO: renamed from: scrollOffsets$delegate, reason: from kotlin metadata */
    private final MutableState scrollOffsets;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridScrollPosition(int[] iArr, int[] iArr2, p<? super Integer, ? super Integer, int[]> pVar) {
        Integer numValueOf;
        this.fillIndices = pVar;
        this.indices = SnapshotStateKt.mutableStateOf(iArr, this);
        this.index = SnapshotIntStateKt.mutableIntStateOf(calculateFirstVisibleIndex(iArr));
        this.scrollOffsets = SnapshotStateKt.mutableStateOf(iArr2, this);
        this.scrollOffset = SnapshotIntStateKt.mutableIntStateOf(calculateFirstVisibleScrollOffset(iArr, iArr2));
        if (iArr.length == 0) {
            numValueOf = null;
        } else {
            int i10 = iArr[0];
            int i11 = 1;
            int length = iArr.length - 1;
            if (1 <= length) {
                while (true) {
                    int i12 = iArr[i11];
                    i10 = i10 > i12 ? i12 : i10;
                    if (i11 == length) {
                        break;
                    } else {
                        i11++;
                    }
                }
            }
            numValueOf = Integer.valueOf(i10);
        }
        this.nearestRangeState = new LazyLayoutNearestRangeState(numValueOf != null ? numValueOf.intValue() : 0, 90, 200);
    }

    private final int calculateFirstVisibleIndex(int[] indices) {
        int i10 = Integer.MAX_VALUE;
        for (int i11 : indices) {
            if (i11 <= 0) {
                return 0;
            }
            if (i10 > i11) {
                i10 = i11;
            }
        }
        if (i10 == Integer.MAX_VALUE) {
            return 0;
        }
        return i10;
    }

    private final int calculateFirstVisibleScrollOffset(int[] indices, int[] offsets) {
        int iCalculateFirstVisibleIndex = calculateFirstVisibleIndex(indices);
        int length = offsets.length;
        int iMin = Integer.MAX_VALUE;
        for (int i10 = 0; i10 < length; i10++) {
            if (indices[i10] == iCalculateFirstVisibleIndex) {
                iMin = Math.min(iMin, offsets[i10]);
            }
        }
        if (iMin == Integer.MAX_VALUE) {
            return 0;
        }
        return iMin;
    }

    private final void setIndex(int i10) {
        this.index.setIntValue(i10);
    }

    private final void setIndices(int[] iArr) {
        this.indices.setValue(iArr);
    }

    private final void setScrollOffset(int i10) {
        this.scrollOffset.setIntValue(i10);
    }

    private final void setScrollOffsets(int[] iArr) {
        this.scrollOffsets.setValue(iArr);
    }

    private final void update(int[] indices, int[] offsets) {
        setIndices(indices);
        setIndex(calculateFirstVisibleIndex(indices));
        setScrollOffsets(offsets);
        setScrollOffset(calculateFirstVisibleScrollOffset(indices, offsets));
    }

    public final int getIndex() {
        return this.index.getIntValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int[] getIndices() {
        return (int[]) this.indices.getValue();
    }

    public final LazyLayoutNearestRangeState getNearestRangeState() {
        return this.nearestRangeState;
    }

    public final int getScrollOffset() {
        return this.scrollOffset.getIntValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int[] getScrollOffsets() {
        return (int[]) this.scrollOffsets.getValue();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [int[], java.lang.Object] */
    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public final /* synthetic */ int[] merge(int[] iArr, int[] iArr2, int[] iArr3) {
        return n.a(this, iArr, iArr2, iArr3);
    }

    public final void requestPosition(int index, int scrollOffset) {
        int[] iArr = (int[]) this.fillIndices.invoke(Integer.valueOf(index), Integer.valueOf(getIndices().length));
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i10 = 0; i10 < length; i10++) {
            iArr2[i10] = scrollOffset;
        }
        update(iArr, iArr2);
        this.nearestRangeState.update(index);
        this.lastKnownFirstItemKey = null;
    }

    public final void updateFromMeasureResult(LazyStaggeredGridMeasureResult measureResult) {
        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem;
        int iCalculateFirstVisibleIndex = calculateFirstVisibleIndex(measureResult.getFirstVisibleItemIndices());
        List<LazyStaggeredGridMeasuredItem> visibleItemsInfo = measureResult.getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                lazyStaggeredGridMeasuredItem = null;
                break;
            }
            lazyStaggeredGridMeasuredItem = visibleItemsInfo.get(i10);
            if (lazyStaggeredGridMeasuredItem.getIndex() == iCalculateFirstVisibleIndex) {
                break;
            } else {
                i10++;
            }
        }
        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = lazyStaggeredGridMeasuredItem;
        this.lastKnownFirstItemKey = lazyStaggeredGridMeasuredItem2 != null ? lazyStaggeredGridMeasuredItem2.getKey() : null;
        this.nearestRangeState.update(iCalculateFirstVisibleIndex);
        if (this.hadFirstNotEmptyLayout || measureResult.getTotalItemsCount() > 0) {
            this.hadFirstNotEmptyLayout = true;
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                try {
                    update(measureResult.getFirstVisibleItemIndices(), measureResult.getFirstVisibleItemScrollOffsets());
                } finally {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                }
            } finally {
                snapshotCreateNonObservableSnapshot.dispose();
            }
        }
    }

    public final void updateScrollOffset(int[] scrollOffsets) {
        setScrollOffsets(scrollOffsets);
        setScrollOffset(calculateFirstVisibleScrollOffset(getIndices(), scrollOffsets));
    }

    public final int[] updateScrollPositionIfTheFirstItemWasMoved(LazyLayoutItemProvider itemProvider, int[] indices) {
        Object obj = this.lastKnownFirstItemKey;
        Integer numB0 = r.b0(indices, 0);
        int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(itemProvider, obj, numB0 != null ? numB0.intValue() : 0);
        if (r.I(indices, iFindIndexByKey)) {
            return indices;
        }
        this.nearestRangeState.update(iFindIndexByKey);
        int[] iArr = (int[]) this.fillIndices.invoke(Integer.valueOf(iFindIndexByKey), Integer.valueOf(indices.length));
        setIndices(iArr);
        setIndex(calculateFirstVisibleIndex(iArr));
        return iArr;
    }

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public boolean equivalent(int[] a10, int[] b10) {
        return Arrays.equals(a10, b10);
    }
}
