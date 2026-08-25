package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class f0 implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Spliterator.OfInt f18535a;

    public /* synthetic */ f0(Spliterator.OfInt ofInt) {
        this.f18535a = ofInt;
    }

    public static /* synthetic */ h0 a(Spliterator.OfInt ofInt) {
        if (ofInt == null) {
            return null;
        }
        return ofInt instanceof g0 ? ((g0) ofInt).f18563a : new f0(ofInt);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ int characteristics() {
        return this.f18535a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Spliterator.OfInt ofInt = this.f18535a;
        if (obj instanceof f0) {
            obj = ((f0) obj).f18535a;
        }
        return ofInt.equals(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long estimateSize() {
        return this.f18535a.estimateSize();
    }

    @Override // j$.util.n0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18535a.forEachRemaining(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18535a.forEachRemaining((Consumer<? super Integer>) consumer);
    }

    @Override // j$.util.h0
    public final /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
        this.f18535a.forEachRemaining(intConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.f18535a.getComparator();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.f18535a.getExactSizeIfKnown();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return this.f18535a.hasCharacteristics(i10);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18535a.hashCode();
    }

    @Override // j$.util.n0
    public final /* synthetic */ boolean tryAdvance(Object obj) {
        return this.f18535a.tryAdvance(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.f18535a.tryAdvance((Consumer<? super Integer>) consumer);
    }

    @Override // j$.util.h0
    public final /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
        return this.f18535a.tryAdvance(intConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ Spliterator trySplit() {
        return o0.a(this.f18535a.trySplit());
    }

    @Override // j$.util.h0, j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ h0 trySplit() {
        return a(this.f18535a.trySplit());
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ n0 trySplit() {
        return l0.a(this.f18535a.trySplit());
    }
}
