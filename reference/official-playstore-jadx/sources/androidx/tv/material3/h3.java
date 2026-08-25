package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class h3 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.a f6052i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f6053l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f6054m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6055n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ float f6056o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g1 f6057p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ d1 f6058q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ f1 f6059r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ c1 f6060s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ e1 f6061t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6062u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ r7.q f6063v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f6064w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f6065y;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h3(r7.a aVar, Modifier modifier, r7.a aVar2, boolean z, float f10, g1 g1Var, d1 d1Var, f1 f1Var, c1 c1Var, e1 e1Var, MutableInteractionSource mutableInteractionSource, r7.q qVar, int i10, int i11, int i12) {
        super(2);
        this.f6052i = aVar;
        this.f6053l = modifier;
        this.f6054m = aVar2;
        this.f6055n = z;
        this.f6056o = f10;
        this.f6057p = g1Var;
        this.f6058q = d1Var;
        this.f6059r = f1Var;
        this.f6060s = c1Var;
        this.f6061t = e1Var;
        this.f6062u = mutableInteractionSource;
        this.f6063v = qVar;
        this.f6064w = i10;
        this.x = i11;
        this.f6065y = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f6064w | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.x);
        int i10 = this.f6065y;
        SurfaceKt.m6007Surface05tvjtU(this.f6052i, this.f6053l, this.f6054m, this.f6055n, this.f6056o, this.f6057p, this.f6058q, this.f6059r, this.f6060s, this.f6061t, this.f6062u, this.f6063v, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
