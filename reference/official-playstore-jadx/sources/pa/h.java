package pa;

import ka.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21273i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f21274l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f21275m;

    public h(retrofit2.v vVar, Exception exc) {
        this.f21274l = vVar;
        this.f21275m = exc;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f21273i) {
            case 0:
                i iVar = (i) this.f21275m;
                int i10 = 0;
                while (true) {
                    try {
                        ((Runnable) this.f21274l).run();
                    } catch (Throwable th) {
                        h0.a(d7.k.f14688i, th);
                    }
                    Runnable runnableQ0 = iVar.q0();
                    if (runnableQ0 == null) {
                        break;
                    } else {
                        this.f21274l = runnableQ0;
                        i10++;
                        if (i10 >= 16 && iVar.f21277i.isDispatchNeeded(iVar)) {
                            iVar.f21277i.dispatch(iVar, this);
                            break;
                        }
                    }
                    break;
                }
                break;
            default:
                t7.a.A((retrofit2.v) this.f21274l).resumeWith(new x6.c0((Exception) this.f21275m));
                break;
        }
    }

    public h(i iVar, Runnable runnable) {
        this.f21275m = iVar;
        this.f21274l = runnable;
    }
}
