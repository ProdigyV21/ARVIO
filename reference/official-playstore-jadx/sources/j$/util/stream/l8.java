package j$.util.stream;

import java.util.function.LongPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class l8 extends g5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f18778b;

    public l8(f6 f6Var, l5 l5Var) {
        super(l5Var);
        this.f18778b = true;
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18699a.c(-1L);
    }

    @Override // j$.util.stream.k5, j$.util.stream.l5
    public final void accept(long j10) {
        if (this.f18778b) {
            LongPredicate longPredicate = null;
            longPredicate.test(j10);
            throw null;
        }
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final boolean f() {
        return !this.f18778b || this.f18699a.f();
    }
}
