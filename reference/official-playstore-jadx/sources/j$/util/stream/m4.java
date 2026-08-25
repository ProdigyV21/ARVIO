package j$.util.stream;

import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m4 implements q4, j5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ IntBinaryOperator f18787c;

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

    public m4(IntBinaryOperator intBinaryOperator) {
        this.f18787c = intBinaryOperator;
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        m4 m4Var = (m4) q4Var;
        if (m4Var.f18785a) {
            return;
        }
        accept(m4Var.f18786b);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18785a = true;
        this.f18786b = 0;
    }

    @Override // j$.util.stream.l5
    public final void accept(int i10) {
        if (this.f18785a) {
            this.f18785a = false;
            this.f18786b = i10;
        } else {
            this.f18786b = this.f18787c.applyAsInt(this.f18786b, i10);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f18785a ? j$.util.m.f18572c : new j$.util.m(this.f18786b);
    }
}
