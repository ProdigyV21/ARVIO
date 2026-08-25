package j$.util.concurrent;

import j$.util.Spliterator;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends p implements Spliterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f18483i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f18484j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(l[] lVarArr, int i10, int i11, int i12, long j10, int i13) {
        super(lVarArr, i10, i11, i12);
        this.f18483i = i13;
        this.f18484j = j10;
    }

    @Override // j$.util.Spliterator
    public final int characteristics() {
        switch (this.f18483i) {
            case 0:
                return 4353;
            default:
                return 4352;
        }
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ long getExactSizeIfKnown() {
        switch (this.f18483i) {
        }
        return j$.com.android.tools.r8.a.n(this);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean hasCharacteristics(int i10) {
        switch (this.f18483i) {
        }
        return j$.com.android.tools.r8.a.p(this, i10);
    }

    @Override // j$.util.Spliterator
    public final Comparator getComparator() {
        switch (this.f18483i) {
            case 0:
                throw new IllegalStateException();
            default:
                throw new IllegalStateException();
        }
    }

    @Override // j$.util.Spliterator
    public final Spliterator trySplit() {
        switch (this.f18483i) {
            case 0:
                int i10 = this.f18501f;
                int i11 = this.f18502g;
                int i12 = (i10 + i11) >>> 1;
                if (i12 <= i10) {
                    return null;
                }
                l[] lVarArr = this.f18496a;
                this.f18502g = i12;
                long j10 = this.f18484j >>> 1;
                this.f18484j = j10;
                return new j(lVarArr, this.f18503h, i12, i11, j10, 0);
            default:
                int i13 = this.f18501f;
                int i14 = this.f18502g;
                int i15 = (i13 + i14) >>> 1;
                if (i15 <= i13) {
                    return null;
                }
                l[] lVarArr2 = this.f18496a;
                this.f18502g = i15;
                long j11 = this.f18484j >>> 1;
                this.f18484j = j11;
                return new j(lVarArr2, this.f18503h, i15, i14, j11, 1);
        }
    }

    @Override // j$.util.Spliterator
    public final void forEachRemaining(Consumer consumer) {
        switch (this.f18483i) {
            case 0:
                consumer.getClass();
                while (true) {
                    l lVarA = a();
                    if (lVarA != null) {
                        consumer.n(lVarA.f18489b);
                    }
                    break;
                }
                break;
            default:
                consumer.getClass();
                while (true) {
                    l lVarA2 = a();
                    if (lVarA2 != null) {
                        consumer.n(lVarA2.f18490c);
                    }
                    break;
                }
                break;
        }
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        switch (this.f18483i) {
            case 0:
                consumer.getClass();
                l lVarA = a();
                if (lVarA != null) {
                    consumer.n(lVarA.f18489b);
                    break;
                }
                break;
            default:
                consumer.getClass();
                l lVarA2 = a();
                if (lVarA2 != null) {
                    consumer.n(lVarA2.f18490c);
                    break;
                }
                break;
        }
        return true;
    }

    @Override // j$.util.Spliterator
    public final long estimateSize() {
        switch (this.f18483i) {
        }
        return this.f18484j;
    }
}
