package ob;

import java.io.IOException;
import java.util.ArrayDeque;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes5.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20965a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f20966b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f20967c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f20968d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f20969e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f20970f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayDeque f20971g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f20972h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v f20973i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final u f20974j;
    public final w k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final w f20975l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20976m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public IOException f20977n;

    public x(int i10, o oVar, boolean z, boolean z5, gb.a0 a0Var) {
        this.f20965a = i10;
        this.f20966b = oVar;
        this.f20970f = oVar.B.a();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f20971g = arrayDeque;
        this.f20973i = new v(this, oVar.A.a(), z5);
        this.f20974j = new u(this, z);
        this.k = new w(this);
        this.f20975l = new w(this);
        if (a0Var == null) {
            if (!h()) {
                throw new IllegalStateException("remotely-initiated streams should have headers");
            }
        } else {
            if (h()) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
            }
            arrayDeque.add(a0Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r2 = this;
            byte[] r0 = ib.c.f16268a
            monitor-enter(r2)
            ob.v r0 = r2.f20973i     // Catch: java.lang.Throwable -> L18
            boolean r1 = r0.f20959l     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L1c
            boolean r0 = r0.f20962o     // Catch: java.lang.Throwable -> L18
            if (r0 == 0) goto L1c
            ob.u r0 = r2.f20974j     // Catch: java.lang.Throwable -> L18
            boolean r1 = r0.f20954i     // Catch: java.lang.Throwable -> L18
            if (r1 != 0) goto L1a
            boolean r0 = r0.f20956m     // Catch: java.lang.Throwable -> L18
            if (r0 == 0) goto L1c
            goto L1a
        L18:
            r0 = move-exception
            goto L35
        L1a:
            r0 = 1
            goto L1d
        L1c:
            r0 = 0
        L1d:
            boolean r1 = r2.i()     // Catch: java.lang.Throwable -> L18
            monitor-exit(r2)
            if (r0 == 0) goto L2b
            r0 = 9
            r1 = 0
            r2.c(r1, r0)
            return
        L2b:
            if (r1 != 0) goto L34
            ob.o r0 = r2.f20966b
            int r1 = r2.f20965a
            r0.l(r1)
        L34:
            return
        L35:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ob.x.a():void");
    }

    public final void b() throws IOException {
        u uVar = this.f20974j;
        if (uVar.f20956m) {
            throw new IOException("stream closed");
        }
        if (uVar.f20954i) {
            throw new IOException("stream finished");
        }
        if (this.f20976m != 0) {
            IOException iOException = this.f20977n;
            if (iOException == null) {
                throw new StreamResetException(this.f20976m);
            }
        }
    }

    public final void c(IOException iOException, int i10) {
        if (d(iOException, i10)) {
            this.f20966b.H.y(this.f20965a, i10);
        }
    }

    public final boolean d(IOException iOException, int i10) {
        byte[] bArr = ib.c.f16268a;
        synchronized (this) {
            if (this.f20976m != 0) {
                return false;
            }
            this.f20976m = i10;
            this.f20977n = iOException;
            notifyAll();
            if (this.f20973i.f20959l) {
                if (this.f20974j.f20954i) {
                    return false;
                }
            }
            this.f20966b.l(this.f20965a);
            return true;
        }
    }

    public final void e(int i10) {
        if (d(null, i10)) {
            this.f20966b.G(this.f20965a, i10);
        }
    }

    public final synchronized int f() {
        return this.f20976m;
    }

    public final u g() {
        synchronized (this) {
            if (!this.f20972h && !h()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f20974j;
    }

    public final boolean h() {
        boolean z = (this.f20965a & 1) == 1;
        this.f20966b.getClass();
        return true == z;
    }

    public final synchronized boolean i() {
        try {
            if (this.f20976m != 0) {
                return false;
            }
            v vVar = this.f20973i;
            if (vVar.f20959l || vVar.f20962o) {
                u uVar = this.f20974j;
                if (uVar.f20954i || uVar.f20956m) {
                    if (this.f20972h) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void j(gb.a0 a0Var, boolean z) {
        boolean zI;
        byte[] bArr = ib.c.f16268a;
        synchronized (this) {
            try {
                if (this.f20972h && z) {
                    this.f20973i.getClass();
                } else {
                    this.f20972h = true;
                    this.f20971g.add(a0Var);
                }
                if (z) {
                    this.f20973i.f20959l = true;
                }
                zI = i();
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zI) {
            return;
        }
        this.f20966b.l(this.f20965a);
    }

    public final synchronized void k(int i10) {
        if (this.f20976m == 0) {
            this.f20976m = i10;
            notifyAll();
        }
    }
}
