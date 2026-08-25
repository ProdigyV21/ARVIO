package gb;

import j$.util.Objects;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f15605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SocketFactory f15606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SSLSocketFactory f15607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HostnameVerifier f15608d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k f15609e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f15610f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ProxySelector f15611g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b0 f15612h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f15613i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f15614j;

    public a(String str, int i10, t tVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, k kVar, u uVar, List list, List list2, ProxySelector proxySelector) {
        this.f15605a = tVar;
        this.f15606b = socketFactory;
        this.f15607c = sSLSocketFactory;
        this.f15608d = hostnameVerifier;
        this.f15609e = kVar;
        this.f15610f = uVar;
        this.f15611g = proxySelector;
        androidx.tv.foundation.lazy.list.k kVar2 = new androidx.tv.foundation.lazy.list.k(1);
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            kVar2.f5411e = "http";
        } else {
            if (!str2.equalsIgnoreCase("https")) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            kVar2.f5411e = "https";
        }
        String strX = f4.f.x(u.g(str, 0, 0, 7));
        if (strX == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        kVar2.f5414h = strX;
        if (1 > i10 || i10 >= 65536) {
            throw new IllegalArgumentException(a0.c.i(i10, "unexpected port: ").toString());
        }
        kVar2.f5408b = i10;
        this.f15612h = kVar2.d();
        this.f15613i = ib.c.x(list);
        this.f15614j = ib.c.x(list2);
    }

    public final boolean a(a aVar) {
        return kotlin.jvm.internal.p.a(this.f15605a, aVar.f15605a) && kotlin.jvm.internal.p.a(this.f15610f, aVar.f15610f) && kotlin.jvm.internal.p.a(this.f15613i, aVar.f15613i) && kotlin.jvm.internal.p.a(this.f15614j, aVar.f15614j) && kotlin.jvm.internal.p.a(this.f15611g, aVar.f15611g) && kotlin.jvm.internal.p.a(this.f15607c, aVar.f15607c) && kotlin.jvm.internal.p.a(this.f15608d, aVar.f15608d) && kotlin.jvm.internal.p.a(this.f15609e, aVar.f15609e) && this.f15612h.f15621e == aVar.f15612h.f15621e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return kotlin.jvm.internal.p.a(this.f15612h, aVar.f15612h) && a(aVar);
    }

    public final int hashCode() {
        return Objects.hashCode(this.f15609e) + ((Objects.hashCode(this.f15608d) + ((Objects.hashCode(this.f15607c) + ((this.f15611g.hashCode() + androidx.compose.material3.d.e(this.f15614j, androidx.compose.material3.d.e(this.f15613i, (this.f15610f.hashCode() + ((this.f15605a.hashCode() + androidx.compose.foundation.c.c(527, 31, this.f15612h.f15625i)) * 31)) * 31, 31), 31)) * 961)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Address{");
        b0 b0Var = this.f15612h;
        sb2.append(b0Var.f15620d);
        sb2.append(':');
        sb2.append(b0Var.f15621e);
        sb2.append(", ");
        sb2.append("proxySelector=" + this.f15611g);
        sb2.append('}');
        return sb2.toString();
    }
}
