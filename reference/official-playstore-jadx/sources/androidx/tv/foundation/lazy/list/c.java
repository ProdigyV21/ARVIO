package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Modifier f5370i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m0 f5371l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5372m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f5373n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Arrangement.Horizontal f5374o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Alignment.Vertical f5375p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f5376q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ h2.b f5377r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.l f5378s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f5379t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f5380u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Modifier modifier, m0 m0Var, PaddingValues paddingValues, boolean z, Arrangement.Horizontal horizontal, Alignment.Vertical vertical, boolean z5, h2.b bVar, r7.l lVar, int i10, int i11) {
        super(2);
        this.f5370i = modifier;
        this.f5371l = m0Var;
        this.f5372m = paddingValues;
        this.f5373n = z;
        this.f5374o = horizontal;
        this.f5375p = vertical;
        this.f5376q = z5;
        this.f5377r = bVar;
        this.f5378s = lVar;
        this.f5379t = i10;
        this.f5380u = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        LazyDslKt.TvLazyRow(this.f5370i, this.f5371l, this.f5372m, this.f5373n, this.f5374o, this.f5375p, this.f5376q, this.f5377r, this.f5378s, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5379t | 1), this.f5380u);
        return t0.f22605a;
    }
}
