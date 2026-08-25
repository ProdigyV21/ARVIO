package androidx.tv.material3;

import androidx.compose.animation.ContentTransform;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class f0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5955i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f5956l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l0 f5957m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f5958n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ ContentTransform f5959o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ ContentTransform f5960p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.q f5961q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.r f5962r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f5963s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f5964t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f0(int i10, Modifier modifier, l0 l0Var, long j10, ContentTransform contentTransform, ContentTransform contentTransform2, r7.q qVar, r7.r rVar, int i11, int i12) {
        super(2);
        this.f5955i = i10;
        this.f5956l = modifier;
        this.f5957m = l0Var;
        this.f5958n = j10;
        this.f5959o = contentTransform;
        this.f5960p = contentTransform2;
        this.f5961q = qVar;
        this.f5962r = rVar;
        this.f5963s = i11;
        this.f5964t = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        CarouselKt.Carousel(this.f5955i, this.f5956l, this.f5957m, this.f5958n, this.f5959o, this.f5960p, this.f5961q, this.f5962r, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f5963s | 1), this.f5964t);
        return x6.t0.f22605a;
    }
}
