package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;

/* JADX INFO: loaded from: classes3.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f5521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LazyLayoutMeasureScope f5522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5523c;

    public y(long j10, boolean z, l lVar, LazyLayoutMeasureScope lazyLayoutMeasureScope) {
        this.f5521a = lVar;
        this.f5522b = lazyLayoutMeasureScope;
        this.f5523c = ConstraintsKt.Constraints$default(0, z ? Constraints.m5646getMaxWidthimpl(j10) : Integer.MAX_VALUE, 0, z ? Integer.MAX_VALUE : Constraints.m5645getMaxHeightimpl(j10), 5, null);
    }

    public final x a(int i10) {
        l lVar = this.f5521a;
        Object key = lVar.getKey(i10);
        Object contentType = lVar.getContentType(i10);
        q qVar = (q) this;
        return new x(i10, this.f5522b.mo691measure0kLqBqw(i10, this.f5523c), qVar.f5471d, qVar.f5475h, qVar.f5476i, qVar.f5472e.getLayoutDirection(), qVar.f5477j, qVar.k, qVar.f5478l, i10 == qVar.f5473f + (-1) ? 0 : qVar.f5474g, qVar.f5479m, key, contentType);
    }
}
