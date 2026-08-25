package rb;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends t7.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X509TrustManager f21446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X509TrustManagerExtensions f21447b;

    public b(X509TrustManager x509TrustManager, X509TrustManagerExtensions x509TrustManagerExtensions) {
        this.f21446a = x509TrustManager;
        this.f21447b = x509TrustManagerExtensions;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof b) && ((b) obj).f21446a == this.f21446a;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f21446a);
    }

    @Override // t7.a
    public final List i(String str, List list) throws SSLPeerUnverifiedException {
        try {
            return this.f21447b.checkServerTrusted((X509Certificate[]) list.toArray(new X509Certificate[0]), "RSA", str);
        } catch (CertificateException e5) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e5.getMessage());
            sSLPeerUnverifiedException.initCause(e5);
            throw sSLPeerUnverifiedException;
        }
    }
}
