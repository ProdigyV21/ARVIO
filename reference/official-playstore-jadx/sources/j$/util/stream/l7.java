package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class l7 extends a7 implements j$.util.h0 {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.A(this, consumer);
    }

    @Override // j$.util.stream.a7
    public final a7 e(Spliterator spliterator) {
        return new l7(this.f18618b, spliterator, this.f18617a);
    }

    @Override // j$.util.stream.a7
    public final void d() {
        q6 q6Var = new q6();
        this.f18624h = q6Var;
        Objects.requireNonNull(q6Var);
        this.f18621e = this.f18618b.F0(new k7(q6Var, 1));
        this.f18622f = new j$.util.function.i(this, 10);
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final Spliterator trySplit() {
        return (j$.util.h0) super.trySplit();
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final j$.util.h0 trySplit() {
        return (j$.util.h0) super.trySplit();
    }

    @Override // j$.util.stream.a7, j$.util.Spliterator
    public final j$.util.n0 trySplit() {
        return (j$.util.h0) super.trySplit();
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(IntConsumer intConsumer) {
        int i10;
        Objects.requireNonNull(intConsumer);
        boolean zA = a();
        if (zA) {
            q6 q6Var = (q6) this.f18624h;
            long j10 = this.f18623g;
            int iR = q6Var.r(j10);
            if (q6Var.f18647c == 0 && iR == 0) {
                i10 = ((int[]) q6Var.f18909e)[(int) j10];
            } else {
                i10 = ((int[][]) q6Var.f18910f)[iR][(int) (j10 - q6Var.f18648d[iR])];
            }
            intConsumer.accept(i10);
        }
        return zA;
    }

    @Override // j$.util.n0
    public final void forEachRemaining(IntConsumer intConsumer) {
        if (this.f18624h == null && !this.f18625i) {
            Objects.requireNonNull(intConsumer);
            c();
            Objects.requireNonNull(intConsumer);
            k7 k7Var = new k7(intConsumer, 0);
            this.f18618b.E0(this.f18620d, k7Var);
            this.f18625i = true;
            return;
        }
        while (tryAdvance(intConsumer)) {
        }
    }
}
