package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Modifier f5453i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m0 f5454l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5455m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5456n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f5457o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f5458p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f5459q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ h2.b f5460r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Alignment.Horizontal f5461s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Vertical f5462t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Alignment.Vertical f5463u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Horizontal f5464v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ r7.l f5465w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f5466y;
    public final /* synthetic */ int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(Modifier modifier, m0 m0Var, PaddingValues paddingValues, boolean z, boolean z5, boolean z10, int i10, h2.b bVar, Alignment.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, Arrangement.Horizontal horizontal2, r7.l lVar, int i11, int i12, int i13) {
        super(2);
        this.f5453i = modifier;
        this.f5454l = m0Var;
        this.f5455m = paddingValues;
        this.f5456n = z;
        this.f5457o = z5;
        this.f5458p = z10;
        this.f5459q = i10;
        this.f5460r = bVar;
        this.f5461s = horizontal;
        this.f5462t = vertical;
        this.f5463u = vertical2;
        this.f5464v = horizontal2;
        this.f5465w = lVar;
        this.x = i11;
        this.f5466y = i12;
        this.z = i13;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.x | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.f5466y);
        int i10 = this.z;
        LazyListKt.LazyList(this.f5453i, this.f5454l, this.f5455m, this.f5456n, this.f5457o, this.f5458p, this.f5459q, this.f5460r, this.f5461s, this.f5462t, this.f5463u, this.f5464v, this.f5465w, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return t0.f22605a;
    }
}
