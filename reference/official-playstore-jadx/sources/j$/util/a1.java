package j$.util;

import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public class a1 implements Spliterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final java.util.Collection f18442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Iterator f18443b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18444c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f18445d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f18446e;

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    public a1(java.util.Collection collection, int i10) {
        this.f18442a = collection;
        this.f18443b = null;
        this.f18444c = (i10 & 4096) == 0 ? i10 | 16448 : i10;
    }

    public a1(Iterator it, int i10) {
        this.f18442a = null;
        this.f18443b = it;
        this.f18445d = Long.MAX_VALUE;
        this.f18444c = i10 & (-16449);
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        long size;
        Iterator it = this.f18443b;
        if (it == null) {
            it = this.f18442a.iterator();
            this.f18443b = it;
            size = this.f18442a.size();
            this.f18445d = size;
        } else {
            size = this.f18445d;
        }
        if (size <= 1 || !it.hasNext()) {
            return null;
        }
        int i10 = this.f18446e + 1024;
        if (i10 > size) {
            i10 = (int) size;
        }
        if (i10 > 33554432) {
            i10 = 33554432;
        }
        Object[] objArr = new Object[i10];
        int i11 = 0;
        do {
            objArr[i11] = it.next();
            i11++;
            if (i11 >= i10) {
                break;
            }
        } while (it.hasNext());
        this.f18446e = i11;
        long j10 = this.f18445d;
        if (j10 != Long.MAX_VALUE) {
            this.f18445d = j10 - ((long) i11);
        }
        return new t0(objArr, 0, i11, this.f18444c);
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        Iterator it = this.f18443b;
        if (it == null) {
            it = this.f18442a.iterator();
            this.f18443b = it;
            this.f18445d = this.f18442a.size();
        }
        if (it instanceof j) {
            ((j) it).forEachRemaining(consumer);
            return;
        }
        Objects.requireNonNull(consumer);
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        if (this.f18443b == null) {
            this.f18443b = this.f18442a.iterator();
            this.f18445d = this.f18442a.size();
        }
        if (!this.f18443b.hasNext()) {
            return false;
        }
        consumer.accept(this.f18443b.next());
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        if (this.f18443b == null) {
            this.f18443b = this.f18442a.iterator();
            long size = this.f18442a.size();
            this.f18445d = size;
            return size;
        }
        return this.f18445d;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return this.f18444c;
    }

    @Override // j$.util.Spliterator
    public java.util.Comparator getComparator() {
        if (j$.com.android.tools.r8.a.p(this, 4)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
