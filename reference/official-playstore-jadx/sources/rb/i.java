package rb;

import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements k {
    @Override // rb.k
    public final boolean a(SSLSocket sSLSocket) {
        return qb.h.f21371d && Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // rb.k
    public final m b(SSLSocket sSLSocket) {
        return new j();
    }
}
