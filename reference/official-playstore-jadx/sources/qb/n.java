package qb;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes5.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile n f21385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f21386b;

    /* JADX WARN: Removed duplicated region for block: B:34:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d3  */
    static {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.n.<clinit>():void");
    }

    public static void i(String str, Throwable th, int i10) {
        f21386b.log(i10 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public static /* synthetic */ void j(n nVar, String str, int i10) {
        int i11 = (i10 & 2) != 0 ? 4 : 5;
        nVar.getClass();
        i(str, null, i11);
    }

    public t7.a b(X509TrustManager x509TrustManager) {
        return new ub.a(c(x509TrustManager));
    }

    public ub.d c(X509TrustManager x509TrustManager) {
        X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
        return new ub.b((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public void e(Socket socket, InetSocketAddress inetSocketAddress, int i10) throws IOException {
        socket.connect(inetSocketAddress, i10);
    }

    public String f(SSLSocket sSLSocket) {
        return null;
    }

    public Object g() {
        if (f21386b.isLoggable(Level.FINE)) {
            return new Throwable("response.body().close()");
        }
        return null;
    }

    public boolean h(String str) {
        return true;
    }

    public void k(Object obj, String str) {
        if (obj == null) {
            str = str.concat(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        i(str, (Throwable) obj, 5);
    }

    public SSLContext l() {
        return SSLContext.getInstance("TLS");
    }

    public SSLSocketFactory m(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContextL = l();
            sSLContextL.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContextL.getSocketFactory();
        } catch (GeneralSecurityException e5) {
            throw new AssertionError("No System TLS: " + e5, e5);
        }
    }

    public X509TrustManager n() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        throw new IllegalStateException("Unexpected default trust managers: ".concat(Arrays.toString(trustManagers)).toString());
    }

    public final String toString() {
        return getClass().getSimpleName();
    }

    public void a(SSLSocket sSLSocket) {
    }

    public void d(SSLSocket sSLSocket, String str, List list) {
    }
}
