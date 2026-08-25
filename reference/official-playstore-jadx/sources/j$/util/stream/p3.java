package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p3 extends r3 implements k5 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long[] f18825h;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.stream.k5
    public final /* synthetic */ void l(Long l10) {
        v3.H(this, l10);
    }

    public p3(Spliterator spliterator, v3 v3Var, long[] jArr) {
        super(spliterator, v3Var, jArr.length);
        this.f18825h = jArr;
    }

    public p3(p3 p3Var, Spliterator spliterator, long j10, long j11) {
        super(p3Var, spliterator, j10, j11, p3Var.f18825h.length);
        this.f18825h = p3Var.f18825h;
    }

    @Override // j$.util.stream.r3
    public final r3 a(Spliterator spliterator, long j10, long j11) {
        return new p3(this, spliterator, j10, j11);
    }

    @Override // j$.util.stream.r3, j$.util.stream.l5
    public final void accept(long j10) {
        int i10 = this.f18853f;
        if (i10 >= this.f18854g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f18853f));
        }
        long[] jArr = this.f18825h;
        this.f18853f = i10 + 1;
        jArr[i10] = j10;
    }
}
