package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public class j0 extends h0 {
    @Override // androidx.lifecycle.h0
    public void i(Object obj) {
        h0.b("setValue");
        this.f3245g++;
        this.f3243e = obj;
        d(null);
    }

    public final void j(Object obj) {
        boolean z;
        synchronized (this.f3239a) {
            z = this.f3244f == h0.k;
            this.f3244f = obj;
        }
        if (z) {
            o.a.Z().a0(this.f3248j);
        }
    }
}
