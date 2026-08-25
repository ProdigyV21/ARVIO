package j$.util;

import j$.util.Spliterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m0 implements Spliterator.OfPrimitive {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n0 f18575a;

    public /* synthetic */ m0(n0 n0Var) {
        this.f18575a = n0Var;
    }

    public static /* synthetic */ Spliterator.OfPrimitive a(n0 n0Var) {
        if (n0Var == null) {
            return null;
        }
        return n0Var instanceof l0 ? ((l0) n0Var).f18571a : n0Var instanceof e0 ? d0.a((e0) n0Var) : n0Var instanceof h0 ? g0.a((h0) n0Var) : n0Var instanceof k0 ? j0.a((k0) n0Var) : new m0(n0Var);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ int characteristics() {
        return this.f18575a.characteristics();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        n0 n0Var = this.f18575a;
        if (obj instanceof m0) {
            obj = ((m0) obj).f18575a;
        }
        return n0Var.equals(obj);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ long estimateSize() {
        return this.f18575a.estimateSize();
    }

    @Override // java.util.Spliterator.OfPrimitive
    public final /* synthetic */ void forEachRemaining(Object obj) {
        this.f18575a.forEachRemaining(obj);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        this.f18575a.forEachRemaining(consumer);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ java.util.Comparator getComparator() {
        return this.f18575a.getComparator();
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return this.f18575a.getExactSizeIfKnown();
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return this.f18575a.hasCharacteristics(i10);
    }

    public final /* synthetic */ int hashCode() {
        return this.f18575a.hashCode();
    }

    @Override // java.util.Spliterator.OfPrimitive
    public final /* synthetic */ boolean tryAdvance(Object obj) {
        return this.f18575a.tryAdvance(obj);
    }

    @Override // java.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return this.f18575a.tryAdvance(consumer);
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    public final /* synthetic */ Spliterator.OfPrimitive trySplit() {
        return a(this.f18575a.trySplit());
    }

    @Override // java.util.Spliterator.OfPrimitive, java.util.Spliterator
    public final /* synthetic */ java.util.Spliterator trySplit() {
        return Spliterator.Wrapper.convert(this.f18575a.trySplit());
    }
}
