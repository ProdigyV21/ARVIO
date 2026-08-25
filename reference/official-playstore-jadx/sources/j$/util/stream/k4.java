package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class k4 implements q4, j5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f18762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18763b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ IntBinaryOperator f18764c;

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

    public k4(int i10, IntBinaryOperator intBinaryOperator) {
        this.f18763b = i10;
        this.f18764c = intBinaryOperator;
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        accept(((k4) q4Var).f18762a);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18762a = this.f18763b;
    }

    @Override // j$.util.stream.l5
    public final void accept(int i10) {
        this.f18762a = this.f18764c.applyAsInt(this.f18762a, i10);
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Integer.valueOf(this.f18762a);
    }
}
