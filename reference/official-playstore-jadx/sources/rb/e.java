package rb;

import javax.net.ssl.SSLSocket;
import kotlin.text.u;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements k {
    @Override // rb.k
    public final boolean a(SSLSocket sSLSocket) {
        return u.P(sSLSocket.getClass().getName(), "com.google.android.gms.org.conscrypt.", false);
    }

    @Override // rb.k
    public final m b(SSLSocket sSLSocket) {
        Class<?> cls = sSLSocket.getClass();
        Class<?> superclass = cls;
        while (!superclass.getSimpleName().equals("OpenSSLSocketImpl")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
            }
        }
        return new f(superclass);
    }
}
