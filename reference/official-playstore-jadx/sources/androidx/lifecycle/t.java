package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class t implements w, ka.k0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f3296i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d7.j f3297l;

    public t(r rVar, d7.j jVar) {
        this.f3296i = rVar;
        this.f3297l = jVar;
        if (rVar.getCurrentState() == q.f3266i) {
            ka.m0.g(jVar, null);
        }
    }

    @Override // ka.k0
    public final d7.j getCoroutineContext() {
        return this.f3297l;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        r rVar = this.f3296i;
        if (rVar.getCurrentState().compareTo(q.f3266i) <= 0) {
            rVar.removeObserver(this);
            ka.m0.g(this.f3297l, null);
        }
    }
}
