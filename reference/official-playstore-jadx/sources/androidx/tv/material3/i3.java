package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class i3 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f6099i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f6100l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f6101m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6102n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f6103o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f6104p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ b3 f6105q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ y2 f6106r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ a3 f6107s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ x2 f6108t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ z2 f6109u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6110v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ r7.q f6111w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f6112y;
    public final /* synthetic */ int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i3(boolean z, r7.a aVar, Modifier modifier, boolean z5, r7.a aVar2, float f10, b3 b3Var, y2 y2Var, a3 a3Var, x2 x2Var, z2 z2Var, MutableInteractionSource mutableInteractionSource, r7.q qVar, int i10, int i11, int i12) {
        super(2);
        this.f6099i = z;
        this.f6100l = aVar;
        this.f6101m = modifier;
        this.f6102n = z5;
        this.f6103o = aVar2;
        this.f6104p = f10;
        this.f6105q = b3Var;
        this.f6106r = y2Var;
        this.f6107s = a3Var;
        this.f6108t = x2Var;
        this.f6109u = z2Var;
        this.f6110v = mutableInteractionSource;
        this.f6111w = qVar;
        this.x = i10;
        this.f6112y = i11;
        this.z = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.x | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.f6112y);
        int i10 = this.z;
        SurfaceKt.m6009SurfacexYaah8o(this.f6099i, this.f6100l, this.f6101m, this.f6102n, this.f6103o, this.f6104p, this.f6105q, this.f6106r, this.f6107s, this.f6108t, this.f6109u, this.f6110v, this.f6111w, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
