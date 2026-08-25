package gb;

import io.sentry.p7;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends xb.u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ g f15651i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ androidx.tv.foundation.lazy.grid.f0 f15652l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, androidx.tv.foundation.lazy.grid.f0 f0Var, xb.m0 m0Var) {
        super(m0Var);
        this.f15651i = gVar;
        this.f15652l = f0Var;
    }

    @Override // xb.u, xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        g gVar = this.f15651i;
        androidx.tv.foundation.lazy.grid.f0 f0Var = this.f15652l;
        synchronized (gVar) {
            if (f0Var.f5234d) {
                return;
            }
            f0Var.f5234d = true;
            super.close();
            ((p7) this.f15652l.f5232b).b();
        }
    }
}
