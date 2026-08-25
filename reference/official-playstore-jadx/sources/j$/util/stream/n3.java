package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class n3 extends r3 implements i5 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final double[] f18802h;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.i5
    public final /* synthetic */ void n(Double d4) {
        v3.D(this, d4);
    }

    public n3(Spliterator spliterator, v3 v3Var, double[] dArr) {
        super(spliterator, v3Var, dArr.length);
        this.f18802h = dArr;
    }

    public n3(n3 n3Var, Spliterator spliterator, long j10, long j11) {
        super(n3Var, spliterator, j10, j11, n3Var.f18802h.length);
        this.f18802h = n3Var.f18802h;
    }

    @Override // j$.util.stream.r3
    public final r3 a(Spliterator spliterator, long j10, long j11) {
        return new n3(this, spliterator, j10, j11);
    }

    @Override // j$.util.stream.r3, j$.util.stream.l5
    public final void accept(double d4) {
        int i10 = this.f18853f;
        if (i10 >= this.f18854g) {
            throw new IndexOutOfBoundsException(Integer.toString(this.f18853f));
        }
        double[] dArr = this.f18802h;
        this.f18853f = i10 + 1;
        dArr[i10] = d4;
    }
}
