package ka;

import d7.j;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f0 extends d7.a implements d7.g {
    public static final e0 Key = new e0(d7.f.f14687i, d0.f19570i);

    public f0() {
        super(d7.f.f14687i);
    }

    public abstract void dispatch(d7.j jVar, Runnable runnable);

    public void dispatchYield(d7.j jVar, Runnable runnable) {
        dispatch(jVar, runnable);
    }

    @Override // d7.a, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) d7.e.a(this, bVar);
    }

    @Override // d7.g
    public final <T> d7.d<T> interceptContinuation(d7.d<? super T> dVar) {
        return new pa.g(this, dVar);
    }

    public boolean isDispatchNeeded(d7.j jVar) {
        return true;
    }

    public f0 limitedParallelism(int i10) {
        pa.a.a(i10);
        return new pa.i(this, i10);
    }

    @Override // d7.a, d7.j
    public d7.j minusKey(j.b<?> bVar) {
        return d7.e.b(this, bVar);
    }

    @Override // d7.g
    public final void releaseInterceptedContinuation(d7.d<?> dVar) {
        pa.g gVar = (pa.g) dVar;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = pa.g.f21268r;
        while (atomicReferenceFieldUpdater.get(gVar) == pa.a.f21251d) {
        }
        Object obj = atomicReferenceFieldUpdater.get(gVar);
        l lVar = obj instanceof l ? (l) obj : null;
        if (lVar != null) {
            lVar.o();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + m0.j(this);
    }

    @x6.e
    public final f0 plus(f0 f0Var) {
        return f0Var;
    }
}
