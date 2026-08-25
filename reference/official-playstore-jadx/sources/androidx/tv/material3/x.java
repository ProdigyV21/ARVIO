package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ CarouselDefaults f6495i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f6496l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6497m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Modifier f6498n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ float f6499o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.q f6500p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6501q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6502r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(CarouselDefaults carouselDefaults, int i10, int i11, Modifier modifier, float f10, r7.q qVar, int i12, int i13) {
        super(2);
        this.f6495i = carouselDefaults;
        this.f6496l = i10;
        this.f6497m = i11;
        this.f6498n = modifier;
        this.f6499o = f10;
        this.f6500p = qVar;
        this.f6501q = i12;
        this.f6502r = i13;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        this.f6495i.m5981IndicatorRowhGBTI10(this.f6496l, this.f6497m, this.f6498n, this.f6499o, this.f6500p, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6501q | 1), this.f6502r);
        return x6.t0.f22605a;
    }
}
