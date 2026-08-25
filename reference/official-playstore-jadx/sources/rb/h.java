package rb;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f21457a = new g();

    @Override // rb.m
    public final boolean a(SSLSocket sSLSocket) {
        return false;
    }

    @Override // rb.m
    public final boolean b() {
        boolean z = qb.e.f21368d;
        return qb.e.f21368d;
    }

    @Override // rb.m
    public final String c(SSLSocket sSLSocket) {
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null ? true : applicationProtocol.equals("")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // rb.m
    public final void d(SSLSocket sSLSocket, String str, List list) {
        if (a(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            qb.n nVar = qb.n.f21385a;
            parameters.setApplicationProtocols((String[]) io.sentry.util.l.f(list).toArray(new String[0]));
            bCSSLSocket.setParameters(parameters);
        }
    }
}
