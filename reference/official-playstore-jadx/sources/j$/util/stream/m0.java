package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m0 extends q0 implements i5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DoubleConsumer f18781b;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        n((Double) obj);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.i5
    public final /* synthetic */ void n(Double d4) {
        v3.D(this, d4);
    }

    @Override // j$.util.stream.e8
    public final Object e(a aVar, Spliterator spliterator) {
        aVar.E0(spliterator, this);
        return null;
    }

    @Override // j$.util.stream.e8
    public final /* bridge */ /* synthetic */ Object h(v3 v3Var, Spliterator spliterator) {
        a(v3Var, spliterator);
        return null;
    }

    public m0(DoubleConsumer doubleConsumer, boolean z) {
        super(z);
        this.f18781b = doubleConsumer;
    }

    @Override // j$.util.stream.q0, j$.util.stream.l5
    public final void accept(double d4) {
        this.f18781b.accept(d4);
    }
}
