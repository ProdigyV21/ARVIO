package ua;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.l;
import ka.y2;
import pa.w;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements ka.k, y2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f22095i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d f22096l;

    public c(d dVar, l lVar) {
        this.f22096l = dVar;
        this.f22095i = lVar;
    }

    @Override // ka.k
    public final c2.a a(Object obj, r7.l lVar) {
        d dVar = this.f22096l;
        b bVar = new b(dVar, this, 1);
        c2.a aVarC = this.f22095i.C((t0) obj, bVar);
        if (aVarC != null) {
            d.f22097h.set(dVar, null);
        }
        return aVarC;
    }

    @Override // ka.k
    public final void c(r7.l lVar) {
        this.f22095i.c(lVar);
    }

    @Override // ka.k
    public final boolean cancel(Throwable th) {
        return this.f22095i.cancel(th);
    }

    @Override // ka.k
    public final void d(Object obj, r7.l lVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d.f22097h;
        d dVar = this.f22096l;
        atomicReferenceFieldUpdater.set(dVar, null);
        b bVar = new b(dVar, this, 0);
        this.f22095i.d((t0) obj, bVar);
    }

    @Override // ka.y2
    public final void e(w wVar, int i10) {
        this.f22095i.e(wVar, i10);
    }

    @Override // ka.k
    public final void f(Object obj) {
        this.f22095i.f(obj);
    }

    @Override // d7.d
    public final d7.j getContext() {
        return this.f22095i.f19609o;
    }

    @Override // ka.k
    public final boolean isActive() {
        return this.f22095i.isActive();
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        this.f22095i.resumeWith(obj);
    }
}
