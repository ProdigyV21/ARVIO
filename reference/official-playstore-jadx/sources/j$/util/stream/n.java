package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class n extends b5 {
    public static k2 T0(v3 v3Var, Spliterator spliterator) {
        j$.time.c cVar = new j$.time.c(24);
        j$.time.c cVar2 = new j$.time.c(25);
        j$.time.c cVar3 = new j$.time.c(26);
        Objects.requireNonNull(cVar);
        Objects.requireNonNull(cVar2);
        Objects.requireNonNull(cVar3);
        return new k2((Collection) new a4(z6.REFERENCE, cVar3, cVar2, cVar, 3).h(v3Var, spliterator));
    }

    @Override // j$.util.stream.a
    public final g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        a aVar = (a) v3Var;
        if (y6.DISTINCT.r(aVar.f18607m)) {
            return v3Var.j0(spliterator, false, intFunction);
        }
        if (y6.ORDERED.r(aVar.f18607m)) {
            return T0(v3Var, spliterator);
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        j$.util.concurrent.t tVar = new j$.util.concurrent.t(4, atomicBoolean, concurrentHashMap);
        Objects.requireNonNull(tVar);
        new p0(tVar, false).a(v3Var, spliterator);
        Collection collectionKeySet = concurrentHashMap.keySet();
        if (atomicBoolean.get()) {
            HashSet hashSet = new HashSet(collectionKeySet);
            hashSet.add(null);
            collectionKeySet = hashSet;
        }
        return new k2(collectionKeySet);
    }

    @Override // j$.util.stream.a
    public final Spliterator N0(a aVar, Spliterator spliterator) {
        if (y6.DISTINCT.r(aVar.f18607m)) {
            return aVar.G0(spliterator);
        }
        if (y6.ORDERED.r(aVar.f18607m)) {
            return T0(aVar, spliterator).spliterator();
        }
        return new h7(aVar.G0(spliterator), new ConcurrentHashMap());
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        Objects.requireNonNull(l5Var);
        if (y6.DISTINCT.r(i10)) {
            return l5Var;
        }
        if (y6.SORTED.r(i10)) {
            return new l(l5Var);
        }
        return new m(l5Var);
    }
}
