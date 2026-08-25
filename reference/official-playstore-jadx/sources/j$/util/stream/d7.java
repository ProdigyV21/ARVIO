package j$.util.stream;

import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class d7 extends e7 implements LongConsumer {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long[] f18667c;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    public d7(int i10) {
        this.f18667c = new long[i10];
    }

    @Override // j$.util.stream.e7
    public final void a(Object obj, long j10) {
        LongConsumer longConsumer = (LongConsumer) obj;
        for (int i10 = 0; i10 < j10; i10++) {
            longConsumer.accept(this.f18667c[i10]);
        }
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j10) {
        int i10 = this.f18682b;
        this.f18682b = i10 + 1;
        this.f18667c[i10] = j10;
    }
}
