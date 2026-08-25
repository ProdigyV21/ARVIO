package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class n0 extends q0 implements j5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final IntConsumer f18799b;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        d((Integer) obj);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.j5
    public final /* synthetic */ void d(Integer num) {
        v3.F(this, num);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
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

    public n0(IntConsumer intConsumer, boolean z) {
        super(z);
        this.f18799b = intConsumer;
    }

    @Override // j$.util.stream.q0, j$.util.stream.l5
    public final void accept(int i10) {
        this.f18799b.accept(i10);
    }
}
