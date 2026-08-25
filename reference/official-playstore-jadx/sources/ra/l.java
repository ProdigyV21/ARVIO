package ra;

import ka.f0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends f0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final l f21440i = new l();

    @Override // ka.f0
    public final void dispatch(d7.j jVar, Runnable runnable) {
        d dVar = d.f21426l;
        dVar.f21428i.k(runnable, k.f21439h, false);
    }

    @Override // ka.f0
    public final void dispatchYield(d7.j jVar, Runnable runnable) {
        d dVar = d.f21426l;
        dVar.f21428i.k(runnable, k.f21439h, true);
    }

    @Override // ka.f0
    public final f0 limitedParallelism(int i10) {
        pa.a.a(i10);
        return i10 >= k.f21435d ? this : super.limitedParallelism(i10);
    }
}
