package j$.util;

import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class k implements LongConsumer, IntConsumer {
    private long count;
    private long sum;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i10) {
        accept(i10);
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j10) {
        this.count++;
        this.sum += j10;
        this.min = Math.min(this.min, j10);
        this.max = Math.max(this.max, j10);
    }

    public final void a(k kVar) {
        this.count += kVar.count;
        this.sum += kVar.sum;
        this.min = Math.min(this.min, kVar.min);
        this.max = Math.max(this.max, kVar.max);
    }

    public final String toString() {
        String simpleName = k.class.getSimpleName();
        Long lValueOf = Long.valueOf(this.count);
        Long lValueOf2 = Long.valueOf(this.sum);
        Long lValueOf3 = Long.valueOf(this.min);
        long j10 = this.count;
        return String.format("%s{count=%d, sum=%d, min=%d, average=%f, max=%d}", simpleName, lValueOf, lValueOf2, lValueOf3, Double.valueOf(j10 > 0 ? this.sum / j10 : 0.0d), Long.valueOf(this.max));
    }
}
