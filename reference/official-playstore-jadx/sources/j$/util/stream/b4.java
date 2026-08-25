package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class b4 implements q4, i5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f18639b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ DoubleBinaryOperator f18640c;

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        v3.J();
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
        n((Double) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.i5
    public final /* synthetic */ void n(Double d4) {
        v3.D(this, d4);
    }

    public b4(DoubleBinaryOperator doubleBinaryOperator) {
        this.f18640c = doubleBinaryOperator;
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        b4 b4Var = (b4) q4Var;
        if (b4Var.f18638a) {
            return;
        }
        accept(b4Var.f18639b);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18638a = true;
        this.f18639b = 0.0d;
    }

    @Override // j$.util.stream.l5
    public final void accept(double d4) {
        if (this.f18638a) {
            this.f18638a = false;
            this.f18639b = d4;
        } else {
            this.f18639b = this.f18640c.applyAsDouble(this.f18639b, d4);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f18638a ? j$.util.l.f18568c : new j$.util.l(this.f18639b);
    }
}
