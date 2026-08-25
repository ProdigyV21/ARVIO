package nb;

import androidx.appcompat.widget.u;
import xb.j;
import xb.k;
import xb.m0;
import xb.q0;
import xb.w;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements m0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w f20701i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f20702l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f20703m;

    public b(u uVar) {
        this.f20703m = uVar;
        this.f20701i = new w(((k) uVar.f1766e).timeout());
    }

    @Override // xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.f20702l) {
            return;
        }
        this.f20702l = true;
        ((k) this.f20703m.f1766e).H("0\r\n\r\n");
        w wVar = this.f20701i;
        q0 q0Var = wVar.f22799e;
        wVar.f22799e = q0.f22791d;
        q0Var.a();
        q0Var.b();
        this.f20703m.f1762a = 3;
    }

    @Override // xb.m0, java.io.Flushable
    public final synchronized void flush() {
        if (this.f20702l) {
            return;
        }
        ((k) this.f20703m.f1766e).flush();
    }

    @Override // xb.m0
    public final q0 timeout() {
        return this.f20701i;
    }

    @Override // xb.m0
    public final void write(j jVar, long j10) {
        k kVar = (k) this.f20703m.f1766e;
        if (this.f20702l) {
            throw new IllegalStateException("closed");
        }
        if (j10 == 0) {
            return;
        }
        kVar.v0(j10);
        kVar.H("\r\n");
        kVar.write(jVar, j10);
        kVar.H("\r\n");
    }
}
