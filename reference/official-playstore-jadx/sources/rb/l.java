package rb;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f21459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m f21460b;

    public l(k kVar) {
        this.f21459a = kVar;
    }

    @Override // rb.m
    public final boolean a(SSLSocket sSLSocket) {
        return this.f21459a.a(sSLSocket);
    }

    @Override // rb.m
    public final boolean b() {
        return true;
    }

    @Override // rb.m
    public final String c(SSLSocket sSLSocket) {
        m mVarE = e(sSLSocket);
        if (mVarE != null) {
            return mVarE.c(sSLSocket);
        }
        return null;
    }

    @Override // rb.m
    public final void d(SSLSocket sSLSocket, String str, List list) {
        m mVarE = e(sSLSocket);
        if (mVarE != null) {
            mVarE.d(sSLSocket, str, list);
        }
    }

    public final synchronized m e(SSLSocket sSLSocket) {
        try {
            if (this.f21460b == null && this.f21459a.a(sSLSocket)) {
                this.f21460b = this.f21459a.b(sSLSocket);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f21460b;
    }
}
