package oa;

/* JADX INFO: loaded from: classes5.dex */
public final class y implements d7.d, f7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d7.d f20863i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d7.j f20864l;

    public y(d7.j jVar, d7.d dVar) {
        this.f20863i = dVar;
        this.f20864l = jVar;
    }

    @Override // f7.d
    public final f7.d getCallerFrame() {
        d7.d dVar = this.f20863i;
        if (dVar instanceof f7.d) {
            return (f7.d) dVar;
        }
        return null;
    }

    @Override // d7.d
    public final d7.j getContext() {
        return this.f20864l;
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        this.f20863i.resumeWith(obj);
    }
}
