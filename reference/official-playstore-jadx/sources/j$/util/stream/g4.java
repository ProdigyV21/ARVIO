package j$.util.stream;

import j$.util.Optional;
import j$.util.function.Consumer$CC;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class g4 implements q4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18697b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ BinaryOperator f18698c;

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

    public g4(BinaryOperator binaryOperator) {
        this.f18698c = binaryOperator;
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        g4 g4Var = (g4) q4Var;
        if (g4Var.f18696a) {
            return;
        }
        n(g4Var.f18697b);
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        this.f18696a = true;
        this.f18697b = null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        if (this.f18696a) {
            this.f18696a = false;
            this.f18697b = obj;
        } else {
            this.f18697b = this.f18698c.apply(this.f18697b, obj);
        }
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f18696a ? Optional.empty() : Optional.of(this.f18697b);
    }
}
