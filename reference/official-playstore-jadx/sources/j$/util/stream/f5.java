package j$.util.stream;

import j$.util.Objects;
import j$.util.function.Consumer$CC;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f5 implements j5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l5 f18691a;

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
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
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

    public f5(l5 l5Var) {
        this.f18691a = (l5) Objects.requireNonNull(l5Var);
    }

    @Override // j$.util.stream.l5
    public void c(long j10) {
        this.f18691a.c(j10);
    }

    @Override // j$.util.stream.l5
    public void end() {
        this.f18691a.end();
    }

    @Override // j$.util.stream.l5
    public boolean f() {
        return this.f18691a.f();
    }
}
