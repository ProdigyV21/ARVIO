package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w1 implements z1, b4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f18034i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f18035l;

    public /* synthetic */ w1(Object obj, Object obj2) {
        this.f18034i = obj;
        this.f18035l = obj2;
    }

    @Override // io.sentry.b4
    public void b(m1 m1Var) {
        h7 h7Var = (h7) this.f18034i;
        a1 a1Var = (a1) this.f18035l;
        h7Var.getClass();
        if (m1Var == h7Var) {
            a1Var.C();
        }
    }

    @Override // io.sentry.z1
    public Object c() {
        f2 f2Var = (f2) this.f18034i;
        h2 h2Var = (h2) this.f18035l;
        f2Var.getClass();
        try {
            try {
                return Integer.valueOf(h2Var.nextInt());
            } catch (Exception unused) {
                return Long.valueOf(h2Var.nextLong());
            }
        } catch (Exception unused2) {
            return Double.valueOf(h2Var.nextDouble());
        }
    }
}
