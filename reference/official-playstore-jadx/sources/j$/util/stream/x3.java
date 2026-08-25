package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class x3 extends r4 implements q4, k5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Supplier f18947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ObjLongConsumer f18948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ p f18949d;

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

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        this.f18855a = this.f18949d.apply(this.f18855a, ((x3) q4Var).f18855a);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18855a = this.f18947b.get();
    }

    @Override // j$.util.stream.l5
    public final void accept(long j10) {
        this.f18948c.accept(this.f18855a, j10);
    }

    public x3(Supplier supplier, ObjLongConsumer objLongConsumer, p pVar) {
        this.f18947b = supplier;
        this.f18948c = objLongConsumer;
        this.f18949d = pVar;
    }
}
