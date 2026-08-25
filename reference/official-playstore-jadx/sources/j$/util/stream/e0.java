package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class e0 implements e8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f18672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Predicate f18673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Supplier f18674d;

    public e0(boolean z, z6 z6Var, Object obj, Predicate predicate, Supplier supplier) {
        this.f18671a = (z ? 0 : y6.f18974r) | y6.f18977u;
        this.f18672b = obj;
        this.f18673c = predicate;
        this.f18674d = supplier;
    }

    @Override // j$.util.stream.e8
    public final int t() {
        return this.f18671a;
    }

    @Override // j$.util.stream.e8
    public final Object e(a aVar, Spliterator spliterator) {
        f8 f8Var = (f8) this.f18674d.get();
        aVar.E0(spliterator, f8Var);
        Object obj = f8Var.get();
        return obj != null ? obj : this.f18672b;
    }

    @Override // j$.util.stream.e8
    public final Object h(v3 v3Var, Spliterator spliterator) {
        a aVar = (a) v3Var;
        return new k0(this, y6.ORDERED.r(aVar.f18607m), aVar, spliterator).invoke();
    }
}
