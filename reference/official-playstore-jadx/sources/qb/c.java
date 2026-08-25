package qb;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import androidx.appcompat.widget.v3;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f21365e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f21366c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v3 f21367d;

    static {
        boolean z = false;
        if (io.sentry.util.l.k() && Build.VERSION.SDK_INT < 30) {
            z = true;
        }
        f21365e = z;
    }

    public c() throws NoSuchMethodException {
        rb.n nVar;
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketImpl"));
            Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketFactoryImpl"));
            Class.forName("com.android.org.conscrypt".concat(".SSLParametersImpl"));
            nVar = new rb.n(cls);
        } catch (Exception e5) {
            n.f21385a.getClass();
            n.i("unable to load android socket classes", e5, 5);
            nVar = null;
        }
        ArrayList arrayListX = r.X(new rb.m[]{nVar, new rb.l(rb.f.f21451f), new rb.l(rb.j.f21458a), new rb.l(rb.h.f21457a)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListX) {
            if (((rb.m) obj).b()) {
                arrayList.add(obj);
            }
        }
        this.f21366c = arrayList;
        try {
            Class<?> cls2 = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls2.getMethod("get", null);
            method = cls2.getMethod(TtmlNode.TEXT_EMPHASIS_MARK_OPEN, String.class);
            method2 = cls2.getMethod("warnIfOpen", null);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        this.f21367d = new v3(method3, method, method2);
    }

    @Override // qb.n
    public final t7.a b(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        rb.b bVar = x509TrustManagerExtensions != null ? new rb.b(x509TrustManager, x509TrustManagerExtensions) : null;
        return bVar != null ? bVar : super.b(x509TrustManager);
    }

    @Override // qb.n
    public final ub.d c(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // qb.n
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object next;
        Iterator it = this.f21366c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((rb.m) next).a(sSLSocket)) {
                    break;
                }
            }
        }
        rb.m mVar = (rb.m) next;
        if (mVar != null) {
            mVar.d(sSLSocket, str, list);
        }
    }

    @Override // qb.n
    public final void e(Socket socket, InetSocketAddress inetSocketAddress, int i10) throws IOException {
        try {
            socket.connect(inetSocketAddress, i10);
        } catch (ClassCastException e5) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e5;
            }
            throw new IOException("Exception in connect", e5);
        }
    }

    @Override // qb.n
    public final String f(SSLSocket sSLSocket) {
        Object next;
        Iterator it = this.f21366c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((rb.m) next).a(sSLSocket)) {
                break;
            }
        }
        rb.m mVar = (rb.m) next;
        if (mVar != null) {
            return mVar.c(sSLSocket);
        }
        return null;
    }

    @Override // qb.n
    public final Object g() {
        v3 v3Var = this.f21367d;
        Method method = v3Var.f1798a;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(null, null);
                v3Var.f1799b.invoke(objInvoke, "response.body().close()");
                return objInvoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // qb.n
    public final boolean h(String str) {
        return Build.VERSION.SDK_INT >= 24 ? NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str) : NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    @Override // qb.n
    public final void k(Object obj, String str) {
        v3 v3Var = this.f21367d;
        v3Var.getClass();
        if (obj != null) {
            try {
                v3Var.f1800c.invoke(obj, null);
                return;
            } catch (Exception unused) {
            }
        }
        n.j(this, str, 4);
    }
}
