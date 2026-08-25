package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;

/* JADX INFO: loaded from: classes3.dex */
public final class g2 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ NavigationDrawerItemDefaults f6005i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f6006l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f6007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f6008n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f6009o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6010p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g2(NavigationDrawerItemDefaults navigationDrawerItemDefaults, String str, long j10, long j11, int i10, int i11) {
        super(2);
        this.f6005i = navigationDrawerItemDefaults;
        this.f6006l = str;
        this.f6007m = j10;
        this.f6008n = j11;
        this.f6009o = i10;
        this.f6010p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        this.f6005i.m5998TrailingBadgeRIQooxk(this.f6006l, this.f6007m, this.f6008n, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6009o | 1), this.f6010p);
        return x6.t0.f22605a;
    }
}
