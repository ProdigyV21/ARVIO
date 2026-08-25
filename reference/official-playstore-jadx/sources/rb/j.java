package rb;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f21458a = new i();

    @Override // rb.m
    public final boolean a(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // rb.m
    public final boolean b() {
        boolean z = qb.h.f21371d;
        return qb.h.f21371d;
    }

    @Override // rb.m
    public final String c(SSLSocket sSLSocket) {
        if (a(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // rb.m
    public final void d(SSLSocket sSLSocket, String str, List list) {
        if (a(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            qb.n nVar = qb.n.f21385a;
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) io.sentry.util.l.f(list).toArray(new String[0]));
        }
    }
}
