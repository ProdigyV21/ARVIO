package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class r2 extends i2 {
    @Override // j$.util.stream.g2
    public final g2 h(long j10, long j11, IntFunction intFunction) {
        if (j10 == 0 && j11 == this.f18724c) {
            return this;
        }
        long jCount = this.f18722a.count();
        if (j10 >= jCount) {
            return this.f18723b.h(j10 - jCount, j11 - jCount, intFunction);
        }
        if (j11 > jCount) {
            return v3.e0(z6.REFERENCE, this.f18722a.h(j10, jCount, intFunction), this.f18723b.h(0L, j11 - jCount, intFunction));
        }
        return this.f18722a.h(j10, j11, intFunction);
    }

    @Override // j$.util.stream.g2
    public final Spliterator spliterator() {
        return new i3(this);
    }

    @Override // j$.util.stream.g2
    public final void k(Object[] objArr, int i10) {
        Objects.requireNonNull(objArr);
        g2 g2Var = this.f18722a;
        g2Var.k(objArr, i10);
        this.f18723b.k(objArr, i10 + ((int) g2Var.count()));
    }

    @Override // j$.util.stream.g2
    public final Object[] m(IntFunction intFunction) {
        long j10 = this.f18724c;
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        Object[] objArr = (Object[]) intFunction.apply((int) j10);
        k(objArr, 0);
        return objArr;
    }

    @Override // j$.util.stream.g2
    public final void forEach(Consumer consumer) {
        this.f18722a.forEach(consumer);
        this.f18723b.forEach(consumer);
    }

    public final String toString() {
        long j10 = this.f18724c;
        return j10 < 32 ? String.format("ConcNode[%s.%s]", this.f18722a, this.f18723b) : String.format("ConcNode[size=%d]", Long.valueOf(j10));
    }
}
