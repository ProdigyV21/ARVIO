package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class j7 extends a7 implements j$.util.e0 {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.i(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.z(this, consumer);
    }

    @Override // j$.util.stream.a7
    public final a7 e(Spliterator spliterator) {
        return new j7(this.f18618b, spliterator, this.f18617a);
    }

    @Override // j$.util.stream.a7
    public final void d() {
        o6 o6Var = new o6();
        this.f18624h = o6Var;
        Objects.requireNonNull(o6Var);
        this.f18621e = this.f18618b.F0(new i7(o6Var, 1));
        this.f18622f = new j$.util.function.i(this, 9);
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (j$.util.e0) super.trySplit();
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final j$.util.e0 trySplit() {
        return (j$.util.e0) super.trySplit();
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final j$.util.n0 trySplit() {
        return (j$.util.e0) super.trySplit();
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        double d4;
        Objects.requireNonNull(doubleConsumer);
        boolean zA = a();
        if (zA) {
            o6 o6Var = (o6) this.f18624h;
            long j10 = this.f18623g;
            int iR = o6Var.r(j10);
            if (o6Var.f18647c == 0 && iR == 0) {
                d4 = ((double[]) o6Var.f18909e)[(int) j10];
            } else {
                d4 = ((double[][]) o6Var.f18910f)[iR][(int) (j10 - o6Var.f18648d[iR])];
            }
            doubleConsumer.accept(d4);
        }
        return zA;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        if (this.f18624h == null && !this.f18625i) {
            Objects.requireNonNull(doubleConsumer);
            c();
            Objects.requireNonNull(doubleConsumer);
            i7 i7Var = new i7(doubleConsumer, 0);
            this.f18618b.E0(this.f18620d, i7Var);
            this.f18625i = true;
            return;
        }
        while (tryAdvance(doubleConsumer)) {
        }
    }
}
