package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Modifier f5292i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b1 f5293l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f5294m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5295n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f5296o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f5297p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f5298q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Vertical f5299r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Horizontal f5300s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ h2.b f5301t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ r7.l f5302u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f5303v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f5304w;
    public final /* synthetic */ int x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(Modifier modifier, b1 b1Var, r7.p pVar, PaddingValues paddingValues, boolean z, boolean z5, boolean z10, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, h2.b bVar, r7.l lVar, int i10, int i11, int i12) {
        super(2);
        this.f5292i = modifier;
        this.f5293l = b1Var;
        this.f5294m = pVar;
        this.f5295n = paddingValues;
        this.f5296o = z;
        this.f5297p = z5;
        this.f5298q = z10;
        this.f5299r = vertical;
        this.f5300s = horizontal;
        this.f5301t = bVar;
        this.f5302u = lVar;
        this.f5303v = i10;
        this.f5304w = i11;
        this.x = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f5303v | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.f5304w);
        int i10 = this.x;
        LazyGridKt.LazyGrid(this.f5292i, this.f5293l, this.f5294m, this.f5295n, this.f5296o, this.f5297p, this.f5298q, this.f5299r, this.f5300s, this.f5301t, this.f5302u, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
