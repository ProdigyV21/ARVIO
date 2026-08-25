package j$.util;

import java.util.ConcurrentModificationException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final java.util.List f18439a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18440b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f18441c;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 16464;
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
    public final java.util.Comparator getComparator() {
        throw new IllegalStateException();
    }

    public a(java.util.List list) {
        this.f18439a = list;
        this.f18440b = 0;
        this.f18441c = -1;
    }

    public a(a aVar, int i10, int i11) {
        this.f18439a = aVar.f18439a;
        this.f18440b = i10;
        this.f18441c = i11;
    }

    public final int a() {
        java.util.List list = this.f18439a;
        int i10 = this.f18441c;
        if (i10 >= 0) {
            return i10;
        }
        int size = list.size();
        this.f18441c = size;
        return size;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        int iA = a();
        int i10 = this.f18440b;
        int i11 = (iA + i10) >>> 1;
        if (i10 >= i11) {
            return null;
        }
        this.f18440b = i11;
        return new a(this, i10, i11);
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        int iA = a();
        int i10 = this.f18440b;
        if (i10 >= iA) {
            return false;
        }
        this.f18440b = i10 + 1;
        try {
            consumer.accept(this.f18439a.get(i10));
            return true;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        Objects.requireNonNull(consumer);
        java.util.List list = this.f18439a;
        int iA = a();
        this.f18440b = iA;
        for (int i10 = this.f18440b; i10 < iA; i10++) {
            try {
                consumer.accept(list.get(i10));
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return a() - this.f18440b;
    }
}
