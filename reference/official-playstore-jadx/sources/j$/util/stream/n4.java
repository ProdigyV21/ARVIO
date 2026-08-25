package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class n4 extends r4 implements q4, j5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Supplier f18803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ObjIntConsumer f18804c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ p f18805d;

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        d((Integer) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.j5
    public final /* synthetic */ void d(Integer num) {
        v3.F(this, num);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        this.f18855a = this.f18805d.apply(this.f18855a, ((n4) q4Var).f18855a);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18855a = this.f18803b.get();
    }

    @Override // j$.util.stream.l5
    public final void accept(int i10) {
        this.f18804c.accept(this.f18855a, i10);
    }

    public n4(Supplier supplier, ObjIntConsumer objIntConsumer, p pVar) {
        this.f18803b = supplier;
        this.f18804c = objIntConsumer;
        this.f18805d = pVar;
    }
}
