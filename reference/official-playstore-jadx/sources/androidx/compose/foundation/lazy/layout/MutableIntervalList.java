package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.runtime.collection.MutableVector;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000f\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0004\b\u0013\u0010\u0014J9\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0018\u0010\u0018\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0004\u0012\u00020\u000b0\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\n\u001a\u00020\u0005H\u0096\u0002¢\u0006\u0004\b\u001b\u0010\tR \u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR$\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00058\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u0011\u0010 \u001a\u0004\b!\u0010\"R\u001e\u0010#\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "T", "Landroidx/compose/foundation/lazy/layout/IntervalList;", "<init>", "()V", "", "itemIndex", "Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;", "getIntervalForIndex", "(I)Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;", "index", "Lx6/t0;", "checkIndexBounds", "(I)V", "", "contains", "(Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;I)Z", ContentDisposition.Parameters.Size, "value", "addInterval", "(ILjava/lang/Object;)V", "fromIndex", "toIndex", "Lkotlin/Function1;", "block", "forEach", "(IILr7/l;)V", "get", "Landroidx/compose/runtime/collection/MutableVector;", "intervals", "Landroidx/compose/runtime/collection/MutableVector;", "<set-?>", "I", "getSize", "()I", "lastInterval", "Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MutableIntervalList<T> implements IntervalList<T> {
    public static final int $stable = 8;
    private final MutableVector<IntervalList.Interval<T>> intervals = new MutableVector<>(new IntervalList.Interval[16], 0);
    private IntervalList.Interval<? extends T> lastInterval;
    private int size;

    private final void checkIndexBounds(int index) {
        if (index < 0 || index >= getSize()) {
            StringBuilder sbS = a0.c.s(index, "Index ", ", size ");
            sbS.append(getSize());
            throw new IndexOutOfBoundsException(sbS.toString());
        }
    }

    private final boolean contains(IntervalList.Interval<? extends T> interval, int i10) {
        return i10 < interval.getSize() + interval.getStartIndex() && interval.getStartIndex() <= i10;
    }

    private final IntervalList.Interval<T> getIntervalForIndex(int itemIndex) {
        IntervalList.Interval<? extends T> interval = this.lastInterval;
        if (interval != null && contains(interval, itemIndex)) {
            return interval;
        }
        MutableVector<IntervalList.Interval<T>> mutableVector = this.intervals;
        IntervalList.Interval interval2 = (IntervalList.Interval<? extends T>) mutableVector.getContent()[IntervalListKt.binarySearch(mutableVector, itemIndex)];
        this.lastInterval = interval2;
        return interval2;
    }

    public final void addInterval(int size, T value) {
        if (size < 0) {
            throw new IllegalArgumentException(a0.c.i(size, "size should be >=0, but was ").toString());
        }
        if (size == 0) {
            return;
        }
        IntervalList.Interval<T> interval = new IntervalList.Interval<>(getSize(), size, value);
        this.size = getSize() + size;
        this.intervals.add(interval);
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public void forEach(int fromIndex, int toIndex, l<? super IntervalList.Interval<? extends T>, t0> block) {
        checkIndexBounds(fromIndex);
        checkIndexBounds(toIndex);
        if (toIndex < fromIndex) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.r("toIndex (", toIndex, ") should be not smaller than fromIndex (", fromIndex, ')').toString());
        }
        int iBinarySearch = IntervalListKt.binarySearch(this.intervals, fromIndex);
        int startIndex = this.intervals.getContent()[iBinarySearch].getStartIndex();
        while (startIndex <= toIndex) {
            IntervalList.Interval<T> interval = this.intervals.getContent()[iBinarySearch];
            block.invoke(interval);
            startIndex += interval.getSize();
            iBinarySearch++;
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public IntervalList.Interval<T> get(int index) {
        checkIndexBounds(index);
        return getIntervalForIndex(index);
    }

    @Override // androidx.compose.foundation.lazy.layout.IntervalList
    public int getSize() {
        return this.size;
    }
}
