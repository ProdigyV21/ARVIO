package j$.util;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class t0 implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f19000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f19002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f19003d;

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    public t0(Object[] objArr, int i10, int i11, int i12) {
        this.f19000a = objArr;
        this.f19001b = i10;
        this.f19002c = i11;
        this.f19003d = i12 | 16448;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        int i10 = this.f19001b;
        int i11 = (this.f19002c + i10) >>> 1;
        if (i10 >= i11) {
            return null;
        }
        this.f19001b = i11;
        return new t0(this.f19000a, i10, i11, this.f19003d);
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        int i10;
        consumer.getClass();
        Object[] objArr = this.f19000a;
        int length = objArr.length;
        int i11 = this.f19002c;
        if (length < i11 || (i10 = this.f19001b) < 0) {
            return;
        }
        this.f19001b = i11;
        if (i10 < i11) {
            do {
                consumer.n(objArr[i10]);
                i10++;
            } while (i10 < i11);
        }
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        int i10 = this.f19001b;
        if (i10 < 0 || i10 >= this.f19002c) {
            return false;
        }
        this.f19001b = i10 + 1;
        consumer.n(this.f19000a[i10]);
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f19002c - this.f19001b;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.f19003d;
    }

    @Override // j$.util.Spliterator
    public final java.util.Comparator getComparator() {
        if (j$.com.android.tools.r8.a.p(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
