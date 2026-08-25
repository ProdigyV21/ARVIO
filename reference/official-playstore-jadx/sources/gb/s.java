package gb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class s implements t {
    @Override // gb.t
    public final List lookup(String str) throws UnknownHostException {
        try {
            return kotlin.collections.r.o0(InetAddress.getAllByName(str));
        } catch (NullPointerException e5) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
            unknownHostException.initCause(e5);
            throw unknownHostException;
        }
    }
}
