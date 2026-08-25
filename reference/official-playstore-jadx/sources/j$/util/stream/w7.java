package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class w7 extends x7 implements j$.util.k0, LongConsumer {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f18944f;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.k(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.B(this, consumer);
    }

    @Override // j$.util.stream.a8
    public final Spliterator b(Spliterator spliterator) {
        return new w7((j$.util.k0) spliterator, this);
    }

    @Override // j$.util.stream.x7
    public final void g(Object obj) {
        ((LongConsumer) obj).accept(this.f18944f);
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j10) {
        this.f18944f = j10;
    }

    @Override // j$.util.stream.x7
    public final e7 h(int i10) {
        return new d7(i10);
    }
}
