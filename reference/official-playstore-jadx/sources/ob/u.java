package ob;

import java.io.InterruptedIOException;
import xb.m0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class u implements m0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f20954i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final xb.j f20955l = new xb.j();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f20956m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x f20957n;

    public u(x xVar, boolean z) {
        this.f20957n = xVar;
        this.f20954i = z;
    }

    @Override // xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        x xVar = this.f20957n;
        byte[] bArr = ib.c.f16268a;
        synchronized (xVar) {
            if (this.f20956m) {
                return;
            }
            boolean z = xVar.f() == 0;
            x xVar2 = this.f20957n;
            if (!xVar2.f20974j.f20954i) {
                if (this.f20955l.f22759l > 0) {
                    while (this.f20955l.f22759l > 0) {
                        i(true);
                    }
                } else if (z) {
                    xVar2.f20966b.y(xVar2.f20965a, true, null, 0L);
                }
            }
            synchronized (this.f20957n) {
                this.f20956m = true;
            }
            this.f20957n.f20966b.flush();
            this.f20957n.a();
        }
    }

    @Override // xb.m0, java.io.Flushable
    public final void flush() {
        x xVar = this.f20957n;
        byte[] bArr = ib.c.f16268a;
        synchronized (xVar) {
            xVar.b();
        }
        while (this.f20955l.f22759l > 0) {
            i(false);
            this.f20957n.f20966b.flush();
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void i(boolean z) {
        long jMin;
        boolean z5;
        x xVar = this.f20957n;
        synchronized (xVar) {
            xVar.f20975l.i();
            while (xVar.f20969e >= xVar.f20970f && !this.f20954i && !this.f20956m && xVar.f() == 0) {
                try {
                    try {
                        xVar.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                } catch (Throwable th) {
                    xVar.f20975l.l();
                    throw th;
                }
            }
            xVar.f20975l.l();
            xVar.b();
            jMin = Math.min(xVar.f20970f - xVar.f20969e, this.f20955l.f22759l);
            xVar.f20969e += jMin;
            z5 = z && jMin == this.f20955l.f22759l;
        }
        this.f20957n.f20975l.i();
        try {
            x xVar2 = this.f20957n;
            xVar2.f20966b.y(xVar2.f20965a, z5, this.f20955l, jMin);
        } finally {
            this.f20957n.f20975l.l();
        }
    }

    @Override // xb.m0
    public final q0 timeout() {
        return this.f20957n.f20975l;
    }

    @Override // xb.m0
    public final void write(xb.j jVar, long j10) {
        byte[] bArr = ib.c.f16268a;
        xb.j jVar2 = this.f20955l;
        jVar2.write(jVar, j10);
        while (jVar2.f22759l >= 16384) {
            i(false);
        }
    }
}
