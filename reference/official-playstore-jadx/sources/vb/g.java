package vb;

import com.google.android.gms.common.api.internal.g0;
import gb.a0;
import gb.i0;
import gb.k0;
import gb.p0;
import gb.v0;
import gb.w0;
import io.ktor.network.sockets.DatagramKt;
import java.io.IOException;
import java.net.ProtocolException;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;
import xb.l;
import xb.m;
import xb.y;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements v0, i {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final List f22278w = Collections.singletonList(i0.HTTP_1_1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w0 f22279a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Random f22280b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f22281c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f22283e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f22284f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public lb.i f22285g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e f22286h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public j f22287i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k f22288j;
    public final kb.b k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f22289l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.android.replay.util.e f22290m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f22293p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f22294q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f22296s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f22297t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f22298u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f22299v;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public h f22282d = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayDeque f22291n = new ArrayDeque();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayDeque f22292o = new ArrayDeque();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f22295r = -1;

    public g(kb.e eVar, k0 k0Var, w0 w0Var, Random random, long j10, long j11) {
        this.f22279a = w0Var;
        this.f22280b = random;
        this.f22281c = j10;
        this.f22283e = j11;
        this.k = eVar.e();
        String str = k0Var.f15731b;
        if (!"GET".equals(str)) {
            throw new IllegalArgumentException(androidx.compose.material3.d.C("Request must be GET: ", str).toString());
        }
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.f22284f = xb.a.a(v1.i.k(bArr).f22773i, xb.a.f22714a);
    }

    @Override // gb.v0
    public final boolean a(String str) {
        byte[] bytes = str.getBytes(kotlin.text.a.f19924a);
        m mVar = new m(bytes);
        mVar.f22775m = str;
        synchronized (this) {
            if (!this.f22297t && !this.f22294q) {
                long j10 = this.f22293p;
                if (((long) bytes.length) + j10 > 16777216) {
                    e(1001, null);
                    return false;
                }
                this.f22293p = j10 + ((long) bytes.length);
                this.f22292o.add(new d(mVar));
                l();
                return true;
            }
            return false;
        }
    }

    @Override // vb.i
    public final synchronized void b(m mVar) {
        try {
            if (!this.f22297t && (!this.f22294q || !this.f22292o.isEmpty())) {
                this.f22291n.add(mVar);
                l();
            }
        } finally {
        }
    }

    @Override // vb.i
    public final void c(String str) {
        this.f22279a.onMessage(this, str);
    }

    @Override // vb.i
    public final void d(m mVar) {
        this.f22279a.onMessage(this, mVar);
    }

    @Override // gb.v0
    public final boolean e(int i10, String str) {
        String str2;
        synchronized (this) {
            m mVar = null;
            try {
                if (i10 < 1000 || i10 >= 5000) {
                    str2 = "Code must be in range [1000,5000): " + i10;
                } else if ((1004 > i10 || i10 >= 1007) && (1015 > i10 || i10 >= 3000)) {
                    str2 = null;
                } else {
                    str2 = "Code " + i10 + " is reserved and may not be used.";
                }
                if (str2 != null) {
                    throw new IllegalArgumentException(str2.toString());
                }
                if (str != null) {
                    mVar = new m(str.getBytes(kotlin.text.a.f19924a));
                    mVar.f22775m = str;
                    if (r1.length > 123) {
                        throw new IllegalArgumentException("reason.size() > 123: ".concat(str).toString());
                    }
                }
                if (!this.f22297t && !this.f22294q) {
                    this.f22294q = true;
                    this.f22292o.add(new c(i10, mVar));
                    l();
                    return true;
                }
                return false;
            } finally {
            }
        }
    }

    @Override // vb.i
    public final synchronized void f() {
        this.f22299v = false;
    }

    @Override // vb.i
    public final void g(int i10, String str) {
        io.sentry.android.replay.util.e eVar;
        j jVar;
        k kVar;
        if (i10 == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        synchronized (this) {
            if (this.f22295r != -1) {
                throw new IllegalStateException("already closed");
            }
            this.f22295r = i10;
            this.f22296s = str;
            eVar = null;
            if (this.f22294q && this.f22292o.isEmpty()) {
                io.sentry.android.replay.util.e eVar2 = this.f22290m;
                this.f22290m = null;
                jVar = this.f22287i;
                this.f22287i = null;
                kVar = this.f22288j;
                this.f22288j = null;
                this.k.f();
                eVar = eVar2;
            } else {
                jVar = null;
                kVar = null;
            }
        }
        try {
            this.f22279a.onClosing(this, i10, str);
            if (eVar != null) {
                this.f22279a.onClosed(this, i10, str);
            }
        } finally {
            if (eVar != null) {
                ib.c.d(eVar);
            }
            if (jVar != null) {
                ib.c.d(jVar);
            }
            if (kVar != null) {
                ib.c.d(kVar);
            }
        }
    }

    public final void h(p0 p0Var, g0 g0Var) {
        a0 a0Var = p0Var.f15785p;
        int i10 = p0Var.f15783n;
        if (i10 != 101) {
            StringBuilder sb2 = new StringBuilder("Expected HTTP 101 response but was '");
            sb2.append(i10);
            sb2.append(' ');
            throw new ProtocolException(androidx.compose.foundation.c.u(sb2, p0Var.f15782m, '\''));
        }
        String strA = a0Var.a("Connection");
        if (strA == null) {
            strA = null;
        }
        if (!"Upgrade".equalsIgnoreCase(strA)) {
            throw new ProtocolException(androidx.compose.material3.d.i('\'', "Expected 'Connection' header value 'Upgrade' but was '", strA));
        }
        String strA2 = a0Var.a("Upgrade");
        if (strA2 == null) {
            strA2 = null;
        }
        if (!"websocket".equalsIgnoreCase(strA2)) {
            throw new ProtocolException(androidx.compose.material3.d.i('\'', "Expected 'Upgrade' header value 'websocket' but was '", strA2));
        }
        String strA3 = a0Var.a("Sec-WebSocket-Accept");
        String str = strA3 != null ? strA3 : null;
        byte[] bytes = a0.c.p(new StringBuilder(), this.f22284f, "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(kotlin.text.a.f19924a);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(bytes, 0, bytes.length);
        String strA4 = xb.a.a(new m(messageDigest.digest()).f22773i, xb.a.f22714a);
        if (strA4.equals(str)) {
            if (g0Var == null) {
                throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
            }
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strA4 + "' but was '" + str + '\'');
    }

    public final void i(Exception exc, p0 p0Var) {
        synchronized (this) {
            if (this.f22297t) {
                return;
            }
            this.f22297t = true;
            io.sentry.android.replay.util.e eVar = this.f22290m;
            this.f22290m = null;
            j jVar = this.f22287i;
            this.f22287i = null;
            k kVar = this.f22288j;
            this.f22288j = null;
            this.k.f();
            try {
                this.f22279a.onFailure(this, exc, p0Var);
            } finally {
                if (eVar != null) {
                    ib.c.d(eVar);
                }
                if (jVar != null) {
                    ib.c.d(jVar);
                }
                if (kVar != null) {
                    ib.c.d(kVar);
                }
            }
        }
    }

    public final void j(String str, io.sentry.android.replay.util.e eVar) {
        g gVar;
        h hVar = this.f22282d;
        synchronized (this) {
            try {
                this.f22289l = str;
                this.f22290m = eVar;
                this.f22288j = new k((xb.k) eVar.f17048m, this.f22280b, hVar.f22300a, hVar.f22302c, this.f22283e);
                this.f22286h = new e(this);
                long j10 = this.f22281c;
                if (j10 != 0) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(j10);
                    gVar = this;
                    try {
                        this.k.c(new ob.m(str.concat(" ping"), nanos, 1, gVar), nanos);
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } else {
                    gVar = this;
                }
                if (!gVar.f22292o.isEmpty()) {
                    l();
                }
                gVar.f22287i = new j((l) eVar.f17047l, this, hVar.f22300a, hVar.f22304e);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void k() {
        while (this.f22295r == -1) {
            j jVar = this.f22287i;
            jVar.j();
            if (!jVar.f22314s) {
                i iVar = jVar.f22307l;
                xb.j jVar2 = jVar.f22317v;
                int i10 = jVar.f22311p;
                if (i10 != 1 && i10 != 2) {
                    byte[] bArr = ib.c.f16268a;
                    throw new ProtocolException("Unknown opcode: ".concat(Integer.toHexString(i10)));
                }
                while (!jVar.f22310o) {
                    long j10 = jVar.f22312q;
                    if (j10 > 0) {
                        jVar.f22306i.S(jVar2, j10);
                    }
                    if (jVar.f22313r) {
                        if (jVar.f22315t) {
                            a aVar = jVar.f22318w;
                            if (aVar == null) {
                                aVar = new a(jVar.f22309n, 1);
                                jVar.f22318w = aVar;
                            }
                            Inflater inflater = (Inflater) aVar.f22268n;
                            xb.j jVar3 = aVar.f22267m;
                            if (jVar3.f22759l != 0) {
                                throw new IllegalArgumentException("Failed requirement.");
                            }
                            if (aVar.f22266l) {
                                inflater.reset();
                            }
                            jVar3.z0(jVar2);
                            jVar3.P0(DatagramKt.MAX_DATAGRAM_SIZE);
                            long bytesRead = inflater.getBytesRead() + jVar3.f22759l;
                            do {
                                ((y) aVar.f22269o).i(jVar2, Long.MAX_VALUE);
                            } while (inflater.getBytesRead() < bytesRead);
                        }
                        if (i10 == 1) {
                            iVar.c(jVar2.t0());
                        } else {
                            iVar.d(jVar2.c0(jVar2.f22759l));
                        }
                    } else {
                        while (!jVar.f22310o) {
                            jVar.j();
                            if (!jVar.f22314s) {
                                break;
                            } else {
                                jVar.i();
                            }
                        }
                        if (jVar.f22311p != 0) {
                            int i11 = jVar.f22311p;
                            byte[] bArr2 = ib.c.f16268a;
                            throw new ProtocolException("Expected continuation opcode. Got: ".concat(Integer.toHexString(i11)));
                        }
                    }
                }
                throw new IOException("closed");
            }
            jVar.i();
        }
    }

    public final void l() {
        byte[] bArr = ib.c.f16268a;
        e eVar = this.f22286h;
        if (eVar != null) {
            this.k.c(eVar, 0L);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074 A[Catch: all -> 0x007d, TRY_ENTER, TryCatch #3 {all -> 0x007d, blocks: (B:28:0x0074, B:31:0x0080, B:33:0x0084, B:34:0x008b, B:36:0x0097, B:39:0x009b, B:40:0x009c, B:41:0x009d, B:43:0x00a1, B:57:0x00c1, B:60:0x00e5, B:62:0x00ef, B:63:0x00f6, B:66:0x0101, B:68:0x0105, B:77:0x011b, B:78:0x011d, B:79:0x011e, B:80:0x0127, B:58:0x00d5, B:81:0x0128, B:82:0x012d, B:65:0x00fe, B:35:0x008c), top: B:97:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0080 A[Catch: all -> 0x007d, TryCatch #3 {all -> 0x007d, blocks: (B:28:0x0074, B:31:0x0080, B:33:0x0084, B:34:0x008b, B:36:0x0097, B:39:0x009b, B:40:0x009c, B:41:0x009d, B:43:0x00a1, B:57:0x00c1, B:60:0x00e5, B:62:0x00ef, B:63:0x00f6, B:66:0x0101, B:68:0x0105, B:77:0x011b, B:78:0x011d, B:79:0x011e, B:80:0x0127, B:58:0x00d5, B:81:0x0128, B:82:0x012d, B:65:0x00fe, B:35:0x008c), top: B:97:0x0072, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010c A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0111 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0116 A[DONT_GENERATE] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m() {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: vb.g.m():boolean");
    }
}
