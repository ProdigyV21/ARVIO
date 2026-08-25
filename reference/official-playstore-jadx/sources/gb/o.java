package gb;

import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f15761a;

    public o(int i10, long j10, TimeUnit timeUnit) {
        this.f15761a = new io.sentry.android.core.internal.util.g(kb.e.f19674h, i10, j10, timeUnit);
    }

    public final void a() {
        Socket socket;
        io.sentry.android.core.internal.util.g gVar = this.f15761a;
        Iterator it = ((ConcurrentLinkedQueue) gVar.f16609e).iterator();
        while (it.hasNext()) {
            lb.k kVar = (lb.k) it.next();
            synchronized (kVar) {
                if (kVar.f20138p.isEmpty()) {
                    it.remove();
                    kVar.f20133j = true;
                    socket = kVar.f20127d;
                } else {
                    socket = null;
                }
            }
            if (socket != null) {
                ib.c.e(socket);
            }
        }
        if (((ConcurrentLinkedQueue) gVar.f16609e).isEmpty()) {
            ((kb.b) gVar.f16607c).a();
        }
    }
}
