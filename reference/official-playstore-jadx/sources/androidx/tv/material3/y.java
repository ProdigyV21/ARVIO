package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;

/* JADX INFO: loaded from: classes3.dex */
public final class y extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6535i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f6536l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6537m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l0 f6538n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f6539o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.l f6540p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6541q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6542r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(long j10, int i10, l0 l0Var, boolean z, r7.l lVar, int i11, int i12, int i13) {
        super(2);
        this.f6535i = i13;
        this.f6536l = j10;
        this.f6537m = i10;
        this.f6538n = l0Var;
        this.f6539o = z;
        this.f6540p = lVar;
        this.f6541q = i11;
        this.f6542r = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6535i) {
            case 0:
                ((Number) obj2).intValue();
                CarouselKt.AutoScrollSideEffect(this.f6536l, this.f6537m, this.f6538n, this.f6539o, this.f6540p, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6541q | 1), this.f6542r);
                break;
            default:
                ((Number) obj2).intValue();
                CarouselKt.AutoScrollSideEffect(this.f6536l, this.f6537m, this.f6538n, this.f6539o, this.f6540p, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6541q | 1), this.f6542r);
                break;
        }
        return x6.t0.f22605a;
    }
}
