package androidx.fragment.app;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ q.a f2860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AtomicReference f2861b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g.a f2862c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.activity.result.b f2863d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ c0 f2864e;

    public x(c0 c0Var, q.a aVar, AtomicReference atomicReference, g.a aVar2, androidx.activity.result.b bVar) {
        this.f2864e = c0Var;
        this.f2860a = aVar;
        this.f2861b = atomicReference;
        this.f2862c = aVar2;
        this.f2863d = bVar;
    }

    @Override // androidx.fragment.app.z
    public final void a() {
        c0 c0Var = this.f2864e;
        this.f2861b.set(((androidx.activity.result.k) this.f2860a.apply()).c(c0Var.generateActivityResultKey(), c0Var, this.f2862c, this.f2863d));
    }
}
