package ka;

import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Thread f19589n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final c1 f19590o;

    public g(d7.j jVar, Thread thread, c1 c1Var) {
        super(jVar, true);
        this.f19589n = thread;
        this.f19590o = c1Var;
    }

    @Override // ka.f2
    public final void g(Object obj) {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.f19589n;
        if (kotlin.jvm.internal.p.a(threadCurrentThread, thread)) {
            return;
        }
        LockSupport.unpark(thread);
    }
}
