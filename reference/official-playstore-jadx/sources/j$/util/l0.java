package j$.util;

import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class l0 implements n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Spliterator.OfPrimitive f18571a;

    public /* synthetic */ l0(Spliterator.OfPrimitive ofPrimitive) {
        this.f18571a = ofPrimitive;
    }

    public static /* synthetic */ n0 a(Spliterator.OfPrimitive ofPrimitive) {
        if (ofPrimitive == null) {
            return null;
        }
        return ofPrimitive instanceof m0 ? ((m0) ofPrimitive).f18575a : ofPrimitive instanceof Spliterator.OfDouble ? c0.a((Spliterator.OfDouble) ofPrimitive) : ofPrimitive instanceof Spliterator.OfInt ? f0.a((Spliterator.OfInt) ofPrimitive) : ofPrimitive instanceof Spliterator.OfLong ? i0.a((Spliterator.OfLong) ofPrimitive) : new l0(ofPrimitive);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ int characteristics() {
        return this.f18571a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Spliterator.OfPrimitive ofPrimitive = this.f18571a;
        if (obj instanceof l0) {
            obj = ((l0) obj).f18571a;
        }
        return ofPrimitive.equals(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long estimateSize() {
        return this.f18571a.estimateSize();
    }

    @Override // j$.util.n0
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18571a.forEachRemaining(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18571a.forEachRemaining(consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.f18571a.getComparator();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.f18571a.getExactSizeIfKnown();
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return this.f18571a.hasCharacteristics(i10);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18571a.hashCode();
    }

    @Override // j$.util.n0
    public final /* synthetic */ boolean tryAdvance(Object obj) {
        return this.f18571a.tryAdvance(obj);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.f18571a.tryAdvance(consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ Spliterator trySplit() {
        return o0.a(this.f18571a.trySplit());
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final /* synthetic */ n0 trySplit() {
        return a(this.f18571a.trySplit());
    }
}
