package j$.util;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class u0 implements e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double[] f19005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f19007c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f19008d;

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.i(this, consumer);
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
        return j$.com.android.tools.r8.a.z(this, consumer);
    }

    public u0(double[] dArr, int i10, int i11, int i12) {
        this.f19005a = dArr;
        this.f19006b = i10;
        this.f19007c = i11;
        this.f19008d = i12 | 16448;
    }

    @Override // j$.util.n0, j$.util.Spliterator
    public final e0 trySplit() {
        int i10 = this.f19006b;
        int i11 = (this.f19007c + i10) >>> 1;
        if (i10 >= i11) {
            return null;
        }
        this.f19006b = i11;
        return new u0(this.f19005a, i10, i11, this.f19008d);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        int i10;
        doubleConsumer.getClass();
        double[] dArr = this.f19005a;
        int length = dArr.length;
        int i11 = this.f19007c;
        if (length < i11 || (i10 = this.f19006b) < 0) {
            return;
        }
        this.f19006b = i11;
        if (i10 < i11) {
            do {
                doubleConsumer.accept(dArr[i10]);
                i10++;
            } while (i10 < i11);
        }
    }

    @Override // j$.util.n0
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        doubleConsumer.getClass();
        int i10 = this.f19006b;
        if (i10 < 0 || i10 >= this.f19007c) {
            return false;
        }
        this.f19006b = i10 + 1;
        doubleConsumer.accept(this.f19005a[i10]);
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f19007c - this.f19006b;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.f19008d;
    }

    @Override // j$.util.Spliterator
    public final java.util.Comparator getComparator() {
        if (j$.com.android.tools.r8.a.p(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
