package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Modifier f5359i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m0 f5360l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5361m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5362n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Vertical f5363o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Alignment.Horizontal f5364p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f5365q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ h2.b f5366r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.l f5367s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f5368t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f5369u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Modifier modifier, m0 m0Var, PaddingValues paddingValues, boolean z, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, boolean z5, h2.b bVar, r7.l lVar, int i10, int i11) {
        super(2);
        this.f5359i = modifier;
        this.f5360l = m0Var;
        this.f5361m = paddingValues;
        this.f5362n = z;
        this.f5363o = vertical;
        this.f5364p = horizontal;
        this.f5365q = z5;
        this.f5366r = bVar;
        this.f5367s = lVar;
        this.f5368t = i10;
        this.f5369u = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        LazyDslKt.TvLazyColumn(this.f5359i, this.f5360l, this.f5361m, this.f5362n, this.f5363o, this.f5364p, this.f5365q, this.f5366r, this.f5367s, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5368t | 1), this.f5369u);
        return t0.f22605a;
    }
}
