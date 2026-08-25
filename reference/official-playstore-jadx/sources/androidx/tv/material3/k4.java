package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class k4 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6173i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f6174l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f6175m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f6176n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.p f6177o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.r f6178p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.q f6179q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6180r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f6181s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k4(int i10, Modifier modifier, long j10, long j11, r7.p pVar, r7.r rVar, r7.q qVar, int i11, int i12) {
        super(2);
        this.f6173i = i10;
        this.f6174l = modifier;
        this.f6175m = j10;
        this.f6176n = j11;
        this.f6177o = pVar;
        this.f6178p = rVar;
        this.f6179q = qVar;
        this.f6180r = i11;
        this.f6181s = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        TabRowKt.m6019TabRowpAZo6Ak(this.f6173i, this.f6174l, this.f6175m, this.f6176n, this.f6177o, this.f6178p, this.f6179q, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6180r | 1), this.f6181s);
        return x6.t0.f22605a;
    }
}
