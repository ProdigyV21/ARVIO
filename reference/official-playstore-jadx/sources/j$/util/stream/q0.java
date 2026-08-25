package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q0 implements e8, f8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f18834a;

    public /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    public /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    public /* synthetic */ void accept(long j10) {
        v3.K();
        throw null;
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void c(long j10) {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    public q0(boolean z) {
        this.f18834a = z;
    }

    @Override // j$.util.stream.e8
    public final int t() {
        if (this.f18834a) {
            return 0;
        }
        return y6.f18974r;
    }

    public final void a(v3 v3Var, Spliterator spliterator) {
        if (this.f18834a) {
            new r0(v3Var, spliterator, this).invoke();
        } else {
            new s0(v3Var, spliterator, v3Var.F0(this)).invoke();
        }
    }
}
