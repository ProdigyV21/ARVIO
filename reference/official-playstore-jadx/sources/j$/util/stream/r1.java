package j$.util.stream;

import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class r1 extends s1 implements i5 {
    @Override // java.util.function.Consumer
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.i5
    public final /* synthetic */ void n(Double d4) {
        v3.D(this, d4);
    }

    @Override // j$.util.stream.s1, j$.util.stream.l5
    public final void accept(double d4) {
        if (this.f18865a) {
            return;
        }
        DoublePredicate doublePredicate = null;
        doublePredicate.test(d4);
        throw null;
    }
}
