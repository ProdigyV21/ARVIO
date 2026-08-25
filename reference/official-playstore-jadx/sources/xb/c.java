package xb;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements m0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22717i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f22718l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22719m;

    public /* synthetic */ c(Object obj, Object obj2, int i10) {
        this.f22717i = i10;
        this.f22718l = obj;
        this.f22719m = obj2;
    }

    @Override // xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.f22717i) {
            case 0:
                n0 n0Var = (n0) this.f22718l;
                c cVar = (c) this.f22719m;
                n0Var.i();
                try {
                    cVar.close();
                    if (n0Var.j()) {
                        throw n0Var.l(null);
                    }
                    return;
                } catch (IOException e5) {
                    if (!n0Var.j()) {
                        throw e5;
                    }
                    throw n0Var.l(e5);
                } finally {
                    n0Var.j();
                }
            default:
                ((OutputStream) this.f22718l).close();
                return;
        }
    }

    @Override // xb.m0, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.f22717i) {
            case 0:
                n0 n0Var = (n0) this.f22718l;
                c cVar = (c) this.f22719m;
                n0Var.i();
                try {
                    cVar.flush();
                    if (n0Var.j()) {
                        throw n0Var.l(null);
                    }
                    return;
                } catch (IOException e5) {
                    if (!n0Var.j()) {
                        throw e5;
                    }
                    throw n0Var.l(e5);
                } finally {
                    n0Var.j();
                }
            default:
                ((OutputStream) this.f22718l).flush();
                return;
        }
    }

    @Override // xb.m0
    public final q0 timeout() {
        switch (this.f22717i) {
            case 0:
                return (n0) this.f22718l;
            default:
                return (q0) this.f22719m;
        }
    }

    public final String toString() {
        switch (this.f22717i) {
            case 0:
                return "AsyncTimeout.sink(" + ((c) this.f22719m) + ')';
            default:
                return "sink(" + ((OutputStream) this.f22718l) + ')';
        }
    }

    @Override // xb.m0
    public final void write(j jVar, long j10) throws IOException {
        switch (this.f22717i) {
            case 0:
                b.c(jVar.f22759l, 0L, j10);
                long j11 = j10;
                while (true) {
                    long j12 = 0;
                    if (j11 <= 0) {
                        return;
                    }
                    j0 j0Var = jVar.f22758i;
                    while (true) {
                        if (j12 < 65536) {
                            j12 += (long) (j0Var.f22762c - j0Var.f22761b);
                            if (j12 >= j11) {
                                j12 = j11;
                            } else {
                                j0Var = j0Var.f22765f;
                            }
                        }
                    }
                    n0 n0Var = (n0) this.f22718l;
                    c cVar = (c) this.f22719m;
                    n0Var.i();
                    try {
                        try {
                            cVar.write(jVar, j12);
                            if (n0Var.j()) {
                                throw n0Var.l(null);
                            }
                            j11 -= j12;
                        } catch (IOException e5) {
                            if (!n0Var.j()) {
                                throw e5;
                            }
                            throw n0Var.l(e5);
                        }
                    } catch (Throwable th) {
                        n0Var.j();
                        throw th;
                    }
                }
                break;
            default:
                b.c(jVar.f22759l, 0L, j10);
                while (j10 > 0) {
                    ((q0) this.f22719m).f();
                    j0 j0Var2 = jVar.f22758i;
                    int iMin = (int) Math.min(j10, j0Var2.f22762c - j0Var2.f22761b);
                    ((OutputStream) this.f22718l).write(j0Var2.f22760a, j0Var2.f22761b, iMin);
                    int i10 = j0Var2.f22761b + iMin;
                    j0Var2.f22761b = i10;
                    long j13 = iMin;
                    j10 -= j13;
                    jVar.f22759l -= j13;
                    if (i10 == j0Var2.f22762c) {
                        jVar.f22758i = j0Var2.a();
                        k0.a(j0Var2);
                    }
                }
                return;
        }
    }
}
