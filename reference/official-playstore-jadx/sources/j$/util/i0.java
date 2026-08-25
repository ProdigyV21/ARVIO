package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class i0 implements k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Spliterator.OfLong f18566a;

    public /* synthetic */ i0(Spliterator.OfLong ofLong) {
        this.f18566a = ofLong;
    }

    public static /* synthetic */ k0 a(Spliterator.OfLong ofLong) {
        if (ofLong == null) {
            return null;
        }
        return ofLong instanceof j0 ? ((j0) ofLong).f18567a : new i0(ofLong);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ int characteristics() {
        return this.f18566a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Spliterator.OfLong ofLong = this.f18566a;
        if (obj instanceof i0) {
            obj = ((i0) obj).f18566a;
        }
        return ofLong.equals(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long estimateSize() {
        return this.f18566a.estimateSize();
    }

    @Override // j$.util.n0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18566a.forEachRemaining(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18566a.forEachRemaining((Consumer<? super Long>) consumer);
    }

    @Override // j$.util.k0
    public final /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
        this.f18566a.forEachRemaining(longConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.f18566a.getComparator();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.f18566a.getExactSizeIfKnown();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return this.f18566a.hasCharacteristics(i10);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18566a.hashCode();
    }

    @Override // j$.util.n0
    public final /* synthetic */ boolean tryAdvance(Object obj) {
        return this.f18566a.tryAdvance(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.f18566a.tryAdvance((Consumer<? super Long>) consumer);
    }

    @Override // j$.util.k0
    public final /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
        return this.f18566a.tryAdvance(longConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ Spliterator trySplit() {
        return o0.a(this.f18566a.trySplit());
    }

    @Override // j$.util.k0, j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ k0 trySplit() {
        return a(this.f18566a.trySplit());
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ n0 trySplit() {
        return l0.a(this.f18566a.trySplit());
    }
}
