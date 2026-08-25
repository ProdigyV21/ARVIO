package i2;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import java.util.HashMap;
import kotlin.collections.a0;

/* JADX INFO: loaded from: classes3.dex */
public final class r implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f16073a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f16074b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f16075c;

    public r(x7.i iVar, LazyLayoutIntervalContent lazyLayoutIntervalContent) {
        IntervalList intervals = lazyLayoutIntervalContent.getIntervals();
        int i10 = iVar.f22619i;
        if (i10 < 0) {
            throw new IllegalStateException("negative nearestRange.first");
        }
        int iMin = Math.min(iVar.f22620l, intervals.getSize() - 1);
        if (iMin < i10) {
            this.f16073a = a0.f19683i;
            this.f16074b = new Object[0];
            this.f16075c = 0;
        } else {
            this.f16074b = new Object[(iMin - i10) + 1];
            this.f16075c = i10;
            HashMap map = new HashMap();
            intervals.forEach(i10, iMin, new q(i10, iMin, map, this));
            this.f16073a = map;
        }
    }

    public final Object a(int i10) {
        int i11 = i10 - this.f16075c;
        if (i11 < 0) {
            return null;
        }
        Object[] objArr = this.f16074b;
        if (i11 <= objArr.length - 1) {
            return objArr[i11];
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    @Override // i2.k
    public final int getIndex(Object obj) {
        Object obj2 = this.f16073a.get(obj);
        if (obj2 == null) {
            obj2 = -1;
        }
        return ((Number) obj2).intValue();
    }
}
