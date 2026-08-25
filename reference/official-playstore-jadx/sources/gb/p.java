package gb;

import androidx.appcompat.widget.g4;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes5.dex */
public final class p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final p f15774e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p f15775f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f15776a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15777b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f15778c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f15779d;

    static {
        m mVar = m.f15754r;
        m mVar2 = m.f15755s;
        m mVar3 = m.f15756t;
        m mVar4 = m.f15748l;
        m mVar5 = m.f15750n;
        m mVar6 = m.f15749m;
        m mVar7 = m.f15751o;
        m mVar8 = m.f15753q;
        m mVar9 = m.f15752p;
        m[] mVarArr = {mVar, mVar2, mVar3, mVar4, mVar5, mVar6, mVar7, mVar8, mVar9};
        m[] mVarArr2 = {mVar, mVar2, mVar3, mVar4, mVar5, mVar6, mVar7, mVar8, mVar9, m.f15747j, m.k, m.f15745h, m.f15746i, m.f15743f, m.f15744g, m.f15742e};
        g4 g4Var = new g4();
        g4Var.b((m[]) Arrays.copyOf(mVarArr, 9));
        u0 u0Var = u0.TLS_1_3;
        u0 u0Var2 = u0.TLS_1_2;
        g4Var.d(u0Var, u0Var2);
        g4Var.f1590b = true;
        g4Var.a();
        g4 g4Var2 = new g4();
        g4Var2.b((m[]) Arrays.copyOf(mVarArr2, 16));
        g4Var2.d(u0Var, u0Var2);
        g4Var2.f1590b = true;
        f15774e = g4Var2.a();
        g4 g4Var3 = new g4();
        g4Var3.b((m[]) Arrays.copyOf(mVarArr2, 16));
        g4Var3.d(u0Var, u0Var2, u0.TLS_1_1, u0.TLS_1_0);
        g4Var3.f1590b = true;
        g4Var3.a();
        f15775f = new p(false, false, null, null);
    }

    public p(boolean z, boolean z5, String[] strArr, String[] strArr2) {
        this.f15776a = z;
        this.f15777b = z5;
        this.f15778c = strArr;
        this.f15779d = strArr2;
    }

    public final List a() {
        String[] strArr = this.f15778c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(m.f15739b.e(str));
        }
        return kotlin.collections.x.c1(arrayList);
    }

    public final boolean b(SSLSocket sSLSocket) {
        if (!this.f15776a) {
            return false;
        }
        String[] strArr = this.f15779d;
        if (strArr != null && !ib.c.j(strArr, sSLSocket.getEnabledProtocols(), a7.c.f130l)) {
            return false;
        }
        String[] strArr2 = this.f15778c;
        return strArr2 == null || ib.c.j(strArr2, sSLSocket.getEnabledCipherSuites(), m.f15740c);
    }

    public final List c() {
        String[] strArr = this.f15779d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a.a.M(str));
        }
        return kotlin.collections.x.c1(arrayList);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        p pVar = (p) obj;
        boolean z = pVar.f15776a;
        boolean z5 = this.f15776a;
        if (z5 != z) {
            return false;
        }
        if (z5) {
            return Arrays.equals(this.f15778c, pVar.f15778c) && Arrays.equals(this.f15779d, pVar.f15779d) && this.f15777b == pVar.f15777b;
        }
        return true;
    }

    public final int hashCode() {
        if (!this.f15776a) {
            return 17;
        }
        String[] strArr = this.f15778c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.f15779d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.f15777b ? 1 : 0);
    }

    public final String toString() {
        if (!this.f15776a) {
            return "ConnectionSpec()";
        }
        StringBuilder sb2 = new StringBuilder("ConnectionSpec(cipherSuites=");
        sb2.append(Objects.toString(a(), "[all enabled]"));
        sb2.append(", tlsVersions=");
        sb2.append(Objects.toString(c(), "[all enabled]"));
        sb2.append(", supportsTlsExtensions=");
        return a0.c.r(sb2, this.f15777b, ')');
    }
}
