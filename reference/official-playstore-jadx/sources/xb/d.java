package xb;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22720i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f22721l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22722m;

    public /* synthetic */ d(Object obj, Object obj2, int i10) {
        this.f22720i = i10;
        this.f22721l = obj;
        this.f22722m = obj2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.f22720i) {
            case 0:
                n0 n0Var = (n0) this.f22721l;
                d dVar = (d) this.f22722m;
                n0Var.i();
                try {
                    dVar.close();
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
                ((InputStream) this.f22721l).close();
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
    @Override // xb.o0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long read(xb.j r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.f22720i
            java.lang.Object r1 = r5.f22721l
            java.lang.Object r2 = r5.f22722m
            switch(r0) {
                case 0: goto L88;
                default: goto L9;
            }
        L9:
            r3 = 0
            int r0 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r0 != 0) goto L10
            goto L53
        L10:
            if (r0 < 0) goto L78
            r0 = 1
            xb.q0 r2 = (xb.q0) r2     // Catch: java.lang.AssertionError -> L43
            r2.f()     // Catch: java.lang.AssertionError -> L43
            xb.j0 r2 = r6.D0(r0)     // Catch: java.lang.AssertionError -> L43
            int r3 = r2.f22762c     // Catch: java.lang.AssertionError -> L43
            int r3 = 8192 - r3
            long r3 = (long) r3     // Catch: java.lang.AssertionError -> L43
            long r7 = java.lang.Math.min(r7, r3)     // Catch: java.lang.AssertionError -> L43
            int r7 = (int) r7     // Catch: java.lang.AssertionError -> L43
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch: java.lang.AssertionError -> L43
            byte[] r8 = r2.f22760a     // Catch: java.lang.AssertionError -> L43
            int r3 = r2.f22762c     // Catch: java.lang.AssertionError -> L43
            int r7 = r1.read(r8, r3, r7)     // Catch: java.lang.AssertionError -> L43
            r8 = -1
            if (r7 != r8) goto L48
            int r7 = r2.f22761b     // Catch: java.lang.AssertionError -> L43
            int r8 = r2.f22762c     // Catch: java.lang.AssertionError -> L43
            if (r7 != r8) goto L45
            xb.j0 r7 = r2.a()     // Catch: java.lang.AssertionError -> L43
            r6.f22758i = r7     // Catch: java.lang.AssertionError -> L43
            xb.k0.a(r2)     // Catch: java.lang.AssertionError -> L43
            goto L45
        L43:
            r6 = move-exception
            goto L54
        L45:
            r3 = -1
            goto L53
        L48:
            int r8 = r2.f22762c     // Catch: java.lang.AssertionError -> L43
            int r8 = r8 + r7
            r2.f22762c = r8     // Catch: java.lang.AssertionError -> L43
            long r1 = r6.f22759l     // Catch: java.lang.AssertionError -> L43
            long r3 = (long) r7     // Catch: java.lang.AssertionError -> L43
            long r1 = r1 + r3
            r6.f22759l = r1     // Catch: java.lang.AssertionError -> L43
        L53:
            return r3
        L54:
            java.util.logging.Logger r7 = xb.d0.f22723a
            java.lang.Throwable r7 = r6.getCause()
            r8 = 0
            if (r7 == 0) goto L6e
            java.lang.String r7 = r6.getMessage()
            if (r7 == 0) goto L6a
            java.lang.String r1 = "getsockname failed"
            boolean r7 = kotlin.text.o.T(r7, r1, r8)
            goto L6b
        L6a:
            r7 = r8
        L6b:
            if (r7 == 0) goto L6e
            goto L6f
        L6e:
            r0 = r8
        L6f:
            if (r0 == 0) goto L77
            java.io.IOException r7 = new java.io.IOException
            r7.<init>(r6)
            throw r7
        L77:
            throw r6
        L78:
            java.lang.String r6 = "byteCount < 0: "
            java.lang.String r6 = androidx.compose.foundation.c.q(r7, r6)
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L88:
            xb.n0 r1 = (xb.n0) r1
            xb.d r2 = (xb.d) r2
            r1.i()
            long r6 = r2.read(r6, r7)     // Catch: java.lang.Throwable -> La0 java.io.IOException -> La2
            boolean r8 = r1.j()
            if (r8 != 0) goto L9a
            return r6
        L9a:
            r6 = 0
            java.io.IOException r6 = r1.l(r6)
            throw r6
        La0:
            r6 = move-exception
            goto Laf
        La2:
            r6 = move-exception
            boolean r7 = r1.j()     // Catch: java.lang.Throwable -> La0
            if (r7 != 0) goto Laa
            goto Lae
        Laa:
            java.io.IOException r6 = r1.l(r6)     // Catch: java.lang.Throwable -> La0
        Lae:
            throw r6     // Catch: java.lang.Throwable -> La0
        Laf:
            r1.j()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: xb.d.read(xb.j, long):long");
    }

    @Override // xb.o0
    public final q0 timeout() {
        switch (this.f22720i) {
            case 0:
                return (n0) this.f22721l;
            default:
                return (q0) this.f22722m;
        }
    }

    public final String toString() {
        switch (this.f22720i) {
            case 0:
                return "AsyncTimeout.source(" + ((d) this.f22722m) + ')';
            default:
                return "source(" + ((InputStream) this.f22721l) + ')';
        }
    }
}
