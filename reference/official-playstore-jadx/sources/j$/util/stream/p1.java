package j$.util.stream;

import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class p1 extends s1 implements j5 {
    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        d((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.j5
    public final /* synthetic */ void d(Integer num) {
        v3.F(this, num);
    }

    @Override // j$.util.stream.s1, j$.util.stream.l5
    public final void accept(int i10) {
        if (this.f18865a) {
            return;
        }
        IntPredicate intPredicate = null;
        intPredicate.test(i10);
        throw null;
    }
}
