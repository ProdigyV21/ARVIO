package ob;

import io.ktor.network.sockets.DatagramKt;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;
import xb.h0;
import xb.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class o implements Closeable {
    public static final b0 K;
    public final b0 A;
    public b0 B;
    public long C;
    public long D;
    public long E;
    public long F;
    public final Socket G;
    public final y H;
    public final j8.h I;
    public final LinkedHashSet J;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g f20922i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final LinkedHashMap f20923l = new LinkedHashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f20924m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20925n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20926o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f20927p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final kb.e f20928q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final kb.b f20929r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final kb.b f20930s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final kb.b f20931t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.util.l f20932u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f20933v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f20934w;
    public long x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f20935y;
    public long z;

    static {
        b0 b0Var = new b0();
        b0Var.b(7, DatagramKt.MAX_DATAGRAM_SIZE);
        b0Var.b(5, 16384);
        K = b0Var;
    }

    public o(o5.a aVar) {
        this.f20922i = (g) aVar.f20781h;
        String str = aVar.f20775b;
        if (str == null) {
            kotlin.jvm.internal.p.i("connectionName");
            throw null;
        }
        this.f20924m = str;
        this.f20926o = 3;
        kb.e eVar = (kb.e) aVar.f20777d;
        this.f20928q = eVar;
        kb.b bVarE = eVar.e();
        this.f20929r = bVarE;
        this.f20930s = eVar.e();
        this.f20931t = eVar.e();
        this.f20932u = a0.f20874g;
        b0 b0Var = new b0();
        b0Var.b(7, 16777216);
        this.A = b0Var;
        this.B = K;
        this.F = r2.a();
        Socket socket = (Socket) aVar.f20778e;
        if (socket == null) {
            kotlin.jvm.internal.p.i("socket");
            throw null;
        }
        this.G = socket;
        h0 h0Var = (h0) aVar.f20780g;
        if (h0Var == null) {
            kotlin.jvm.internal.p.i("sink");
            throw null;
        }
        this.H = new y(h0Var);
        i0 i0Var = (i0) aVar.f20779f;
        if (i0Var == null) {
            kotlin.jvm.internal.p.i("source");
            throw null;
        }
        this.I = new j8.h(this, new t(i0Var), 2);
        this.J = new LinkedHashSet();
        int i10 = aVar.f20776c;
        if (i10 != 0) {
            long nanos = TimeUnit.MILLISECONDS.toNanos(i10);
            bVarE.c(new m(str.concat(" ping"), nanos, 0, this), nanos);
        }
    }

    public final void G(int i10, int i11) {
        this.f20929r.c(new i(this.f20924m + '[' + i10 + "] writeSynReset", this, i10, i11, 1), 0L);
    }

    public final void O(int i10, long j10) {
        this.f20929r.c(new n(this.f20924m + '[' + i10 + "] windowUpdate", this, i10, j10), 0L);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        i(1, 9, null);
    }

    public final void flush() {
        this.H.flush();
    }

    public final void i(int i10, int i11, IOException iOException) {
        int i12;
        Object[] array;
        byte[] bArr = ib.c.f16268a;
        try {
            o(i10);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (this.f20923l.isEmpty()) {
                array = null;
            } else {
                array = this.f20923l.values().toArray(new x[0]);
                this.f20923l.clear();
            }
        }
        x[] xVarArr = (x[]) array;
        if (xVarArr != null) {
            for (x xVar : xVarArr) {
                try {
                    xVar.c(iOException, i11);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.H.close();
        } catch (IOException unused3) {
        }
        try {
            this.G.close();
        } catch (IOException unused4) {
        }
        this.f20929r.f();
        this.f20930s.f();
        this.f20931t.f();
    }

    public final synchronized x j(int i10) {
        return (x) this.f20923l.get(Integer.valueOf(i10));
    }

    public final synchronized boolean k(long j10) {
        if (this.f20927p) {
            return false;
        }
        if (this.f20935y < this.x) {
            if (j10 >= this.z) {
                return false;
            }
        }
        return true;
    }

    public final synchronized x l(int i10) {
        x xVar;
        xVar = (x) this.f20923l.remove(Integer.valueOf(i10));
        notifyAll();
        return xVar;
    }

    public final void o(int i10) {
        synchronized (this.H) {
            synchronized (this) {
                if (this.f20927p) {
                    return;
                }
                this.f20927p = true;
                this.H.l(this.f20925n, i10, ib.c.f16268a);
            }
        }
    }

    public final synchronized void x(long j10) {
        long j11 = this.C + j10;
        this.C = j11;
        long j12 = j11 - this.D;
        if (j12 >= this.A.a() / 2) {
            O(0, j12);
            this.D += j12;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0035, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.H.f20981m);
        r6 = r2;
        r8.E += r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void y(int r9, boolean r10, xb.j r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            ob.y r12 = r8.H
            r12.j(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L68
            monitor-enter(r8)
        L12:
            long r4 = r8.E     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            long r6 = r8.F     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L34
            java.util.LinkedHashMap r2 = r8.f20923l     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            if (r2 == 0) goto L2c
            r8.wait()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            goto L12
        L2a:
            r9 = move-exception
            goto L66
        L2c:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            throw r9     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
        L34:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L2a
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L2a
            ob.y r4 = r8.H     // Catch: java.lang.Throwable -> L2a
            int r4 = r4.f20981m     // Catch: java.lang.Throwable -> L2a
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L2a
            long r4 = r8.E     // Catch: java.lang.Throwable -> L2a
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L2a
            long r4 = r4 + r6
            r8.E = r4     // Catch: java.lang.Throwable -> L2a
            monitor-exit(r8)
            long r12 = r12 - r6
            ob.y r4 = r8.H
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.j(r5, r9, r11, r2)
            goto Ld
        L59:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r9.interrupt()     // Catch: java.lang.Throwable -> L2a
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L2a
            r9.<init>()     // Catch: java.lang.Throwable -> L2a
            throw r9     // Catch: java.lang.Throwable -> L2a
        L66:
            monitor-exit(r8)
            throw r9
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ob.o.y(int, boolean, xb.j, long):void");
    }
}
