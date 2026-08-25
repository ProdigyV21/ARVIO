package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicReference f18631h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile boolean f18632i;

    public abstract Object h();

    public b(v3 v3Var, Spliterator spliterator) {
        super(v3Var, spliterator);
        this.f18631h = new AtomicReference(null);
    }

    public b(b bVar, Spliterator spliterator) {
        super(bVar, spliterator);
        this.f18631h = bVar.f18631h;
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void compute() {
        Object objH;
        Spliterator spliteratorTrySplit;
        Spliterator spliterator = this.f18657b;
        long jEstimateSize = spliterator.estimateSize();
        long jE = this.f18658c;
        if (jE == 0) {
            jE = d.e(jEstimateSize);
            this.f18658c = jE;
        }
        AtomicReference atomicReference = this.f18631h;
        boolean z = false;
        b bVar = this;
        while (true) {
            objH = atomicReference.get();
            if (objH != null) {
                break;
            }
            boolean z5 = bVar.f18632i;
            if (!z5) {
                CountedCompleter<?> completer = bVar.getCompleter();
                while (true) {
                    b bVar2 = (b) ((d) completer);
                    if (z5 || bVar2 == null) {
                        break;
                    }
                    z5 = bVar2.f18632i;
                    completer = bVar2.getCompleter();
                }
            }
            if (z5) {
                objH = bVar.h();
                break;
            }
            if (jEstimateSize <= jE || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                break;
            }
            b bVar3 = (b) bVar.c(spliteratorTrySplit);
            bVar.f18659d = bVar3;
            b bVar4 = (b) bVar.c(spliterator);
            bVar.f18660e = bVar4;
            bVar.setPendingCount(1);
            if (z) {
                spliterator = spliteratorTrySplit;
                bVar = bVar3;
                bVar3 = bVar4;
            } else {
                bVar = bVar4;
            }
            z = !z;
            bVar3.fork();
            jEstimateSize = spliterator.estimateSize();
        }
        objH = bVar.a();
        bVar.d(objH);
        bVar.tryComplete();
    }

    @Override // j$.util.stream.d
    public final void d(Object obj) {
        if (!b()) {
            this.f18661f = obj;
        } else if (obj != null) {
            AtomicReference atomicReference = this.f18631h;
            while (!atomicReference.compareAndSet(null, obj) && atomicReference.get() == null) {
            }
        }
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter, java.util.concurrent.ForkJoinTask
    public final Object getRawResult() {
        return i();
    }

    public final Object i() {
        if (b()) {
            Object obj = this.f18631h.get();
            return obj == null ? h() : obj;
        }
        return this.f18661f;
    }

    public void f() {
        this.f18632i = true;
    }

    public final void g() {
        b bVar = this;
        for (b bVar2 = (b) ((d) getCompleter()); bVar2 != null; bVar2 = (b) ((d) bVar2.getCompleter())) {
            if (bVar2.f18659d == bVar) {
                b bVar3 = (b) bVar2.f18660e;
                if (!bVar3.f18632i) {
                    bVar3.f();
                }
            }
            bVar = bVar2;
        }
    }
}
