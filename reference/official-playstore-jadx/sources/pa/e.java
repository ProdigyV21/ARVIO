package pa;

import ka.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements k0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d7.j f21262i;

    public e(d7.j jVar) {
        this.f21262i = jVar;
    }

    @Override // ka.k0
    public final d7.j getCoroutineContext() {
        return this.f21262i;
    }

    public final String toString() {
        return "CoroutineScope(coroutineContext=" + this.f21262i + ')';
    }
}
