package gb;

import gb.i;
import gb.v0;
import java.net.ProtocolException;
import java.net.ProxySelector;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 implements Cloneable, i.a, v0.a {
    public static final List N = ib.c.l(i0.HTTP_2, i0.HTTP_1_1);
    public static final List O = ib.c.l(p.f15774e, p.f15775f);
    public final X509TrustManager A;
    public final List B;
    public final List C;
    public final ub.c D;
    public final k E;
    public final t7.a F;
    public final int G;
    public final int H;
    public final int I;
    public final int J;
    public final int K;
    public final long L;
    public final kb.d M;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.emoji2.text.e0 f15700i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o f15701l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f15702m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f15703n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final com.google.firebase.installations.d f15704o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f15705p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final u f15706q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f15707r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f15708s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final r f15709t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final g f15710u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final t f15711v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ProxySelector f15712w;
    public final u x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final SocketFactory f15713y;
    public final SSLSocketFactory z;

    public h0(g0 g0Var) throws NoSuchAlgorithmException, KeyStoreException {
        this.f15700i = g0Var.f15663a;
        this.f15701l = g0Var.f15664b;
        this.f15702m = ib.c.x(g0Var.f15665c);
        this.f15703n = ib.c.x(g0Var.f15666d);
        this.f15704o = g0Var.f15667e;
        this.f15705p = g0Var.f15668f;
        this.f15706q = g0Var.f15669g;
        this.f15707r = g0Var.f15670h;
        this.f15708s = g0Var.f15671i;
        this.f15709t = g0Var.f15672j;
        this.f15710u = g0Var.k;
        this.f15711v = g0Var.f15673l;
        ProxySelector proxySelector = g0Var.f15674m;
        proxySelector = proxySelector == null ? ProxySelector.getDefault() : proxySelector;
        this.f15712w = proxySelector == null ? sb.a.f21772a : proxySelector;
        this.x = g0Var.f15675n;
        this.f15713y = g0Var.f15676o;
        List list = g0Var.f15679r;
        this.B = list;
        this.C = g0Var.f15680s;
        this.D = g0Var.f15681t;
        this.G = g0Var.f15684w;
        this.H = g0Var.x;
        this.I = g0Var.f15685y;
        this.J = g0Var.z;
        this.K = g0Var.A;
        this.L = g0Var.B;
        kb.d dVar = g0Var.C;
        this.M = dVar == null ? new kb.d(3) : dVar;
        if (list == null || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((p) it.next()).f15776a) {
                    SSLSocketFactory sSLSocketFactory = g0Var.f15677p;
                    if (sSLSocketFactory != null) {
                        this.z = sSLSocketFactory;
                        t7.a aVar = g0Var.f15683v;
                        this.F = aVar;
                        this.A = g0Var.f15678q;
                        k kVar = g0Var.f15682u;
                        this.E = kotlin.jvm.internal.p.a(kVar.f15729b, aVar) ? kVar : new k(kVar.f15728a, aVar);
                    } else {
                        qb.n nVar = qb.n.f21385a;
                        X509TrustManager x509TrustManagerN = qb.n.f21385a.n();
                        this.A = x509TrustManagerN;
                        this.z = qb.n.f21385a.m(x509TrustManagerN);
                        t7.a aVarB = qb.n.f21385a.b(x509TrustManagerN);
                        this.F = aVarB;
                        k kVar2 = g0Var.f15682u;
                        this.E = kotlin.jvm.internal.p.a(kVar2.f15729b, aVarB) ? kVar2 : new k(kVar2.f15728a, aVarB);
                    }
                }
            }
            this.z = null;
            this.F = null;
            this.A = null;
            this.E = k.f15727c;
        } else {
            this.z = null;
            this.F = null;
            this.A = null;
            this.E = k.f15727c;
        }
        X509TrustManager x509TrustManager = this.A;
        t7.a aVar2 = this.F;
        SSLSocketFactory sSLSocketFactory2 = this.z;
        List list2 = this.f15703n;
        List list3 = this.f15702m;
        if (list3.contains(null)) {
            throw new IllegalStateException(("Null interceptor: " + list3).toString());
        }
        if (list2.contains(null)) {
            throw new IllegalStateException(("Null network interceptor: " + list2).toString());
        }
        List list4 = this.B;
        if (list4 == null || !list4.isEmpty()) {
            Iterator it2 = list4.iterator();
            while (it2.hasNext()) {
                if (((p) it2.next()).f15776a) {
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("sslSocketFactory == null");
                    }
                    if (aVar2 == null) {
                        throw new IllegalStateException("certificateChainCleaner == null");
                    }
                    if (x509TrustManager == null) {
                        throw new IllegalStateException("x509TrustManager == null");
                    }
                    return;
                }
            }
        }
        if (sSLSocketFactory2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (aVar2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (x509TrustManager != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (!kotlin.jvm.internal.p.a(this.E, k.f15727c)) {
            throw new IllegalStateException("Check failed.");
        }
    }

    @Override // gb.i.a
    public final lb.i a(k0 k0Var) {
        return new lb.i(this, k0Var, false);
    }

    public final g0 b() {
        g0 g0Var = new g0();
        g0Var.f15663a = this.f15700i;
        g0Var.f15664b = this.f15701l;
        kotlin.collections.x.b0(g0Var.f15665c, this.f15702m);
        kotlin.collections.x.b0(g0Var.f15666d, this.f15703n);
        g0Var.f15667e = this.f15704o;
        g0Var.f15668f = this.f15705p;
        g0Var.f15669g = this.f15706q;
        g0Var.f15670h = this.f15707r;
        g0Var.f15671i = this.f15708s;
        g0Var.f15672j = this.f15709t;
        g0Var.k = this.f15710u;
        g0Var.f15673l = this.f15711v;
        g0Var.f15674m = this.f15712w;
        g0Var.f15675n = this.x;
        g0Var.f15676o = this.f15713y;
        g0Var.f15677p = this.z;
        g0Var.f15678q = this.A;
        g0Var.f15679r = this.B;
        g0Var.f15680s = this.C;
        g0Var.f15681t = this.D;
        g0Var.f15682u = this.E;
        g0Var.f15683v = this.F;
        g0Var.f15684w = this.G;
        g0Var.x = this.H;
        g0Var.f15685y = this.I;
        g0Var.z = this.J;
        g0Var.A = this.K;
        g0Var.B = this.L;
        g0Var.C = this.M;
        return g0Var;
    }

    public final vb.g c(k0 k0Var, w0 w0Var) {
        vb.g gVar = new vb.g(kb.e.f19674h, k0Var, w0Var, new Random(), this.K, this.L);
        if (k0Var.f15732c.a("Sec-WebSocket-Extensions") != null) {
            gVar.i(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
            return gVar;
        }
        g0 g0VarB = b();
        byte[] bArr = ib.c.f16268a;
        g0VarB.f15667e = new com.google.firebase.installations.d(2);
        ArrayList arrayList = new ArrayList(vb.g.f22278w);
        i0 i0Var = i0.H2_PRIOR_KNOWLEDGE;
        if (!arrayList.contains(i0Var) && !arrayList.contains(i0.HTTP_1_1)) {
            throw new IllegalArgumentException(("protocols must contain h2_prior_knowledge or http/1.1: " + arrayList).toString());
        }
        if (arrayList.contains(i0Var) && arrayList.size() > 1) {
            throw new IllegalArgumentException(("protocols containing h2_prior_knowledge cannot use other protocols: " + arrayList).toString());
        }
        if (arrayList.contains(i0.HTTP_1_0)) {
            throw new IllegalArgumentException(("protocols must not contain http/1.0: " + arrayList).toString());
        }
        if (arrayList.contains(null)) {
            throw new IllegalArgumentException("protocols must not contain null");
        }
        arrayList.remove(i0.SPDY_3);
        if (!arrayList.equals(g0VarB.f15680s)) {
            g0VarB.C = null;
        }
        g0VarB.f15680s = Collections.unmodifiableList(arrayList);
        h0 h0Var = new h0(g0VarB);
        j0 j0VarB = k0Var.b();
        j0VarB.f15724c.h("Upgrade", "websocket");
        j0VarB.f15724c.h("Connection", "Upgrade");
        j0VarB.f15724c.h("Sec-WebSocket-Key", gVar.f22284f);
        j0VarB.f15724c.h("Sec-WebSocket-Version", "13");
        j0VarB.f15724c.h("Sec-WebSocket-Extensions", "permessage-deflate");
        k0 k0VarB = j0VarB.b();
        lb.i iVar = new lb.i(h0Var, k0VarB, true);
        gVar.f22285g = iVar;
        iVar.K0(new vb.f(gVar, k0VarB));
        return gVar;
    }

    public final Object clone() {
        return super.clone();
    }

    public h0() {
        this(new g0());
    }
}
