package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.a f6075i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f6076l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f6077m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6078n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ j f6079o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g f6080p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ k f6081q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ f f6082r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ float f6083s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ e f6084t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f6085u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6086v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ r7.q f6087w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f6088y;
    public final /* synthetic */ int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(r7.a aVar, Modifier modifier, r7.a aVar2, boolean z, j jVar, g gVar, k kVar, f fVar, float f10, e eVar, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, r7.q qVar, int i10, int i11, int i12) {
        super(2);
        this.f6075i = aVar;
        this.f6076l = modifier;
        this.f6077m = aVar2;
        this.f6078n = z;
        this.f6079o = jVar;
        this.f6080p = gVar;
        this.f6081q = kVar;
        this.f6082r = fVar;
        this.f6083s = f10;
        this.f6084t = eVar;
        this.f6085u = paddingValues;
        this.f6086v = mutableInteractionSource;
        this.f6087w = qVar;
        this.x = i10;
        this.f6088y = i11;
        this.z = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.x | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.f6088y);
        int i10 = this.z;
        ButtonKt.m5975ButtonImplTCVpFMg(this.f6075i, this.f6076l, this.f6077m, this.f6078n, this.f6079o, this.f6080p, this.f6081q, this.f6082r, this.f6083s, this.f6084t, this.f6085u, this.f6086v, this.f6087w, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
