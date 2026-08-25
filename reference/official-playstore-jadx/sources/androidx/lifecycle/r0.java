package androidx.lifecycle;

import java.util.concurrent.CancellationException;
import ka.v1;

/* JADX INFO: loaded from: classes3.dex */
public final class r0 implements w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ p f3278i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ kotlin.jvm.internal.k0 f3279l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ka.k0 f3280m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f3281n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ ka.l f3282o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ ua.d f3283p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ androidx.work.impl.constraints.l f3284q;

    public r0(p pVar, kotlin.jvm.internal.k0 k0Var, ka.k0 k0Var2, p pVar2, ka.l lVar, ua.d dVar, androidx.work.impl.constraints.l lVar2) {
        this.f3278i = pVar;
        this.f3279l = k0Var;
        this.f3280m = k0Var2;
        this.f3281n = pVar2;
        this.f3282o = lVar;
        this.f3283p = dVar;
        this.f3284q = lVar2;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        p pVar2 = this.f3278i;
        kotlin.jvm.internal.k0 k0Var = this.f3279l;
        if (pVar == pVar2) {
            k0Var.f19746i = ka.m0.p(this.f3280m, null, 0, new q0(this.f3283p, this.f3284q, null), 3);
            return;
        }
        if (pVar == this.f3281n) {
            v1 v1Var = (v1) k0Var.f19746i;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            k0Var.f19746i = null;
        }
        if (pVar == p.ON_DESTROY) {
            this.f3282o.resumeWith(x6.t0.f22605a);
        }
    }
}
