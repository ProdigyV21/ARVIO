package oa;

import d7.j;

/* JADX INFO: loaded from: classes5.dex */
public final class n implements d7.j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Throwable f20849i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d7.j f20850l;

    public n(d7.j jVar, Throwable th) {
        this.f20849i = th;
        this.f20850l = jVar;
    }

    @Override // d7.j
    public final Object fold(Object obj, r7.p pVar) {
        return this.f20850l.fold(obj, pVar);
    }

    @Override // d7.j
    public final j.a get(j.b bVar) {
        return this.f20850l.get(bVar);
    }

    @Override // d7.j
    public final d7.j minusKey(j.b bVar) {
        return this.f20850l.minusKey(bVar);
    }

    @Override // d7.j
    public final d7.j plus(d7.j jVar) {
        return this.f20850l.plus(jVar);
    }
}
