package androidx.compose.foundation.lazy.layout;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import kotlin.Metadata;
import t.e0;
import t.f0;
import t.y;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/lazy/layout/NearestRangeKeyIndexMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "Lx7/i;", "nearestRange", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "intervalContent", "<init>", "(Lx7/i;Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;)V", "", "key", "", "getIndex", "(Ljava/lang/Object;)I", "index", "getKey", "(I)Ljava/lang/Object;", "Lt/e0;", "map", "Lt/e0;", "", UserMetadata.KEYDATA_FILENAME, "[Ljava/lang/Object;", "keysStartIndex", "I", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NearestRangeKeyIndexMap implements LazyLayoutKeyIndexMap {
    public static final int $stable = 8;
    private final Object[] keys;
    private final int keysStartIndex;
    private final e0 map;

    public NearestRangeKeyIndexMap(i iVar, LazyLayoutIntervalContent<?> lazyLayoutIntervalContent) {
        IntervalList<Interval> intervals = lazyLayoutIntervalContent.getIntervals();
        int i10 = iVar.f22619i;
        if (i10 < 0) {
            throw new IllegalStateException("negative nearestRange.first");
        }
        int iMin = Math.min(iVar.f22620l, intervals.getSize() - 1);
        if (iMin < i10) {
            this.map = f0.f21812a;
            this.keys = new Object[0];
            this.keysStartIndex = 0;
        } else {
            int i11 = (iMin - i10) + 1;
            this.keys = new Object[i11];
            this.keysStartIndex = i10;
            y yVar = new y(i11);
            intervals.forEach(i10, iMin, new NearestRangeKeyIndexMap$2$1(i10, iMin, yVar, this));
            this.map = yVar;
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public int getIndex(Object key) {
        e0 e0Var = this.map;
        int iA = e0Var.a(key);
        if (iA >= 0) {
            return e0Var.f21806c[iA];
        }
        return -1;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap
    public Object getKey(int index) {
        Object[] objArr = this.keys;
        int i10 = index - this.keysStartIndex;
        if (i10 < 0 || i10 > objArr.length - 1) {
            return null;
        }
        return objArr[i10];
    }
}
