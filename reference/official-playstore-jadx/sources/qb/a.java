package qb;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f21361d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f21362c;

    static {
        f21361d = io.sentry.util.l.k() && Build.VERSION.SDK_INT >= 29;
    }

    public a() {
        ArrayList arrayListX = r.X(new rb.m[]{(!io.sentry.util.l.k() || Build.VERSION.SDK_INT < 29) ? null : new rb.a(), new rb.l(rb.f.f21451f), new rb.l(rb.j.f21458a), new rb.l(rb.h.f21457a)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListX) {
            if (((rb.m) obj).b()) {
                arrayList.add(obj);
            }
        }
        this.f21362c = arrayList;
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
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object next;
        Iterator it = this.f21362c.iterator();
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
    public final String f(SSLSocket sSLSocket) {
        Object next;
        Iterator it = this.f21362c.iterator();
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
    public final boolean h(String str) {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}
