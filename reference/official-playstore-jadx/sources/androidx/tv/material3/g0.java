package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;

/* JADX INFO: loaded from: classes3.dex */
public final class g0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ l0 f5997i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5998l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5999m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(l0 l0Var, int i10, int i11) {
        super(2);
        this.f5997i = l0Var;
        this.f5998l = i10;
        this.f5999m = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f5999m | 1);
        CarouselKt.CarouselStateUpdater(this.f5997i, this.f5998l, (Composer) obj, iUpdateChangedFlags);
        return x6.t0.f22605a;
    }
}
