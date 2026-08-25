package j$.util;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class z0 implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f19013a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19014b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f19015c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f19016d;

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.A(this, consumer);
    }

    public z0(int[] iArr, int i10, int i11, int i12) {
        this.f19013a = iArr;
        this.f19014b = i10;
        this.f19015c = i11;
        this.f19016d = i12 | 16448;
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final h0 trySplit() {
        int i10 = this.f19014b;
        int i11 = (this.f19015c + i10) >>> 1;
        if (i10 >= i11) {
            return null;
        }
        this.f19014b = i11;
        return new z0(this.f19013a, i10, i11, this.f19016d);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(IntConsumer intConsumer) {
        int i10;
        intConsumer.getClass();
        int[] iArr = this.f19013a;
        int length = iArr.length;
        int i11 = this.f19015c;
        if (length < i11 || (i10 = this.f19014b) < 0) {
            return;
        }
        this.f19014b = i11;
        if (i10 < i11) {
            do {
                intConsumer.accept(iArr[i10]);
                i10++;
            } while (i10 < i11);
        }
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(IntConsumer intConsumer) {
        intConsumer.getClass();
        int i10 = this.f19014b;
        if (i10 < 0 || i10 >= this.f19015c) {
            return false;
        }
        this.f19014b = i10 + 1;
        intConsumer.accept(this.f19013a[i10]);
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f19015c - this.f19014b;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.f19016d;
    }

    @Override // j$.util.Spliterator
    public final java.util.Comparator getComparator() {
        if (j$.com.android.tools.r8.a.p(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
