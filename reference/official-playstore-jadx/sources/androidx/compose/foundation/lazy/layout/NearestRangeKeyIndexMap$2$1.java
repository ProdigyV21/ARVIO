package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import t.y;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent$Interval;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NearestRangeKeyIndexMap$2$1 extends r implements l<IntervalList.Interval<? extends LazyLayoutIntervalContent.Interval>, t0> {
    final /* synthetic */ int $first;
    final /* synthetic */ int $last;
    final /* synthetic */ y $map;
    final /* synthetic */ NearestRangeKeyIndexMap this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NearestRangeKeyIndexMap$2$1(int i10, int i11, y yVar, NearestRangeKeyIndexMap nearestRangeKeyIndexMap) {
        super(1);
        this.$first = i10;
        this.$last = i11;
        this.$map = yVar;
        this.this$0 = nearestRangeKeyIndexMap;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((IntervalList.Interval<? extends LazyLayoutIntervalContent.Interval>) obj);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke(androidx.compose.foundation.lazy.layout.IntervalList.Interval<? extends androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval> r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r8.getValue()
            androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent$Interval r0 = (androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval) r0
            r7.l r0 = r0.getKey()
            int r1 = r7.$first
            int r2 = r8.getStartIndex()
            int r1 = java.lang.Math.max(r1, r2)
            int r2 = r7.$last
            int r3 = r8.getStartIndex()
            int r4 = r8.getSize()
            int r4 = r4 + r3
            int r4 = r4 + (-1)
            int r2 = java.lang.Math.min(r2, r4)
            if (r1 > r2) goto L63
        L27:
            if (r0 == 0) goto L39
            int r3 = r8.getStartIndex()
            int r3 = r1 - r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r3 = r0.invoke(r3)
            if (r3 != 0) goto L3d
        L39:
            java.lang.Object r3 = androidx.compose.foundation.lazy.layout.Lazy_androidKt.getDefaultLazyLayoutKey(r1)
        L3d:
            t.y r4 = r7.$map
            int r5 = r4.c(r3)
            if (r5 >= 0) goto L46
            int r5 = ~r5
        L46:
            java.lang.Object[] r6 = r4.f21805b
            r6[r5] = r3
            int[] r4 = r4.f21806c
            r4[r5] = r1
            androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap r4 = r7.this$0
            java.lang.Object[] r4 = androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap.access$getKeys$p(r4)
            androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap r5 = r7.this$0
            int r5 = androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap.access$getKeysStartIndex$p(r5)
            int r5 = r1 - r5
            r4[r5] = r3
            if (r1 == r2) goto L63
            int r1 = r1 + 1
            goto L27
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap$2$1.invoke(androidx.compose.foundation.lazy.layout.IntervalList$Interval):void");
    }
}
