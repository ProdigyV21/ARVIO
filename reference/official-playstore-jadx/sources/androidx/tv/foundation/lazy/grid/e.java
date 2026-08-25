package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n0 f5201i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f5202l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b1 f5203m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5204n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f5205o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Horizontal f5206p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Vertical f5207q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f5208r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ h2.b f5209s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ r7.l f5210t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f5211u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f5212v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(int i10, int i11, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, PaddingValues paddingValues, Modifier modifier, n0 n0Var, b1 b1Var, h2.b bVar, r7.l lVar, boolean z, boolean z5) {
        super(2);
        this.f5201i = n0Var;
        this.f5202l = modifier;
        this.f5203m = b1Var;
        this.f5204n = paddingValues;
        this.f5205o = z;
        this.f5206p = horizontal;
        this.f5207q = vertical;
        this.f5208r = z5;
        this.f5209s = bVar;
        this.f5210t = lVar;
        this.f5211u = i10;
        this.f5212v = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        LazyGridDslKt.TvLazyHorizontalGrid(this.f5201i, this.f5202l, this.f5203m, this.f5204n, this.f5205o, this.f5206p, this.f5207q, this.f5208r, this.f5209s, this.f5210t, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5211u | 1), this.f5212v);
        return x6.t0.f22605a;
    }
}
