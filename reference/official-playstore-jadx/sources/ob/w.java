package ob;

import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends xb.e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x f20964m;

    public w(x xVar) {
        this.f20964m = xVar;
    }

    @Override // xb.e
    public final void k() {
        this.f20964m.e(9);
        o oVar = this.f20964m.f20966b;
        synchronized (oVar) {
            long j10 = oVar.f20935y;
            long j11 = oVar.x;
            if (j10 < j11) {
                return;
            }
            oVar.x = j11 + 1;
            oVar.z = System.nanoTime() + ((long) 1000000000);
            oVar.f20929r.c(new jb.g(a0.c.p(new StringBuilder(), oVar.f20924m, " ping"), oVar, 3), 0L);
        }
    }

    public final void l() {
        if (j()) {
            throw new SocketTimeoutException("timeout");
        }
    }
}
