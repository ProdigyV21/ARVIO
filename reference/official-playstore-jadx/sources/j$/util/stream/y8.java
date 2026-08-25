package j$.util.stream;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class y8 implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Spliterator f18985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicBoolean f18986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f18987c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f18988d;

    public abstract Spliterator b(Spliterator spliterator);

    @Override // j$.util.Spliterator
    public final long getExactSizeIfKnown() {
        return -1L;
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    @Override // j$.util.Spliterator
    public void forEachRemaining(Consumer consumer) {
        while (tryAdvance(consumer)) {
        }
    }

    public y8(Spliterator spliterator) {
        this.f18987c = true;
        this.f18985a = spliterator;
        this.f18986b = new AtomicBoolean();
    }

    public y8(Spliterator spliterator, y8 y8Var) {
        this.f18987c = true;
        this.f18985a = spliterator;
        y8Var.getClass();
        this.f18986b = y8Var.f18986b;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18985a.estimateSize();
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.f18985a.characteristics() & (-16449);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        return this.f18985a.getComparator();
    }

    @Override // j$.util.Spliterator
    public Spliterator trySplit() {
        Spliterator spliteratorTrySplit = this.f18985a.trySplit();
        if (spliteratorTrySplit != null) {
            return b(spliteratorTrySplit);
        }
        return null;
    }

    public final boolean a() {
        return (this.f18988d == 0 && this.f18986b.get()) ? false : true;
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.h0 trySplit() {
        return (j$.util.h0) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.k0 trySplit() {
        return (j$.util.k0) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.e0 trySplit() {
        return (j$.util.e0) trySplit();
    }

    @Override // j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.n0 trySplit() {
        return (j$.util.n0) trySplit();
    }
}
