package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends kotlin.jvm.internal.r implements r7.p {
    public final /* synthetic */ int A;
    public final /* synthetic */ int B;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6395i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f6396l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.q f6397m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f6398n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Modifier f6399o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f6400p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.p f6401q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.p f6402r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ w f6403s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ n f6404t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ v f6405u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ m f6406v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ r f6407w;
    public final /* synthetic */ Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6408y;
    public final /* synthetic */ int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(r7.a aVar, r7.q qVar, r7.p pVar, Modifier modifier, r7.a aVar2, r7.p pVar2, r7.p pVar3, w wVar, n nVar, v vVar, m mVar, r rVar, Object obj, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12, int i13) {
        super(2);
        this.f6395i = i13;
        this.f6396l = aVar;
        this.f6397m = qVar;
        this.f6398n = pVar;
        this.f6399o = modifier;
        this.f6400p = aVar2;
        this.f6401q = pVar2;
        this.f6402r = pVar3;
        this.f6403s = wVar;
        this.f6404t = nVar;
        this.f6405u = vVar;
        this.f6406v = mVar;
        this.f6407w = rVar;
        this.x = obj;
        this.f6408y = mutableInteractionSource;
        this.z = i10;
        this.A = i11;
        this.B = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        Composer composer = (Composer) obj;
        switch (this.f6395i) {
            case 0:
                ((Number) obj2).intValue();
                CardKt.ClassicCard(this.f6396l, this.f6397m, this.f6398n, this.f6399o, this.f6400p, this.f6401q, this.f6402r, this.f6403s, this.f6404t, this.f6405u, this.f6406v, this.f6407w, (PaddingValues) this.x, this.f6408y, composer, RecomposeScopeImplKt.updateChangedFlags(this.z | 1), RecomposeScopeImplKt.updateChangedFlags(this.A), this.B);
                break;
            case 1:
                ((Number) obj2).intValue();
                CardKt.WideClassicCard(this.f6396l, this.f6397m, this.f6398n, this.f6399o, this.f6400p, this.f6401q, this.f6402r, this.f6403s, this.f6404t, this.f6405u, this.f6406v, this.f6407w, (PaddingValues) this.x, this.f6408y, composer, RecomposeScopeImplKt.updateChangedFlags(this.z | 1), RecomposeScopeImplKt.updateChangedFlags(this.A), this.B);
                break;
            default:
                ((Number) obj2).intValue();
                CardKt.CompactCard(this.f6396l, this.f6397m, this.f6398n, this.f6399o, this.f6400p, this.f6401q, this.f6402r, this.f6403s, this.f6404t, this.f6405u, this.f6406v, this.f6407w, (Brush) this.x, this.f6408y, composer, RecomposeScopeImplKt.updateChangedFlags(this.z | 1), RecomposeScopeImplKt.updateChangedFlags(this.A), this.B);
                break;
        }
        return x6.t0.f22605a;
    }
}
