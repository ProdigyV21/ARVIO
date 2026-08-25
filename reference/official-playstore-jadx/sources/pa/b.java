package pa;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21254a = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_consensus");

    @q7.w
    private volatile Object _consensus = a.f21248a;

    @Override // pa.s
    public final Object a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f21254a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        c2.a aVar = a.f21248a;
        if (obj2 == aVar) {
            c2.a aVarC = c(obj);
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 == aVar) {
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, aVar, aVarC)) {
                        obj2 = aVarC;
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != aVar) {
                        obj2 = atomicReferenceFieldUpdater.get(this);
                        break;
                    }
                }
            }
        }
        b(obj, obj2);
        return obj2;
    }

    public abstract void b(Object obj, Object obj2);

    public abstract c2.a c(Object obj);
}
