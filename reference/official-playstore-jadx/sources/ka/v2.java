package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class v2 extends f0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final v2 f19649i = new v2();

    @Override // ka.f0
    public final void dispatch(d7.j jVar, Runnable runnable) {
        a3 a3Var = (a3) jVar.get(a3.f19550l);
        if (a3Var == null) {
            throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
        }
        a3Var.f19551i = true;
    }

    @Override // ka.f0
    public final boolean isDispatchNeeded(d7.j jVar) {
        return false;
    }

    @Override // ka.f0
    public final f0 limitedParallelism(int i10) {
        throw new UnsupportedOperationException("limitedParallelism is not supported for Dispatchers.Unconfined");
    }

    @Override // ka.f0
    public final String toString() {
        return "Dispatchers.Unconfined";
    }
}
