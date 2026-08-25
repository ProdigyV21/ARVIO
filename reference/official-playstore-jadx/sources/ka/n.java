package ka;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends w1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final l f19621l;

    public n(l lVar) {
        this.f19621l = lVar;
    }

    @Override // r7.l
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return x6.t0.f22605a;
    }

    @Override // ka.y1
    public final void invoke(Throwable th) {
        f2 f2VarC = c();
        l lVar = this.f19621l;
        Throwable thQ = lVar.q(f2VarC);
        if (lVar.v()) {
            pa.g gVar = (pa.g) lVar.f19608n;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = pa.g.f21268r;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(gVar);
                c2.a aVar = pa.a.f21251d;
                if (!kotlin.jvm.internal.p.a(obj, aVar)) {
                    if (!(obj instanceof Throwable)) {
                        while (!atomicReferenceFieldUpdater.compareAndSet(gVar, obj, null)) {
                            if (atomicReferenceFieldUpdater.get(gVar) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    return;
                }
                while (!atomicReferenceFieldUpdater.compareAndSet(gVar, aVar, thQ)) {
                    if (atomicReferenceFieldUpdater.get(gVar) != aVar) {
                        break;
                    }
                }
                return;
            }
        }
        lVar.cancel(thQ);
        if (lVar.v()) {
            return;
        }
        lVar.o();
    }
}
