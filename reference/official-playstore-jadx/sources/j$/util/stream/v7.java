package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class v7 extends x7 implements j$.util.h0, IntConsumer {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f18932f;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.A(this, consumer);
    }

    @Override // j$.util.stream.a8
    public final Spliterator b(Spliterator spliterator) {
        return new v7((j$.util.h0) spliterator, this);
    }

    @Override // j$.util.stream.x7
    public final void g(Object obj) {
        ((IntConsumer) obj).accept(this.f18932f);
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i10) {
        this.f18932f = i10;
    }

    @Override // j$.util.stream.x7
    public final e7 h(int i10) {
        return new c7(i10);
    }
}
