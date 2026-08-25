package lb;

import com.google.common.util.concurrent.r0;
import gb.h0;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import qb.n;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final gb.j f20105i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile AtomicInteger f20106l = new AtomicInteger(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f20107m;

    public f(i iVar, gb.j jVar) {
        this.f20107m = iVar;
        this.f20105i = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        h0 h0Var;
        String strConcat = "OkHttp ".concat(this.f20107m.f20111l.f15730a.h());
        i iVar = this.f20107m;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(strConcat);
        try {
            iVar.f20115p.i();
            boolean z = false;
            try {
                try {
                } catch (Throwable th) {
                    iVar.f20110i.f15700i.s(this);
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.f20105i.onResponse(iVar, iVar.f());
                h0Var = iVar.f20110i;
            } catch (IOException e6) {
                e = e6;
                z = true;
                if (z) {
                    n nVar = n.f21385a;
                    n nVar2 = n.f21385a;
                    String str = "Callback failure for " + i.a(iVar);
                    nVar2.getClass();
                    n.i(str, e, 4);
                } else {
                    this.f20105i.onFailure(iVar, e);
                }
                h0Var = iVar.f20110i;
            } catch (Throwable th3) {
                th = th3;
                z = true;
                iVar.cancel();
                if (!z) {
                    IOException iOException = new IOException("canceled due to " + th);
                    r0.a(iOException, th);
                    this.f20105i.onFailure(iVar, iOException);
                }
                throw th;
            }
            h0Var.f15700i.s(this);
        } finally {
            threadCurrentThread.setName(name);
        }
    }
}
