package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class u4 extends kotlin.jvm.internal.r implements r7.p {
    public final /* synthetic */ int A;
    public final /* synthetic */ int B;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.a f6451i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6452l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ j f6453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f6454n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ k f6455o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r4 f6456p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f6457q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ e f6458r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f6459s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6460t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ r7.p f6461u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Modifier f6462v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ r7.a f6463w;
    public final /* synthetic */ float x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ r7.q f6464y;
    public final /* synthetic */ int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u4(r7.a aVar, boolean z, j jVar, g gVar, k kVar, r4 r4Var, float f10, e eVar, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, r7.p pVar, Modifier modifier, r7.a aVar2, float f11, r7.q qVar, int i10, int i11, int i12) {
        super(2);
        this.f6451i = aVar;
        this.f6452l = z;
        this.f6453m = jVar;
        this.f6454n = gVar;
        this.f6455o = kVar;
        this.f6456p = r4Var;
        this.f6457q = f10;
        this.f6458r = eVar;
        this.f6459s = paddingValues;
        this.f6460t = mutableInteractionSource;
        this.f6461u = pVar;
        this.f6462v = modifier;
        this.f6463w = aVar2;
        this.x = f11;
        this.f6464y = qVar;
        this.z = i10;
        this.A = i11;
        this.B = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.z | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.A);
        int i10 = this.B;
        WideButtonKt.m6025WideButtonImplroJ8YiU(this.f6451i, this.f6452l, this.f6453m, this.f6454n, this.f6455o, this.f6456p, this.f6457q, this.f6458r, this.f6459s, this.f6460t, this.f6461u, this.f6462v, this.f6463w, this.x, this.f6464y, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
