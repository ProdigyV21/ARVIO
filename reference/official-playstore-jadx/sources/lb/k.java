package lb;

import com.google.common.util.concurrent.r0;
import gb.i0;
import gb.j0;
import gb.k0;
import gb.n;
import gb.o0;
import gb.p;
import gb.p0;
import gb.t0;
import gb.u;
import gb.y;
import gb.z;
import io.ktor.network.sockets.DatagramKt;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import m2.f0;
import ob.b0;
import ob.o;
import ob.x;
import okhttp3.internal.connection.RouteException;
import qb.l;
import xb.d0;
import xb.h0;
import xb.n0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends ob.g implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t0 f20125b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Socket f20126c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Socket f20127d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y f20128e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i0 f20129f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o f20130g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public xb.i0 f20131h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h0 f20132i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f20133j;
    public boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20134l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20135m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20136n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20137o = 1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f20138p = new ArrayList();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f20139q = Long.MAX_VALUE;

    public k(t0 t0Var) {
        this.f20125b = t0Var;
    }

    public static void d(gb.h0 h0Var, t0 t0Var, IOException iOException) {
        if (t0Var.f15817b.type() != Proxy.Type.DIRECT) {
            gb.a aVar = t0Var.f15816a;
            aVar.f15611g.connectFailed(aVar.f15612h.i(), t0Var.f15817b.address(), iOException);
        }
        kb.d dVar = h0Var.M;
        synchronized (dVar) {
            ((LinkedHashSet) dVar.f19673l).add(t0Var);
        }
    }

    @Override // ob.g
    public final synchronized void a(b0 b0Var) {
        this.f20137o = (b0Var.f20882a & 16) != 0 ? b0Var.f20883b[4] : Integer.MAX_VALUE;
    }

    @Override // ob.g
    public final void b(x xVar) {
        xVar.c(null, 8);
    }

    public final void c(int i10, int i11, int i12, int i13, boolean z, gb.i iVar, u uVar) throws Throwable {
        t0 t0Var;
        if (this.f20129f != null) {
            throw new IllegalStateException("already connected");
        }
        gb.a aVar = this.f20125b.f15816a;
        List list = aVar.f15614j;
        b bVar = new b(list);
        if (aVar.f15607c == null) {
            if (!list.contains(p.f15775f)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String str = this.f20125b.f15816a.f15612h.f15620d;
            qb.n nVar = qb.n.f21385a;
            if (!qb.n.f21385a.h(str)) {
                throw new RouteException(new UnknownServiceException(a0.c.l("CLEARTEXT communication to ", str, " not permitted by network security policy")));
            }
        } else if (aVar.f15613i.contains(i0.H2_PRIOR_KNOWLEDGE)) {
            throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        RouteException routeException = null;
        do {
            try {
                t0 t0Var2 = this.f20125b;
                if (t0Var2.f15816a.f15607c != null && t0Var2.f15817b.type() == Proxy.Type.HTTP) {
                    f(i10, i11, i12, uVar);
                    if (this.f20126c == null) {
                    }
                    t0Var = this.f20125b;
                    if (t0Var.f15816a.f15607c == null && t0Var.f15817b.type() == Proxy.Type.HTTP && this.f20126c == null) {
                        throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                    }
                    this.f20139q = System.nanoTime();
                    return;
                }
                e(i10, i11, uVar);
                g(bVar, i13, uVar);
                InetSocketAddress inetSocketAddress = this.f20125b.f15818c;
                t0Var = this.f20125b;
                if (t0Var.f15816a.f15607c == null) {
                }
                this.f20139q = System.nanoTime();
                return;
            } catch (IOException e5) {
                Socket socket = this.f20127d;
                if (socket != null) {
                    ib.c.e(socket);
                }
                Socket socket2 = this.f20126c;
                if (socket2 != null) {
                    ib.c.e(socket2);
                }
                this.f20127d = null;
                this.f20126c = null;
                this.f20131h = null;
                this.f20132i = null;
                this.f20128e = null;
                this.f20129f = null;
                this.f20130g = null;
                this.f20137o = 1;
                InetSocketAddress inetSocketAddress2 = this.f20125b.f15818c;
                if (routeException == null) {
                    routeException = new RouteException(e5);
                } else {
                    r0.a(routeException.f20987i, e5);
                    routeException.f20988l = e5;
                }
                if (!z) {
                    throw routeException;
                }
                bVar.f20083d = true;
                if (!bVar.f20082c) {
                    throw routeException;
                }
                if (e5 instanceof ProtocolException) {
                    throw routeException;
                }
                if (e5 instanceof InterruptedIOException) {
                    throw routeException;
                }
                if ((e5 instanceof SSLHandshakeException) && (e5.getCause() instanceof CertificateException)) {
                    throw routeException;
                }
                if (e5 instanceof SSLPeerUnverifiedException) {
                    throw routeException;
                }
            }
        } while (e5 instanceof SSLException);
        throw routeException;
    }

    public final void e(int i10, int i11, u uVar) throws IOException {
        t0 t0Var = this.f20125b;
        Proxy proxy = t0Var.f15817b;
        gb.a aVar = t0Var.f15816a;
        Proxy.Type type = proxy.type();
        int i12 = type == null ? -1 : j.f20124a[type.ordinal()];
        int i13 = 1;
        Socket socketCreateSocket = (i12 == 1 || i12 == 2) ? aVar.f15606b.createSocket() : new Socket(proxy);
        this.f20126c = socketCreateSocket;
        InetSocketAddress inetSocketAddress = this.f20125b.f15818c;
        uVar.getClass();
        socketCreateSocket.setSoTimeout(i11);
        try {
            qb.n nVar = qb.n.f21385a;
            qb.n.f21385a.e(socketCreateSocket, this.f20125b.f15818c, i10);
            try {
                Logger logger = d0.f22723a;
                n0 n0Var = new n0(socketCreateSocket);
                int i14 = 0;
                this.f20131h = new xb.i0(new xb.d(n0Var, new xb.d(socketCreateSocket.getInputStream(), n0Var, i13), i14));
                n0 n0Var2 = new n0(socketCreateSocket);
                this.f20132i = new h0(new xb.c(n0Var2, new xb.c(socketCreateSocket.getOutputStream(), n0Var2, i13), i14));
            } catch (NullPointerException e5) {
                if (kotlin.jvm.internal.p.a(e5.getMessage(), "throw with null exception")) {
                    throw new IOException(e5);
                }
            }
        } catch (ConnectException e6) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f20125b.f15818c);
            connectException.initCause(e6);
            throw connectException;
        }
    }

    public final void f(int i10, int i11, int i12, u uVar) throws IOException {
        j0 j0Var = new j0();
        t0 t0Var = this.f20125b;
        j0Var.f15722a = t0Var.f15816a.f15612h;
        j0Var.f("CONNECT", null);
        gb.a aVar = t0Var.f15816a;
        j0Var.f15724c.h("Host", ib.c.w(aVar.f15612h, true));
        j0Var.f15724c.h("Proxy-Connection", "Keep-Alive");
        j0Var.f15724c.h("User-Agent", "okhttp/4.12.0");
        k0 k0VarB = j0Var.b();
        z zVar = new z();
        gb.r0 r0Var = ib.c.f16270c;
        zVar.h("Proxy-Authenticate", "OkHttp-Preemptive");
        aVar.f15610f.a(t0Var, new p0(k0VarB, i0.HTTP_1_1, "Preemptive Authenticate", 407, null, zVar.e(), r0Var, null, null, null, -1L, -1L, null));
        gb.b0 b0Var = k0VarB.f15730a;
        e(i10, i11, uVar);
        String str = "CONNECT " + ib.c.w(b0Var, true) + " HTTP/1.1";
        xb.i0 i0Var = this.f20131h;
        h0 h0Var = this.f20132i;
        androidx.appcompat.widget.u uVar2 = new androidx.appcompat.widget.u(null, this, i0Var, h0Var);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        i0Var.f22755i.timeout().g(i11, timeUnit);
        h0Var.f22750i.timeout().g(i12, timeUnit);
        uVar2.s(k0VarB.f15732c, str);
        uVar2.b();
        o0 o0VarF = uVar2.f(false);
        o0VarF.f15762a = k0VarB;
        p0 p0VarA = o0VarF.a();
        int i13 = p0VarA.f15783n;
        long jK = ib.c.k(p0VarA);
        if (jK != -1) {
            nb.d dVarM = uVar2.m(jK);
            ib.c.u(dVarM, Integer.MAX_VALUE);
            dVarM.close();
        }
        if (i13 != 200) {
            if (i13 != 407) {
                throw new IOException(a0.c.i(i13, "Unexpected response code for CONNECT: "));
            }
            aVar.f15610f.a(t0Var, p0VarA);
            throw new IOException("Failed to authenticate with proxy");
        }
        if (!i0Var.f22756l.o() || !h0Var.f22751l.o()) {
            throw new IOException("TLS tunnel buffered too many bytes!");
        }
    }

    public final void g(b bVar, int i10, u uVar) throws Throwable {
        SSLSocket sSLSocket;
        SSLSocket sSLSocket2;
        String strF;
        i0 i0Var = i0.HTTP_2;
        i0 i0Var2 = i0.HTTP_1_1;
        i0 i0Var3 = i0.H2_PRIOR_KNOWLEDGE;
        gb.a aVar = this.f20125b.f15816a;
        if (aVar.f15607c == null) {
            if (!aVar.f15613i.contains(i0Var3)) {
                this.f20127d = this.f20126c;
                this.f20129f = i0Var2;
                return;
            } else {
                this.f20127d = this.f20126c;
                this.f20129f = i0Var3;
                m(i10);
                return;
            }
        }
        uVar.getClass();
        gb.a aVar2 = this.f20125b.f15816a;
        SSLSocketFactory sSLSocketFactory = aVar2.f15607c;
        try {
            Socket socket = this.f20126c;
            gb.b0 b0Var = aVar2.f15612h;
            sSLSocket2 = (SSLSocket) sSLSocketFactory.createSocket(socket, b0Var.f15620d, b0Var.f15621e, true);
        } catch (Throwable th) {
            th = th;
            sSLSocket = null;
        }
        try {
            p pVarA = bVar.a(sSLSocket2);
            if (pVarA.f15777b) {
                qb.n nVar = qb.n.f21385a;
                qb.n.f21385a.d(sSLSocket2, aVar2.f15612h.f15620d, aVar2.f15613i);
            }
            sSLSocket2.startHandshake();
            SSLSession session = sSLSocket2.getSession();
            y yVarO = l.o(session);
            int i11 = 2;
            if (!aVar2.f15608d.verify(aVar2.f15612h.f15620d, session)) {
                List listA = yVarO.a();
                if (listA.isEmpty()) {
                    throw new SSLPeerUnverifiedException("Hostname " + aVar2.f15612h.f15620d + " not verified (no certificates)");
                }
                X509Certificate x509Certificate = (X509Certificate) listA.get(0);
                StringBuilder sb2 = new StringBuilder("\n              |Hostname ");
                sb2.append(aVar2.f15612h.f15620d);
                sb2.append(" not verified:\n              |    certificate: ");
                gb.k kVar = gb.k.f15727c;
                sb2.append(f0.F(x509Certificate));
                sb2.append("\n              |    DN: ");
                sb2.append(x509Certificate.getSubjectDN().getName());
                sb2.append("\n              |    subjectAltNames: ");
                sb2.append(kotlin.collections.x.I0(ub.c.a(x509Certificate, 7), ub.c.a(x509Certificate, 2)));
                sb2.append("\n              ");
                throw new SSLPeerUnverifiedException(f4.f.z(sb2.toString()));
            }
            gb.k kVar2 = aVar2.f15609e;
            this.f20128e = new y(yVarO.f15832a, yVarO.f15833b, yVarO.f15834c, new a8.y(kVar2, yVarO, aVar2, i11));
            String str = aVar2.f15612h.f15620d;
            Iterator it = kVar2.f15728a.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
            if (pVarA.f15777b) {
                qb.n nVar2 = qb.n.f21385a;
                strF = qb.n.f21385a.f(sSLSocket2);
            } else {
                strF = null;
            }
            this.f20127d = sSLSocket2;
            Logger logger = d0.f22723a;
            n0 n0Var = new n0(sSLSocket2);
            this.f20131h = new xb.i0(new xb.d(n0Var, new xb.d(sSLSocket2.getInputStream(), n0Var, 1), 0));
            n0 n0Var2 = new n0(sSLSocket2);
            this.f20132i = new h0(new xb.c(n0Var2, new xb.c(sSLSocket2.getOutputStream(), n0Var2, 1), 0));
            if (strF != null) {
                i0 i0Var4 = i0.HTTP_1_0;
                if (strF.equals("http/1.0")) {
                    i0Var2 = i0Var4;
                } else if (!strF.equals("http/1.1")) {
                    if (strF.equals("h2_prior_knowledge")) {
                        i0Var2 = i0Var3;
                    } else if (strF.equals("h2")) {
                        i0Var2 = i0Var;
                    } else {
                        i0Var2 = i0.SPDY_3;
                        if (!strF.equals("spdy/3.1")) {
                            i0Var2 = i0.QUIC;
                            if (!strF.equals("quic")) {
                                throw new IOException("Unexpected protocol: ".concat(strF));
                            }
                        }
                    }
                }
            }
            this.f20129f = i0Var2;
            qb.n nVar3 = qb.n.f21385a;
            qb.n.f21385a.a(sSLSocket2);
            if (this.f20129f == i0Var) {
                m(i10);
            }
        } catch (Throwable th2) {
            th = th2;
            sSLSocket = sSLSocket2;
            if (sSLSocket != null) {
                qb.n nVar4 = qb.n.f21385a;
                qb.n.f21385a.a(sSLSocket);
            }
            if (sSLSocket != null) {
                ib.c.e(sSLSocket);
            }
            throw th;
        }
    }

    public final synchronized void h() {
        this.f20135m++;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean i(gb.a r8, java.util.List r9) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: lb.k.i(gb.a, java.util.List):boolean");
    }

    public final boolean j(boolean z) {
        long j10;
        byte[] bArr = ib.c.f16268a;
        long jNanoTime = System.nanoTime();
        Socket socket = this.f20126c;
        Socket socket2 = this.f20127d;
        xb.i0 i0Var = this.f20131h;
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        o oVar = this.f20130g;
        if (oVar != null) {
            return oVar.k(jNanoTime);
        }
        synchronized (this) {
            j10 = jNanoTime - this.f20139q;
        }
        if (j10 < 10000000000L || !z) {
            return true;
        }
        try {
            int soTimeout = socket2.getSoTimeout();
            try {
                socket2.setSoTimeout(1);
                return !i0Var.i();
            } finally {
                socket2.setSoTimeout(soTimeout);
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final mb.d k(gb.h0 h0Var, mb.f fVar) {
        int i10 = fVar.f20368g;
        Socket socket = this.f20127d;
        xb.i0 i0Var = this.f20131h;
        h0 h0Var2 = this.f20132i;
        o oVar = this.f20130g;
        if (oVar != null) {
            return new ob.p(h0Var, this, fVar, oVar);
        }
        socket.setSoTimeout(i10);
        q0 q0VarTimeout = i0Var.f22755i.timeout();
        long j10 = i10;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        q0VarTimeout.g(j10, timeUnit);
        h0Var2.f22750i.timeout().g(fVar.f20369h, timeUnit);
        return new androidx.appcompat.widget.u(h0Var, this, i0Var, h0Var2);
    }

    public final synchronized void l() {
        this.f20133j = true;
    }

    public final void m(int i10) throws SocketException {
        Socket socket = this.f20127d;
        xb.i0 i0Var = this.f20131h;
        h0 h0Var = this.f20132i;
        socket.setSoTimeout(0);
        kb.e eVar = kb.e.f19674h;
        o5.a aVar = new o5.a(eVar);
        String str = this.f20125b.f15816a.f15612h.f15620d;
        aVar.f20778e = socket;
        aVar.f20775b = ib.c.f16275h + ' ' + str;
        aVar.f20779f = i0Var;
        aVar.f20780g = h0Var;
        aVar.f20781h = this;
        aVar.f20776c = i10;
        o oVar = new o(aVar);
        this.f20130g = oVar;
        b0 b0Var = o.K;
        this.f20137o = (b0Var.f20882a & 16) != 0 ? b0Var.f20883b[4] : Integer.MAX_VALUE;
        ob.y yVar = oVar.H;
        synchronized (yVar) {
            try {
                if (yVar.f20982n) {
                    throw new IOException("closed");
                }
                Logger logger = ob.y.f20978p;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(ib.c.i(">> CONNECTION " + ob.e.f20894a.i(), new Object[0]));
                }
                yVar.f20979i.U(ob.e.f20894a);
                yVar.f20979i.flush();
            } catch (Throwable th) {
                throw th;
            }
        }
        oVar.H.G(oVar.A);
        if (oVar.A.a() != 65535) {
            oVar.H.O(0, r0 - DatagramKt.MAX_DATAGRAM_SIZE);
        }
        eVar.e().c(new jb.g(oVar.f20924m, oVar.I, 1), 0L);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb2 = new StringBuilder("Connection{");
        t0 t0Var = this.f20125b;
        sb2.append(t0Var.f15816a.f15612h.f15620d);
        sb2.append(':');
        sb2.append(t0Var.f15816a.f15612h.f15621e);
        sb2.append(", proxy=");
        sb2.append(t0Var.f15817b);
        sb2.append(" hostAddress=");
        sb2.append(t0Var.f15818c);
        sb2.append(" cipherSuite=");
        y yVar = this.f20128e;
        if (yVar == null || (obj = yVar.f15833b) == null) {
            obj = "none";
        }
        sb2.append(obj);
        sb2.append(" protocol=");
        sb2.append(this.f20129f);
        sb2.append('}');
        return sb2.toString();
    }
}
