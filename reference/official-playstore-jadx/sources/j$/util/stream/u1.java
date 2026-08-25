package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class u1 extends b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final j$.util.concurrent.t f18903j;

    public u1(j$.util.concurrent.t tVar, a aVar, Spliterator spliterator) {
        super(aVar, spliterator);
        this.f18903j = tVar;
    }

    public u1(u1 u1Var, Spliterator spliterator) {
        super(u1Var, spliterator);
        this.f18903j = u1Var.f18903j;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new u1(this, spliterator);
    }

    @Override // j$.util.stream.d
    public final Object a() {
        v3 v3Var = this.f18656a;
        s1 s1Var = (s1) ((Supplier) this.f18903j.f18516c).get();
        v3Var.E0(this.f18657b, s1Var);
        boolean z = s1Var.f18866b;
        if (z == ((t1) this.f18903j.f18515b).f18881b) {
            Boolean boolValueOf = Boolean.valueOf(z);
            AtomicReference atomicReference = this.f18631h;
            while (!atomicReference.compareAndSet(null, boolValueOf) && atomicReference.get() == null) {
            }
        }
        return null;
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return Boolean.valueOf(!((t1) this.f18903j.f18515b).f18881b);
    }
}
