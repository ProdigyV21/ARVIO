package j$.util.stream;

import j$.util.function.Consumer$CC;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class j4 extends r4 implements q4 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Supplier f18746b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ BiConsumer f18747c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ BiConsumer f18748d;

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

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
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
        this.f18748d.accept(this.f18855a, ((j4) q4Var).f18855a);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18855a = this.f18746b.get();
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.f18747c.accept(this.f18855a, obj);
    }

    public j4(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
        this.f18746b = supplier;
        this.f18747c = biConsumer;
        this.f18748d = biConsumer2;
    }
}
