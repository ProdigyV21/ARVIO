package j$.util.concurrent;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends p implements Spliterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ConcurrentHashMap f18480i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f18481j;

    @Override // j$.util.Spliterator
    public final int characteristics() {
        return 4353;
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
    public final Comparator getComparator() {
        throw new IllegalStateException();
    }

    public f(l[] lVarArr, int i10, int i11, int i12, long j10, ConcurrentHashMap concurrentHashMap) {
        super(lVarArr, i10, i11, i12);
        this.f18480i = concurrentHashMap;
        this.f18481j = j10;
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        int i10 = this.f18501f;
        int i11 = this.f18502g;
        int i12 = (i10 + i11) >>> 1;
        if (i12 <= i10) {
            return null;
        }
        l[] lVarArr = this.f18496a;
        this.f18502g = i12;
        long j10 = this.f18481j >>> 1;
        this.f18481j = j10;
        return new f(lVarArr, this.f18503h, i12, i11, j10, this.f18480i);
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        consumer.getClass();
        while (true) {
            l lVarA = a();
            if (lVarA == null) {
                return;
            } else {
                consumer.n(new k(lVarA.f18489b, lVarA.f18490c, this.f18480i));
            }
        }
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        consumer.getClass();
        l lVarA = a();
        if (lVarA == null) {
            return false;
        }
        consumer.n(new k(lVarA.f18489b, lVarA.f18490c, this.f18480i));
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        return this.f18481j;
    }
}
