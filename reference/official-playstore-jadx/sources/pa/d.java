package pa;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21258i = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_next");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21259l = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_prev");

    @q7.w
    private volatile Object _next;

    @q7.w
    private volatile Object _prev;

    public d(w wVar) {
        this._prev = wVar;
    }

    public final void a() {
        f21259l.lazySet(this, null);
    }

    public final d b() {
        Object obj = f21258i.get(this);
        if (obj == a.f21249b) {
            return null;
        }
        return (d) obj;
    }

    public abstract boolean c();

    public final void d() {
        d dVarB;
        if (b() == null) {
            return;
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21259l;
            d dVar = (d) atomicReferenceFieldUpdater.get(this);
            while (dVar != null && dVar.c()) {
                dVar = (d) atomicReferenceFieldUpdater.get(dVar);
            }
            d dVarB2 = b();
            while (dVarB2.c() && (dVarB = dVarB2.b()) != null) {
                dVarB2 = dVarB;
            }
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(dVarB2);
                d dVar2 = ((d) obj) == null ? null : dVar;
                while (!atomicReferenceFieldUpdater.compareAndSet(dVarB2, obj, dVar2)) {
                    if (atomicReferenceFieldUpdater.get(dVarB2) != obj) {
                        break;
                    }
                }
            }
            if (dVar != null) {
                f21258i.set(dVar, dVarB2);
            }
            if (!dVarB2.c() || dVarB2.b() == null) {
                if (dVar == null || !dVar.c()) {
                    return;
                }
            }
        }
    }
}
