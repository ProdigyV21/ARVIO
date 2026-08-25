package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public final class k0 extends b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final e0 f18757j;
    public final boolean k;

    public k0(e0 e0Var, boolean z, a aVar, Spliterator spliterator) {
        super(aVar, spliterator);
        this.k = z;
        this.f18757j = e0Var;
    }

    public k0(k0 k0Var, Spliterator spliterator) {
        super(k0Var, spliterator);
        this.k = k0Var.k;
        this.f18757j = k0Var.f18757j;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new k0(this, spliterator);
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return this.f18757j.f18672b;
    }

    @Override // j$.util.stream.d
    public final Object a() {
        v3 v3Var = this.f18656a;
        f8 f8Var = (f8) this.f18757j.f18674d.get();
        v3Var.E0(this.f18657b, f8Var);
        Object obj = f8Var.get();
        if (this.k) {
            if (obj != null) {
                d dVar = this;
                while (dVar != null) {
                    d dVar2 = (d) dVar.getCompleter();
                    if (dVar2 != null && dVar2.f18659d != dVar) {
                        g();
                        return obj;
                    }
                    dVar = dVar2;
                }
                AtomicReference atomicReference = this.f18631h;
                while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
                }
                return obj;
            }
        } else if (obj != null) {
            AtomicReference atomicReference2 = this.f18631h;
            while (!atomicReference2.compareAndSet(null, obj) && atomicReference2.get() == null) {
            }
        }
        return null;
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        if (this.k) {
            k0 k0Var = (k0) this.f18659d;
            k0 k0Var2 = null;
            while (true) {
                if (k0Var != k0Var2) {
                    Object objI = k0Var.i();
                    if (objI != null && this.f18757j.f18673c.test(objI)) {
                        d(objI);
                        d dVar = this;
                        while (true) {
                            if (dVar != null) {
                                d dVar2 = (d) dVar.getCompleter();
                                if (dVar2 != null && dVar2.f18659d != dVar) {
                                    g();
                                    break;
                                }
                                dVar = dVar2;
                            } else {
                                AtomicReference atomicReference = this.f18631h;
                                while (!atomicReference.compareAndSet(null, objI) && atomicReference.get() == null) {
                                }
                            }
                        }
                    } else {
                        k0Var2 = k0Var;
                        k0Var = (k0) this.f18660e;
                    }
                } else {
                    break;
                }
            }
        }
        super.onCompletion(countedCompleter);
    }
}
