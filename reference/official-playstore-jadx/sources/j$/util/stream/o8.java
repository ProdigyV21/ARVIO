package j$.util.stream;

import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class o8 extends e5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f18821b;

    public o8(d6 d6Var, l5 l5Var) {
        super(l5Var);
        this.f18821b = true;
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18680a.c(-1L);
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) {
        if (this.f18821b) {
            DoublePredicate doublePredicate = null;
            doublePredicate.test(d4);
            throw null;
        }
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final boolean f() {
        return !this.f18821b || this.f18680a.f();
    }
}
