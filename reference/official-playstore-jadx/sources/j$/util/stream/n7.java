package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class n7 extends a7 implements j$.util.k0 {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.k(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.B(this, consumer);
    }

    @Override // j$.util.stream.a7
    public final a7 e(Spliterator spliterator) {
        return new n7(this.f18618b, spliterator, this.f18617a);
    }

    @Override // j$.util.stream.a7
    public final void d() {
        s6 s6Var = new s6();
        this.f18624h = s6Var;
        Objects.requireNonNull(s6Var);
        this.f18621e = this.f18618b.F0(new m7(s6Var, 1));
        this.f18622f = new j$.util.function.i(this, 11);
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (j$.util.k0) super.trySplit();
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final j$.util.k0 trySplit() {
        return (j$.util.k0) super.trySplit();
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final j$.util.n0 trySplit() {
        return (j$.util.k0) super.trySplit();
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(LongConsumer longConsumer) {
        long j10;
        Objects.requireNonNull(longConsumer);
        boolean zA = a();
        if (zA) {
            s6 s6Var = (s6) this.f18624h;
            long j11 = this.f18623g;
            int iR = s6Var.r(j11);
            if (s6Var.f18647c == 0 && iR == 0) {
                j10 = ((long[]) s6Var.f18909e)[(int) j11];
            } else {
                j10 = ((long[][]) s6Var.f18910f)[iR][(int) (j11 - s6Var.f18648d[iR])];
            }
            longConsumer.accept(j10);
        }
        return zA;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(LongConsumer longConsumer) {
        if (this.f18624h == null && !this.f18625i) {
            Objects.requireNonNull(longConsumer);
            c();
            Objects.requireNonNull(longConsumer);
            m7 m7Var = new m7(longConsumer, 0);
            this.f18618b.E0(this.f18620d, m7Var);
            this.f18625i = true;
            return;
        }
        while (tryAdvance(longConsumer)) {
        }
    }
}
