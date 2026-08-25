package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c0 implements e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Spliterator.OfDouble f18454a;

    public /* synthetic */ c0(Spliterator.OfDouble ofDouble) {
        this.f18454a = ofDouble;
    }

    public static /* synthetic */ e0 a(Spliterator.OfDouble ofDouble) {
        if (ofDouble == null) {
            return null;
        }
        return ofDouble instanceof d0 ? ((d0) ofDouble).f18532a : new c0(ofDouble);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ int characteristics() {
        return this.f18454a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Spliterator.OfDouble ofDouble = this.f18454a;
        if (obj instanceof c0) {
            obj = ((c0) obj).f18454a;
        }
        return ofDouble.equals(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long estimateSize() {
        return this.f18454a.estimateSize();
    }

    @Override // j$.util.n0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18454a.forEachRemaining(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18454a.forEachRemaining((Consumer<? super Double>) consumer);
    }

    @Override // j$.util.e0
    public final /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
        this.f18454a.forEachRemaining(doubleConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.f18454a.getComparator();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.f18454a.getExactSizeIfKnown();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return this.f18454a.hasCharacteristics(i10);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18454a.hashCode();
    }

    @Override // j$.util.n0
    public final /* synthetic */ boolean tryAdvance(Object obj) {
        return this.f18454a.tryAdvance(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.f18454a.tryAdvance((Consumer<? super Double>) consumer);
    }

    @Override // j$.util.e0
    public final /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
        return this.f18454a.tryAdvance(doubleConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ Spliterator trySplit() {
        return o0.a(this.f18454a.trySplit());
    }

    @Override // j$.util.e0, j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ e0 trySplit() {
        return a(this.f18454a.trySplit());
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ n0 trySplit() {
        return l0.a(this.f18454a.trySplit());
    }
}
