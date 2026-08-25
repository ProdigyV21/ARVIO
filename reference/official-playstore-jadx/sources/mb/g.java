package mb;

import com.google.common.util.concurrent.r0;
import gb.b0;
import gb.c0;
import gb.h0;
import gb.k;
import gb.k0;
import gb.o0;
import gb.p0;
import gb.s0;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;
import kotlin.collections.x;
import kotlin.collections.z;
import lb.i;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h0 f20371a;

    public g(h0 h0Var) {
        this.f20371a = h0Var;
    }

    public static int c(p0 p0Var, int i10) {
        String strJ = p0.j(p0Var, "Retry-After");
        if (strJ == null) {
            return i10;
        }
        if (Pattern.compile("\\d+").matcher(strJ).matches()) {
            return Integer.valueOf(strJ).intValue();
        }
        return Integer.MAX_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final gb.k0 a(gb.p0 r13, com.google.android.gms.common.api.internal.g0 r14) throws java.net.ProtocolException {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: mb.g.a(gb.p0, com.google.android.gms.common.api.internal.g0):gb.k0");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(java.io.IOException r4, lb.i r5, gb.k0 r6, boolean r7) {
        /*
            r3 = this;
            gb.h0 r6 = r3.f20371a
            boolean r6 = r6.f15705p
            r0 = 0
            if (r6 != 0) goto L9
            goto L98
        L9:
            if (r7 == 0) goto L10
            boolean r6 = r4 instanceof java.io.FileNotFoundException
            if (r6 == 0) goto L10
            return r0
        L10:
            boolean r6 = r4 instanceof java.net.ProtocolException
            if (r6 == 0) goto L15
            return r0
        L15:
            boolean r6 = r4 instanceof java.io.InterruptedIOException
            if (r6 == 0) goto L20
            boolean r4 = r4 instanceof java.net.SocketTimeoutException
            if (r4 == 0) goto L98
            if (r7 != 0) goto L98
            goto L33
        L20:
            boolean r6 = r4 instanceof javax.net.ssl.SSLHandshakeException
            if (r6 == 0) goto L2e
            java.lang.Throwable r6 = r4.getCause()
            boolean r6 = r6 instanceof java.security.cert.CertificateException
            if (r6 == 0) goto L2e
            goto L98
        L2e:
            boolean r4 = r4 instanceof javax.net.ssl.SSLPeerUnverifiedException
            if (r4 == 0) goto L33
            return r0
        L33:
            lb.e r4 = r5.f20118s
            int r5 = r4.f20101g
            r6 = 1
            if (r5 != 0) goto L44
            int r7 = r4.f20102h
            if (r7 != 0) goto L44
            int r7 = r4.f20103i
            if (r7 != 0) goto L44
            r4 = r0
            goto L96
        L44:
            gb.t0 r7 = r4.f20104j
            if (r7 == 0) goto L49
            goto L91
        L49:
            r7 = 0
            if (r5 > r6) goto L7c
            int r5 = r4.f20102h
            if (r5 > r6) goto L7c
            int r5 = r4.f20103i
            if (r5 <= 0) goto L55
            goto L7c
        L55:
            lb.i r5 = r4.f20097c
            lb.k r5 = r5.f20119t
            if (r5 != 0) goto L5c
            goto L7c
        L5c:
            monitor-enter(r5)
            int r1 = r5.f20134l     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L63
            monitor-exit(r5)
            goto L7c
        L63:
            gb.t0 r1 = r5.f20125b     // Catch: java.lang.Throwable -> L79
            gb.a r1 = r1.f15816a     // Catch: java.lang.Throwable -> L79
            gb.b0 r1 = r1.f15612h     // Catch: java.lang.Throwable -> L79
            gb.a r2 = r4.f20096b     // Catch: java.lang.Throwable -> L79
            gb.b0 r2 = r2.f15612h     // Catch: java.lang.Throwable -> L79
            boolean r1 = ib.c.a(r1, r2)     // Catch: java.lang.Throwable -> L79
            if (r1 != 0) goto L75
            monitor-exit(r5)
            goto L7c
        L75:
            gb.t0 r7 = r5.f20125b     // Catch: java.lang.Throwable -> L79
            monitor-exit(r5)
            goto L7c
        L79:
            r4 = move-exception
            monitor-exit(r5)
            throw r4
        L7c:
            if (r7 == 0) goto L82
            r4.f20104j = r7
        L80:
            r4 = r6
            goto L96
        L82:
            androidx.core.provider.j r5 = r4.f20099e
            if (r5 == 0) goto L8d
            boolean r5 = r5.c()
            if (r5 != r6) goto L8d
            goto L91
        L8d:
            androidx.tv.foundation.lazy.list.k r4 = r4.f20100f
            if (r4 != 0) goto L92
        L91:
            goto L80
        L92:
            boolean r4 = r4.f()
        L96:
            if (r4 != 0) goto L99
        L98:
            return r0
        L99:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: mb.g.b(java.io.IOException, lb.i, gb.k0, boolean):boolean");
    }

    @Override // gb.c0
    public final p0 intercept(c0.a aVar) {
        SSLSocketFactory sSLSocketFactory;
        ub.c cVar;
        k kVar;
        f fVar = (f) aVar;
        k0 k0Var = fVar.f20366e;
        i iVar = fVar.f20362a;
        List listH0 = z.f19728i;
        p0 p0Var = null;
        int i10 = 0;
        k0 k0VarA = k0Var;
        while (true) {
            boolean z = true;
            while (iVar.f20121v == null) {
                synchronized (iVar) {
                    try {
                        if (iVar.x) {
                            throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
                        }
                        if (iVar.f20122w) {
                            throw new IllegalStateException("Check failed.");
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    io.sentry.android.core.internal.util.g gVar = iVar.f20113n;
                    b0 b0Var = k0VarA.f15730a;
                    h0 h0Var = iVar.f20110i;
                    if (b0Var.f15626j) {
                        SSLSocketFactory sSLSocketFactory2 = h0Var.z;
                        if (sSLSocketFactory2 == null) {
                            throw new IllegalStateException("CLEARTEXT-only client");
                        }
                        ub.c cVar2 = h0Var.D;
                        kVar = h0Var.E;
                        sSLSocketFactory = sSLSocketFactory2;
                        cVar = cVar2;
                    } else {
                        sSLSocketFactory = null;
                        cVar = null;
                        kVar = null;
                    }
                    iVar.f20118s = new lb.e(gVar, new gb.a(b0Var.f15620d, b0Var.f15621e, h0Var.f15711v, h0Var.f15713y, sSLSocketFactory, cVar, kVar, h0Var.x, h0Var.C, h0Var.B, h0Var.f15712w), iVar, iVar.f20114o);
                }
                try {
                    if (iVar.z) {
                        throw new IOException("Canceled");
                    }
                    try {
                        try {
                            p0 p0VarA = fVar.a(k0VarA);
                            if (p0Var != null) {
                                o0 o0VarL = p0VarA.l();
                                o0 o0VarL2 = p0Var.l();
                                o0VarL2.f15768g = null;
                                p0 p0VarA2 = o0VarL2.a();
                                if (p0VarA2.f15786q != null) {
                                    throw new IllegalArgumentException("priorResponse.body != null");
                                }
                                o0VarL.f15771j = p0VarA2;
                                p0VarA = o0VarL.a();
                            }
                            p0Var = p0VarA;
                            k0VarA = a(p0Var, iVar.f20121v);
                            if (k0VarA == null) {
                                iVar.e(false);
                                return p0Var;
                            }
                            s0 s0Var = p0Var.f15786q;
                            if (s0Var != null) {
                                ib.c.d(s0Var);
                            }
                            i10++;
                            if (i10 > 20) {
                                throw new ProtocolException("Too many follow-up requests: " + i10);
                            }
                            iVar.e(true);
                        } catch (RouteException e5) {
                            if (!b(e5.f20988l, iVar, k0VarA, false)) {
                                IOException iOException = e5.f20987i;
                                Iterator it = listH0.iterator();
                                while (it.hasNext()) {
                                    r0.a(iOException, (Exception) it.next());
                                }
                                throw iOException;
                            }
                            listH0 = x.H0(e5.f20987i, listH0);
                            iVar.e(true);
                            z = false;
                        }
                    } catch (IOException e6) {
                        if (!b(e6, iVar, k0VarA, !(e6 instanceof ConnectionShutdownException))) {
                            Iterator it2 = listH0.iterator();
                            while (it2.hasNext()) {
                                r0.a(e6, (Exception) it2.next());
                            }
                            throw e6;
                        }
                        listH0 = x.H0(e6, listH0);
                        iVar.e(true);
                        z = false;
                    }
                } catch (Throwable th2) {
                    iVar.e(true);
                    throw th2;
                }
            }
            throw new IllegalStateException("Check failed.");
        }
    }
}
