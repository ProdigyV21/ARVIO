package ob;

import androidx.appcompat.widget.f0;
import gb.h0;
import gb.i0;
import gb.k0;
import gb.p0;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.StreamResetException;
import xb.m0;
import xb.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements mb.d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final List f20936g = ib.c.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final List f20937h = ib.c.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final lb.k f20938a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final mb.f f20939b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o f20940c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile x f20941d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i0 f20942e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f20943f;

    public p(h0 h0Var, lb.k kVar, mb.f fVar, o oVar) {
        this.f20938a = kVar;
        this.f20939b = fVar;
        this.f20940c = oVar;
        List list = h0Var.C;
        i0 i0Var = i0.H2_PRIOR_KNOWLEDGE;
        this.f20942e = list.contains(i0Var) ? i0Var : i0.HTTP_2;
    }

    @Override // mb.d
    public final o0 a(p0 p0Var) {
        return this.f20941d.f20973i;
    }

    @Override // mb.d
    public final void b() {
        this.f20941d.g().close();
    }

    @Override // mb.d
    public final void c(k0 k0Var) throws IOException {
        int i10;
        x xVar;
        boolean z;
        if (this.f20941d != null) {
            return;
        }
        boolean z5 = k0Var.f15733d != null;
        gb.a0 a0Var = k0Var.f15732c;
        ArrayList arrayList = new ArrayList(a0Var.size() + 4);
        arrayList.add(new a(k0Var.f15731b, a.f20867f));
        xb.m mVar = a.f20868g;
        gb.b0 b0Var = k0Var.f15730a;
        String strB = b0Var.b();
        String strD = b0Var.d();
        if (strD != null) {
            strB = androidx.compose.foundation.c.m('?', strB, strD);
        }
        arrayList.add(new a(strB, mVar));
        String strA = a0Var.a("Host");
        if (strA != null) {
            arrayList.add(new a(strA, a.f20870i));
        }
        arrayList.add(new a(b0Var.f15617a, a.f20869h));
        int size = a0Var.size();
        for (int i11 = 0; i11 < size; i11++) {
            String lowerCase = a0Var.b(i11).toLowerCase(Locale.US);
            if (!f20936g.contains(lowerCase) || (lowerCase.equals("te") && kotlin.jvm.internal.p.a(a0Var.e(i11), "trailers"))) {
                arrayList.add(new a(lowerCase, a0Var.e(i11)));
            }
        }
        o oVar = this.f20940c;
        boolean z10 = !z5;
        synchronized (oVar.H) {
            synchronized (oVar) {
                try {
                    if (oVar.f20926o > 1073741823) {
                        oVar.o(8);
                    }
                    if (oVar.f20927p) {
                        throw new ConnectionShutdownException();
                    }
                    i10 = oVar.f20926o;
                    oVar.f20926o = i10 + 2;
                    xVar = new x(i10, oVar, z10, false, null);
                    z = !z5 || oVar.E >= oVar.F || xVar.f20969e >= xVar.f20970f;
                    if (xVar.i()) {
                        oVar.f20923l.put(Integer.valueOf(i10), xVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            oVar.H.o(i10, arrayList, z10);
        }
        if (z) {
            oVar.H.flush();
        }
        this.f20941d = xVar;
        if (this.f20943f) {
            this.f20941d.e(9);
            throw new IOException("Canceled");
        }
        w wVar = this.f20941d.k;
        long j10 = this.f20939b.f20368g;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        wVar.g(j10, timeUnit);
        this.f20941d.f20975l.g(this.f20939b.f20369h, timeUnit);
    }

    @Override // mb.d
    public final void cancel() {
        this.f20943f = true;
        x xVar = this.f20941d;
        if (xVar != null) {
            xVar.e(9);
        }
    }

    @Override // mb.d
    public final long d(p0 p0Var) {
        if (mb.e.a(p0Var)) {
            return ib.c.k(p0Var);
        }
        return 0L;
    }

    @Override // mb.d
    public final lb.k e() {
        return this.f20938a;
    }

    @Override // mb.d
    public final gb.o0 f(boolean z) throws IOException {
        gb.a0 a0Var;
        x xVar = this.f20941d;
        if (xVar == null) {
            throw new IOException("stream wasn't created");
        }
        synchronized (xVar) {
            xVar.k.i();
            while (xVar.f20971g.isEmpty() && xVar.f20976m == 0) {
                try {
                    try {
                        xVar.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                } catch (Throwable th) {
                    xVar.k.l();
                    throw th;
                }
            }
            xVar.k.l();
            if (xVar.f20971g.isEmpty()) {
                IOException iOException = xVar.f20977n;
                if (iOException != null) {
                    throw iOException;
                }
                throw new StreamResetException(xVar.f20976m);
            }
            a0Var = (gb.a0) xVar.f20971g.removeFirst();
        }
        i0 i0Var = this.f20942e;
        ArrayList arrayList = new ArrayList(20);
        int size = a0Var.size();
        f0 f0VarJ = null;
        for (int i10 = 0; i10 < size; i10++) {
            String strB = a0Var.b(i10);
            String strE = a0Var.e(i10);
            if (kotlin.jvm.internal.p.a(strB, ":status")) {
                f0VarJ = t7.a.J("HTTP/1.1 " + strE);
            } else if (!f20937h.contains(strB)) {
                arrayList.add(strB);
                arrayList.add(kotlin.text.o.L0(strE).toString());
            }
        }
        if (f0VarJ == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        gb.o0 o0Var = new gb.o0();
        o0Var.f15763b = i0Var;
        o0Var.f15764c = f0VarJ.f1566l;
        o0Var.f15765d = (String) f0VarJ.f1568n;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        gb.z zVar = new gb.z();
        kotlin.collections.x.c0(zVar.f15836a, strArr);
        o0Var.f15767f = zVar;
        if (z && o0Var.f15764c == 100) {
            return null;
        }
        return o0Var;
    }

    @Override // mb.d
    public final void g() {
        this.f20940c.flush();
    }

    @Override // mb.d
    public final m0 h(k0 k0Var, long j10) {
        return this.f20941d.g();
    }
}
