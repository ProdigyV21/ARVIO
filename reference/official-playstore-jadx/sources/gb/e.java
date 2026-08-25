package gb;

import io.sentry.p7;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class e {
    public static final String k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f15638l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f15639a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a0 f15640b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f15641c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i0 f15642d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f15643e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f15644f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a0 f15645g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final y f15646h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f15647i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f15648j;

    static {
        qb.n nVar = qb.n.f21385a;
        qb.n.f21385a.getClass();
        k = "OkHttp-Sent-Millis";
        qb.n.f21385a.getClass();
        f15638l = "OkHttp-Received-Millis";
    }

    public e(xb.o0 o0Var) throws IOException {
        b0 b0VarD;
        try {
            xb.i0 i0Var = new xb.i0(o0Var);
            String strB = i0Var.B(Long.MAX_VALUE);
            try {
                androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
                kVar.i(null, strB);
                b0VarD = kVar.d();
            } catch (IllegalArgumentException unused) {
                b0VarD = null;
            }
            if (b0VarD == null) {
                IOException iOException = new IOException("Cache corruption for ".concat(strB));
                qb.n nVar = qb.n.f21385a;
                qb.n.f21385a.getClass();
                qb.n.i("cache corruption", iOException, 5);
                throw iOException;
            }
            this.f15639a = b0VarD;
            this.f15641c = i0Var.B(Long.MAX_VALUE);
            z zVar = new z();
            int iZ = k2.c.z(i0Var);
            for (int i10 = 0; i10 < iZ; i10++) {
                zVar.b(i0Var.B(Long.MAX_VALUE));
            }
            this.f15640b = zVar.e();
            androidx.appcompat.widget.f0 f0VarJ = t7.a.J(i0Var.B(Long.MAX_VALUE));
            this.f15642d = (i0) f0VarJ.f1567m;
            this.f15643e = f0VarJ.f1566l;
            this.f15644f = (String) f0VarJ.f1568n;
            z zVar2 = new z();
            int iZ2 = k2.c.z(i0Var);
            for (int i11 = 0; i11 < iZ2; i11++) {
                zVar2.b(i0Var.B(Long.MAX_VALUE));
            }
            String str = k;
            String strF = zVar2.f(str);
            String str2 = f15638l;
            String strF2 = zVar2.f(str2);
            zVar2.g(str);
            zVar2.g(str2);
            this.f15647i = strF != null ? Long.parseLong(strF) : 0L;
            this.f15648j = strF2 != null ? Long.parseLong(strF2) : 0L;
            this.f15645g = zVar2.e();
            if (kotlin.jvm.internal.p.a(this.f15639a.f15617a, "https")) {
                String strB2 = i0Var.B(Long.MAX_VALUE);
                if (strB2.length() > 0) {
                    throw new IOException("expected \"\" but was \"" + strB2 + '\"');
                }
                this.f15646h = new y(!i0Var.i() ? a.a.M(i0Var.B(Long.MAX_VALUE)) : u0.SSL_3_0, m.f15739b.e(i0Var.B(Long.MAX_VALUE)), ib.c.x(a(i0Var)), new x(ib.c.x(a(i0Var)), 0));
            } else {
                this.f15646h = null;
            }
            o0Var.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                xc.d.L(o0Var, th);
                throw th2;
            }
        }
    }

    public static List a(xb.i0 i0Var) throws IOException {
        int iZ = k2.c.z(i0Var);
        if (iZ == -1) {
            return kotlin.collections.z.f19728i;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            ArrayList arrayList = new ArrayList(iZ);
            for (int i10 = 0; i10 < iZ; i10++) {
                String strB = i0Var.B(Long.MAX_VALUE);
                xb.j jVar = new xb.j();
                xb.m mVarE = v1.i.e(strB);
                if (mVarE == null) {
                    throw new IOException("Corrupt certificate in cache entry");
                }
                mVarE.u(jVar, mVarE.f());
                arrayList.add(certificateFactory.generateCertificate(new xb.h(jVar, 0)));
            }
            return arrayList;
        } catch (CertificateException e5) {
            throw new IOException(e5.getMessage());
        }
    }

    public static void b(xb.h0 h0Var, List list) throws IOException {
        try {
            h0Var.Y(list.size());
            h0Var.writeByte(10);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                h0Var.H(xb.a.a(v1.i.k(((Certificate) it.next()).getEncoded()).f22773i, xb.a.f22714a));
                h0Var.writeByte(10);
            }
        } catch (CertificateEncodingException e5) {
            throw new IOException(e5.getMessage());
        }
    }

    public final void c(p7 p7Var) {
        b0 b0Var = this.f15639a;
        y yVar = this.f15646h;
        a0 a0Var = this.f15645g;
        a0 a0Var2 = this.f15640b;
        xb.h0 h0Var = new xb.h0(p7Var.e(0));
        try {
            h0Var.H(b0Var.f15625i);
            h0Var.writeByte(10);
            h0Var.H(this.f15641c);
            h0Var.writeByte(10);
            h0Var.Y(a0Var2.size());
            h0Var.writeByte(10);
            int size = a0Var2.size();
            for (int i10 = 0; i10 < size; i10++) {
                h0Var.H(a0Var2.b(i10));
                h0Var.H(": ");
                h0Var.H(a0Var2.e(i10));
                h0Var.writeByte(10);
            }
            i0 i0Var = this.f15642d;
            int i11 = this.f15643e;
            String str = this.f15644f;
            StringBuilder sb2 = new StringBuilder();
            if (i0Var == i0.HTTP_1_0) {
                sb2.append("HTTP/1.0");
            } else {
                sb2.append("HTTP/1.1");
            }
            sb2.append(' ');
            sb2.append(i11);
            sb2.append(' ');
            sb2.append(str);
            h0Var.H(sb2.toString());
            h0Var.writeByte(10);
            h0Var.Y(a0Var.size() + 2);
            h0Var.writeByte(10);
            int size2 = a0Var.size();
            for (int i12 = 0; i12 < size2; i12++) {
                h0Var.H(a0Var.b(i12));
                h0Var.H(": ");
                h0Var.H(a0Var.e(i12));
                h0Var.writeByte(10);
            }
            h0Var.H(k);
            h0Var.H(": ");
            h0Var.Y(this.f15647i);
            h0Var.writeByte(10);
            h0Var.H(f15638l);
            h0Var.H(": ");
            h0Var.Y(this.f15648j);
            h0Var.writeByte(10);
            if (kotlin.jvm.internal.p.a(b0Var.f15617a, "https")) {
                h0Var.writeByte(10);
                h0Var.H(yVar.f15833b.f15757a);
                h0Var.writeByte(10);
                b(h0Var, yVar.a());
                b(h0Var, yVar.f15834c);
                h0Var.H(yVar.f15832a.f15826i);
                h0Var.writeByte(10);
            }
            h0Var.close();
        } finally {
        }
    }

    public e(p0 p0Var) {
        a0 a0Var;
        k0 k0Var = p0Var.f15780i;
        this.f15639a = k0Var.f15730a;
        a0 a0Var2 = p0Var.f15787r.f15780i.f15732c;
        a0 a0Var3 = p0Var.f15785p;
        Set setJ = k2.c.J(a0Var3);
        if (setJ.isEmpty()) {
            a0Var = ib.c.f16269b;
        } else {
            ArrayList arrayList = new ArrayList(20);
            int size = a0Var2.size();
            for (int i10 = 0; i10 < size; i10++) {
                String strB = a0Var2.b(i10);
                if (setJ.contains(strB)) {
                    String strE = a0Var2.e(i10);
                    t7.a.g(strB);
                    t7.a.h(strE, strB);
                    arrayList.add(strB);
                    arrayList.add(kotlin.text.o.L0(strE).toString());
                }
            }
            a0Var = new a0((String[]) arrayList.toArray(new String[0]));
        }
        this.f15640b = a0Var;
        this.f15641c = k0Var.f15731b;
        this.f15642d = p0Var.f15781l;
        this.f15643e = p0Var.f15783n;
        this.f15644f = p0Var.f15782m;
        this.f15645g = a0Var3;
        this.f15646h = p0Var.f15784o;
        this.f15647i = p0Var.f15790u;
        this.f15648j = p0Var.f15791v;
    }
}
