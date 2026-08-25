package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class f4 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ l4 f5983i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f5984l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f5985m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Modifier f5986n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f5987o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f5988p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ c4 f5989q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f5990r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.q f5991s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f5992t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f5993u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f4(l4 l4Var, boolean z, r7.a aVar, Modifier modifier, r7.a aVar2, boolean z5, c4 c4Var, MutableInteractionSource mutableInteractionSource, r7.q qVar, int i10, int i11) {
        super(2);
        this.f5983i = l4Var;
        this.f5984l = z;
        this.f5985m = aVar;
        this.f5986n = modifier;
        this.f5987o = aVar2;
        this.f5988p = z5;
        this.f5989q = c4Var;
        this.f5990r = mutableInteractionSource;
        this.f5991s = qVar;
        this.f5992t = i10;
        this.f5993u = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        TabKt.Tab(this.f5983i, this.f5984l, this.f5985m, this.f5986n, this.f5987o, this.f5988p, this.f5989q, this.f5990r, this.f5991s, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5992t | 1), this.f5993u);
        return x6.t0.f22605a;
    }
}
