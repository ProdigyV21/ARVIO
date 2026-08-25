package pa;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21285a = AtomicReferenceFieldUpdater.newUpdater(n.class, Object.class, "_cur");

    @q7.w
    private volatile Object _cur = new p(8, false);

    public final boolean a(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21285a;
            p pVar = (p) atomicReferenceFieldUpdater.get(this);
            int iA = pVar.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                p pVarC = pVar.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, pVar, pVarC) && atomicReferenceFieldUpdater.get(this) == pVar) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21285a;
            p pVar = (p) atomicReferenceFieldUpdater.get(this);
            if (pVar.b()) {
                return;
            }
            p pVarC = pVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, pVar, pVarC) && atomicReferenceFieldUpdater.get(this) == pVar) {
            }
        }
    }

    public final int c() {
        p pVar = (p) f21285a.get(this);
        pVar.getClass();
        long j10 = p.f21288f.get(pVar);
        return (((int) ((j10 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j10))) & 1073741823;
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21285a;
            p pVar = (p) atomicReferenceFieldUpdater.get(this);
            Object objD = pVar.d();
            if (objD != p.f21289g) {
                return objD;
            }
            p pVarC = pVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, pVar, pVarC) && atomicReferenceFieldUpdater.get(this) == pVar) {
            }
        }
    }
}
