package i2;

import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16069i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f16070l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ HashMap f16071m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r f16072n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(int i10, int i11, HashMap map, r rVar) {
        super(1);
        this.f16069i = i10;
        this.f16070l = i11;
        this.f16071m = map;
        this.f16072n = rVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054 A[LOOP:0: B:4:0x0029->B:10:0x0054, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0057 A[EDGE_INSN: B:13:0x0057->B:11:0x0057 BREAK  A[LOOP:0: B:4:0x0029->B:10:0x0054], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003b  */
    @Override // r7.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(java.lang.Object r7) {
        /*
            r6 = this;
            androidx.compose.foundation.lazy.layout.IntervalList$Interval r7 = (androidx.compose.foundation.lazy.layout.IntervalList.Interval) r7
            java.lang.Object r0 = r7.getValue()
            androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent$Interval r0 = (androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval) r0
            r7.l r0 = r0.getKey()
            int r1 = r6.f16069i
            int r2 = r7.getStartIndex()
            int r1 = java.lang.Math.max(r1, r2)
            int r2 = r7.getStartIndex()
            int r3 = r7.getSize()
            int r3 = r3 + r2
            int r3 = r3 + (-1)
            int r2 = r6.f16070l
            int r2 = java.lang.Math.min(r2, r3)
            if (r1 > r2) goto L57
        L29:
            if (r0 == 0) goto L3b
            int r3 = r7.getStartIndex()
            int r3 = r1 - r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r3 = r0.invoke(r3)
            if (r3 != 0) goto L3f
        L3b:
            java.lang.Object r3 = androidx.compose.foundation.lazy.layout.Lazy_androidKt.getDefaultLazyLayoutKey(r1)
        L3f:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.util.HashMap r5 = r6.f16071m
            r5.put(r3, r4)
            i2.r r4 = r6.f16072n
            java.lang.Object[] r5 = r4.f16074b
            int r4 = r4.f16075c
            int r4 = r1 - r4
            r5[r4] = r3
            if (r1 == r2) goto L57
            int r1 = r1 + 1
            goto L29
        L57:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.q.invoke(java.lang.Object):java.lang.Object");
    }
}
