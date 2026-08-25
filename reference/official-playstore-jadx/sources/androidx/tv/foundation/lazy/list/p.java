package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.unit.ConstraintsKt;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends kotlin.jvm.internal.r implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ LazyLayoutMeasureScope f5467i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f5468l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5469m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f5470n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j10, int i10, int i11) {
        super(3);
        this.f5467i = lazyLayoutMeasureScope;
        this.f5468l = j10;
        this.f5469m = i10;
        this.f5470n = i11;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        int iIntValue2 = ((Number) obj2).intValue();
        int i10 = iIntValue + this.f5469m;
        long j10 = this.f5468l;
        int iM5660constrainWidthK40F9xA = ConstraintsKt.m5660constrainWidthK40F9xA(j10, i10);
        int iM5659constrainHeightK40F9xA = ConstraintsKt.m5659constrainHeightK40F9xA(j10, iIntValue2 + this.f5470n);
        kotlin.collections.a0 a0Var = kotlin.collections.a0.f19683i;
        return this.f5467i.layout(iM5660constrainWidthK40F9xA, iM5659constrainHeightK40F9xA, a0Var, (r7.l) obj3);
    }
}
