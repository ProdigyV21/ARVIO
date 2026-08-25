package qb;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements ub.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X509TrustManager f21363a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f21364b;

    public b(X509TrustManager x509TrustManager, Method method) {
        this.f21363a = x509TrustManager;
        this.f21364b = method;
    }

    @Override // ub.d
    public final X509Certificate a(X509Certificate x509Certificate) {
        try {
            return ((TrustAnchor) this.f21364b.invoke(this.f21363a, x509Certificate)).getTrustedCert();
        } catch (IllegalAccessException e5) {
            throw new AssertionError("unable to get issues and signature", e5);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return p.a(this.f21363a, bVar.f21363a) && p.a(this.f21364b, bVar.f21364b);
    }

    public final int hashCode() {
        return this.f21364b.hashCode() + (this.f21363a.hashCode() * 31);
    }

    public final String toString() {
        return "CustomTrustRootIndex(trustManager=" + this.f21363a + ", findByIssuerAndSignatureMethod=" + this.f21364b + ')';
    }
}
