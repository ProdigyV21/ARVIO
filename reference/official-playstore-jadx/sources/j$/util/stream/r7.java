package j$.util.stream;

import j$.util.Objects;
import java.util.Comparator;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class r7 extends t7 implements j$.util.n0 {
    public abstract Object b();

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    public r7(j$.util.n0 n0Var, long j10, long j11) {
        super(n0Var, j10, j11, 0L, Math.min(n0Var.estimateSize(), j11));
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(Object obj) {
        long j10;
        Objects.requireNonNull(obj);
        long j11 = this.f18894e;
        long j12 = this.f18890a;
        if (j12 >= j11) {
            return false;
        }
        while (true) {
            j10 = this.f18893d;
            if (j12 <= j10) {
                break;
            }
            ((j$.util.n0) this.f18892c).tryAdvance(b());
            this.f18893d++;
        }
        if (j10 >= this.f18894e) {
            return false;
        }
        this.f18893d = j10 + 1;
        return ((j$.util.n0) this.f18892c).tryAdvance(obj);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(Object obj) {
        Objects.requireNonNull(obj);
        long j10 = this.f18894e;
        long j11 = this.f18890a;
        if (j11 >= j10) {
            return;
        }
        long j12 = this.f18893d;
        if (j12 >= j10) {
            return;
        }
        if (j12 >= j11 && ((j$.util.n0) this.f18892c).estimateSize() + j12 <= this.f18891b) {
            ((j$.util.n0) this.f18892c).forEachRemaining(obj);
            this.f18893d = this.f18894e;
            return;
        }
        while (j11 > this.f18893d) {
            ((j$.util.n0) this.f18892c).tryAdvance(b());
            this.f18893d++;
        }
        while (this.f18893d < this.f18894e) {
            ((j$.util.n0) this.f18892c).tryAdvance(obj);
            this.f18893d++;
        }
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        forEachRemaining((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
        return tryAdvance((Object) intConsumer);
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        forEachRemaining((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
        return tryAdvance((Object) longConsumer);
    }

    public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        forEachRemaining((Object) doubleConsumer);
    }

    public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
        return tryAdvance((Object) doubleConsumer);
    }
}
