package d7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import x6.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements d, f7.d {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f14689l = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "result");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f14690i;
    private volatile Object result;

    public m(d dVar, e7.a aVar) {
        this.f14690i = dVar;
        this.result = aVar;
    }

    public final Object b() throws Throwable {
        e7.a aVar = e7.a.f15033i;
        Object obj = this.result;
        e7.a aVar2 = e7.a.f15034l;
        if (obj == aVar2) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f14689l;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, aVar2, aVar)) {
                if (atomicReferenceFieldUpdater.get(this) != aVar2) {
                    obj = this.result;
                }
            }
            return aVar;
        }
        if (obj == e7.a.f15035m) {
            return aVar;
        }
        if (obj instanceof c0) {
            throw ((c0) obj).f22576i;
        }
        return obj;
    }

    @Override // f7.d
    public final f7.d getCallerFrame() {
        d dVar = this.f14690i;
        if (dVar instanceof f7.d) {
            return (f7.d) dVar;
        }
        return null;
    }

    @Override // d7.d
    public final j getContext() {
        return this.f14690i.getContext();
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this.result;
            e7.a aVar = e7.a.f15034l;
            if (obj2 == aVar) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f14689l;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, aVar, obj)) {
                    if (atomicReferenceFieldUpdater.get(this) != aVar) {
                        break;
                    }
                }
                return;
            }
            e7.a aVar2 = e7.a.f15033i;
            if (obj2 != aVar2) {
                throw new IllegalStateException("Already resumed");
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f14689l;
            e7.a aVar3 = e7.a.f15035m;
            while (!atomicReferenceFieldUpdater2.compareAndSet(this, aVar2, aVar3)) {
                if (atomicReferenceFieldUpdater2.get(this) != aVar2) {
                    break;
                }
            }
            this.f14690i.resumeWith(obj);
            return;
        }
    }

    public final String toString() {
        return "SafeContinuation for " + this.f14690i;
    }

    public m(d dVar) {
        this(dVar, e7.a.f15034l);
    }
}
