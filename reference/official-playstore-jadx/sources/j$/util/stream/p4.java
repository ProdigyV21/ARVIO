package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p4 implements q4, k5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18826a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18827b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LongBinaryOperator f18828c;

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.k5
    public final /* synthetic */ void l(Long l10) {
        v3.H(this, l10);
    }

    public p4(LongBinaryOperator longBinaryOperator) {
        this.f18828c = longBinaryOperator;
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        p4 p4Var = (p4) q4Var;
        if (p4Var.f18826a) {
            return;
        }
        accept(p4Var.f18827b);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18826a = true;
        this.f18827b = 0L;
    }

    @Override // j$.util.stream.l5
    public final void accept(long j10) {
        if (this.f18826a) {
            this.f18826a = false;
            this.f18827b = j10;
        } else {
            this.f18827b = this.f18828c.applyAsLong(this.f18827b, j10);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f18826a ? j$.util.n.f18576c : new j$.util.n(this.f18827b);
    }
}
