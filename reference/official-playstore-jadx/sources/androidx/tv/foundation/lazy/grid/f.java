package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class f extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n0 f5219i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f5220l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b1 f5221m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5222n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f5223o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Vertical f5224p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Horizontal f5225q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f5226r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ h2.b f5227s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ r7.l f5228t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f5229u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f5230v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(int i10, int i11, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, PaddingValues paddingValues, Modifier modifier, n0 n0Var, b1 b1Var, h2.b bVar, r7.l lVar, boolean z, boolean z5) {
        super(2);
        this.f5219i = n0Var;
        this.f5220l = modifier;
        this.f5221m = b1Var;
        this.f5222n = paddingValues;
        this.f5223o = z;
        this.f5224p = vertical;
        this.f5225q = horizontal;
        this.f5226r = z5;
        this.f5227s = bVar;
        this.f5228t = lVar;
        this.f5229u = i10;
        this.f5230v = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        LazyGridDslKt.TvLazyVerticalGrid(this.f5219i, this.f5220l, this.f5221m, this.f5222n, this.f5223o, this.f5224p, this.f5225q, this.f5226r, this.f5227s, this.f5228t, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5229u | 1), this.f5230v);
        return x6.t0.f22605a;
    }
}
