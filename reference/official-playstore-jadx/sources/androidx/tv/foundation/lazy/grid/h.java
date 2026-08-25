package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5242i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n0 f5243l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Vertical f5244m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(PaddingValues paddingValues, n0 n0Var, Arrangement.Vertical vertical) {
        super(2);
        this.f5242i = paddingValues;
        this.f5243l = n0Var;
        this.f5244m = vertical;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        Density density = (Density) obj;
        long value = ((Constraints) obj2).getValue();
        if (Constraints.m5645getMaxHeightimpl(value) == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("LazyHorizontalGrid's height should be bound by parent.");
        }
        PaddingValues paddingValues = this.f5242i;
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(value) - density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(paddingValues.getBottom() + paddingValues.getTop()));
        Arrangement.Vertical vertical = this.f5244m;
        int[] iArrB1 = kotlin.collections.x.b1(this.f5243l.a(iM5645getMaxHeightimpl, density.mo279roundToPx0680j_4(vertical.getSpacing())));
        int[] iArr = new int[iArrB1.length];
        vertical.arrange(density, iM5645getMaxHeightimpl, iArrB1, iArr);
        return new g0(iArrB1, iArr);
    }
}
