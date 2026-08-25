package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c[] f19569i;

    public d(c[] cVarArr) {
        this.f19569i = cVarArr;
    }

    @Override // ka.j
    public final void a(Throwable th) {
        b();
    }

    public final void b() {
        for (c cVar : this.f19569i) {
            z0 z0Var = cVar.f19561m;
            if (z0Var == null) {
                kotlin.jvm.internal.p.i("handle");
                throw null;
            }
            z0Var.dispose();
        }
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        b();
        return x6.t0.f22605a;
    }

    public final String toString() {
        return "DisposeHandlersOnCancel[" + this.f19569i + ']';
    }
}
