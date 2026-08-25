package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5237i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n0 f5238l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Horizontal f5239m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(PaddingValues paddingValues, n0 n0Var, Arrangement.Horizontal horizontal) {
        super(2);
        this.f5237i = paddingValues;
        this.f5238l = n0Var;
        this.f5239m = horizontal;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        Density density = (Density) obj;
        long value = ((Constraints) obj2).getValue();
        if (Constraints.m5646getMaxWidthimpl(value) == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("LazyVerticalGrid's width should be bound by parent.");
        }
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        PaddingValues paddingValues = this.f5237i;
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(value) - density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(PaddingKt.calculateEndPadding(paddingValues, layoutDirection) + PaddingKt.calculateStartPadding(paddingValues, layoutDirection)));
        Arrangement.Horizontal horizontal = this.f5239m;
        int[] iArrB1 = kotlin.collections.x.b1(this.f5238l.a(iM5646getMaxWidthimpl, density.mo279roundToPx0680j_4(horizontal.getSpacing())));
        int[] iArr = new int[iArrB1.length];
        horizontal.arrange(density, iM5646getMaxWidthimpl, iArrB1, layoutDirection, iArr);
        return new g0(iArrB1, iArr);
    }
}
