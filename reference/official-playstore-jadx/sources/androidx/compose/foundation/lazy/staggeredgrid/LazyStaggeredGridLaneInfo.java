package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.c;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.p;
import kotlin.collections.r;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 +2\u00020\u0001:\u0002+,B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\tJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0003J\u001d\u0010\u0017\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020(0'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*¨\u0006-"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo;", "", "<init>", "()V", "", "capacity", "newOffset", "Lx6/t0;", "ensureCapacity", "(II)V", "itemIndex", "lane", "setLane", "getLane", "(I)I", "targetLane", "", "assignedToLane", "(II)Z", "upperBound", "()I", "lowerBound", "reset", "findPreviousItemIndex", "(II)I", "findNextItemIndex", "requestedIndex", "ensureValidIndex", "(I)V", "", "gaps", "setGaps", "(I[I)V", "getGaps", "(I)[I", LinkHeader.Parameters.Anchor, "I", "lanes", "[I", "Lkotlin/collections/p;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo$SpannedItem;", "spannedItems", "Lkotlin/collections/p;", "Companion", "SpannedItem", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridLaneInfo {
    public static final int FullSpan = -2;
    private static final int MaxCapacity = 131072;
    public static final int Unset = -1;
    private int anchor;
    private int[] lanes = new int[16];
    private final p spannedItems = new p();
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLaneInfo$SpannedItem;", "", "index", "", "gaps", "", "(I[I)V", "getGaps", "()[I", "setGaps", "([I)V", "getIndex", "()I", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SpannedItem {
        private int[] gaps;
        private final int index;

        public SpannedItem(int i10, int[] iArr) {
            this.index = i10;
            this.gaps = iArr;
        }

        public final int[] getGaps() {
            return this.gaps;
        }

        public final int getIndex() {
            return this.index;
        }

        public final void setGaps(int[] iArr) {
            this.gaps = iArr;
        }
    }

    private final void ensureCapacity(int capacity, int newOffset) {
        if (capacity > 131072) {
            throw new IllegalArgumentException(c.o(capacity, "Requested item capacity ", " is larger than max supported: 131072!").toString());
        }
        int[] iArr = this.lanes;
        if (iArr.length < capacity) {
            int length = iArr.length;
            while (length < capacity) {
                length *= 2;
            }
            int[] iArr2 = new int[length];
            r.O(newOffset, 0, 12, this.lanes, iArr2);
            this.lanes = iArr2;
        }
    }

    public static /* synthetic */ void ensureCapacity$default(LazyStaggeredGridLaneInfo lazyStaggeredGridLaneInfo, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        lazyStaggeredGridLaneInfo.ensureCapacity(i10, i11);
    }

    public final boolean assignedToLane(int itemIndex, int targetLane) {
        int lane = getLane(itemIndex);
        return lane == targetLane || lane == -1 || lane == -2;
    }

    public final void ensureValidIndex(int requestedIndex) {
        int i10 = this.anchor;
        int i11 = requestedIndex - i10;
        if (i11 < 0 || i11 >= 131072) {
            int iMax = Math.max(requestedIndex - (this.lanes.length / 2), 0);
            this.anchor = iMax;
            int i12 = iMax - i10;
            if (i12 >= 0) {
                int[] iArr = this.lanes;
                if (i12 < iArr.length) {
                    r.K(0, i12, iArr.length, iArr, iArr);
                }
                int[] iArr2 = this.lanes;
                Arrays.fill(iArr2, Math.max(0, iArr2.length - i12), this.lanes.length, 0);
            } else {
                int i13 = -i12;
                int[] iArr3 = this.lanes;
                if (iArr3.length + i13 < 131072) {
                    ensureCapacity(iArr3.length + i13 + 1, i13);
                } else {
                    if (i13 < iArr3.length) {
                        r.K(i13, 0, iArr3.length - i13, iArr3, iArr3);
                    }
                    int[] iArr4 = this.lanes;
                    Arrays.fill(iArr4, 0, Math.min(iArr4.length, i13), 0);
                }
            }
        } else {
            ensureCapacity$default(this, i11 + 1, 0, 2, null);
        }
        while (!this.spannedItems.isEmpty() && ((SpannedItem) this.spannedItems.first()).getIndex() < getAnchor()) {
            this.spannedItems.removeFirst();
        }
        while (!this.spannedItems.isEmpty() && ((SpannedItem) this.spannedItems.last()).getIndex() > upperBound()) {
            this.spannedItems.removeLast();
        }
    }

    public final int findNextItemIndex(int itemIndex, int targetLane) {
        int iUpperBound = upperBound();
        for (int i10 = itemIndex + 1; i10 < iUpperBound; i10++) {
            if (assignedToLane(i10, targetLane)) {
                return i10;
            }
        }
        return upperBound();
    }

    public final int findPreviousItemIndex(int itemIndex, int targetLane) {
        do {
            itemIndex--;
            if (-1 >= itemIndex) {
                return -1;
            }
        } while (!assignedToLane(itemIndex, targetLane));
        return itemIndex;
    }

    public final int[] getGaps(int itemIndex) {
        p pVar = this.spannedItems;
        SpannedItem spannedItem = (SpannedItem) x.p0(t7.a.b(pVar, pVar.size(), new LazyStaggeredGridLaneInfo$getGaps$$inlined$binarySearchBy$default$1(Integer.valueOf(itemIndex))), this.spannedItems);
        if (spannedItem != null) {
            return spannedItem.getGaps();
        }
        return null;
    }

    public final int getLane(int itemIndex) {
        if (itemIndex < getAnchor() || itemIndex >= upperBound()) {
            return -1;
        }
        return this.lanes[itemIndex - this.anchor] - 1;
    }

    /* JADX INFO: renamed from: lowerBound, reason: from getter */
    public final int getAnchor() {
        return this.anchor;
    }

    public final void reset() {
        r.U(this.lanes, 0, 0, 6);
        this.spannedItems.clear();
    }

    public final void setGaps(int itemIndex, int[] gaps) {
        p pVar = this.spannedItems;
        int iB = t7.a.b(pVar, pVar.size(), new LazyStaggeredGridLaneInfo$setGaps$$inlined$binarySearchBy$default$1(Integer.valueOf(itemIndex)));
        if (iB < 0) {
            if (gaps == null) {
                return;
            }
            this.spannedItems.add(-(iB + 1), new SpannedItem(itemIndex, gaps));
            return;
        }
        if (gaps == null) {
            this.spannedItems.remove(iB);
        } else {
            ((SpannedItem) this.spannedItems.get(iB)).setGaps(gaps);
        }
    }

    public final void setLane(int itemIndex, int lane) {
        if (itemIndex < 0) {
            throw new IllegalArgumentException("Negative lanes are not supported");
        }
        ensureValidIndex(itemIndex);
        this.lanes[itemIndex - this.anchor] = lane + 1;
    }

    public final int upperBound() {
        return this.anchor + this.lanes.length;
    }
}
