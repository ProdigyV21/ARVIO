package j$.util;

import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class b1 implements k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long[] f18448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18451d;

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.k(this, consumer);
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
        return j$.com.android.tools.r8.a.B(this, consumer);
    }

    public b1(long[] jArr, int i10, int i11, int i12) {
        this.f18448a = jArr;
        this.f18449b = i10;
        this.f18450c = i11;
        this.f18451d = i12 | 16448;
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final k0 trySplit() {
        int i10 = this.f18449b;
        int i11 = (this.f18450c + i10) >>> 1;
        if (i10 >= i11) {
            return null;
        }
        this.f18449b = i11;
        return new b1(this.f18448a, i10, i11, this.f18451d);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(LongConsumer longConsumer) {
        int i10;
        longConsumer.getClass();
        long[] jArr = this.f18448a;
        int length = jArr.length;
        int i11 = this.f18450c;
        if (length < i11 || (i10 = this.f18449b) < 0) {
            return;
        }
        this.f18449b = i11;
        if (i10 < i11) {
            do {
                longConsumer.accept(jArr[i10]);
                i10++;
            } while (i10 < i11);
        }
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(LongConsumer longConsumer) {
        longConsumer.getClass();
        int i10 = this.f18449b;
        if (i10 < 0 || i10 >= this.f18450c) {
            return false;
        }
        this.f18449b = i10 + 1;
        longConsumer.accept(this.f18448a[i10]);
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18450c - this.f18449b;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.f18451d;
    }

    @Override // j$.util.Spliterator
    public final java.util.Comparator getComparator() {
        if (j$.com.android.tools.r8.a.p(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
