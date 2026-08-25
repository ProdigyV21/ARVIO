package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class k3 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Modifier f6159i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6160l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6161m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Shape f6162n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ long f6163o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f6164p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f6165q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ d f6166r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ s1 f6167s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ float f6168t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6169u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ r7.q f6170v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f6171w;
    public final /* synthetic */ int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f6172y;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k3(Modifier modifier, boolean z, boolean z5, Shape shape, long j10, long j11, float f10, d dVar, s1 s1Var, float f11, MutableInteractionSource mutableInteractionSource, r7.q qVar, int i10, int i11, int i12) {
        super(2);
        this.f6159i = modifier;
        this.f6160l = z;
        this.f6161m = z5;
        this.f6162n = shape;
        this.f6163o = j10;
        this.f6164p = j11;
        this.f6165q = f10;
        this.f6166r = dVar;
        this.f6167s = s1Var;
        this.f6168t = f11;
        this.f6169u = mutableInteractionSource;
        this.f6170v = qVar;
        this.f6171w = i10;
        this.x = i11;
        this.f6172y = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iUpdateChangedFlags = RecomposeScopeImplKt.updateChangedFlags(this.f6171w | 1);
        int iUpdateChangedFlags2 = RecomposeScopeImplKt.updateChangedFlags(this.x);
        int i10 = this.f6172y;
        SurfaceKt.m6010SurfaceImplvf0GYmI(this.f6159i, this.f6160l, this.f6161m, this.f6162n, this.f6163o, this.f6164p, this.f6165q, this.f6166r, this.f6167s, this.f6168t, this.f6169u, this.f6170v, (Composer) obj, iUpdateChangedFlags, iUpdateChangedFlags2, i10);
        return x6.t0.f22605a;
    }
}
