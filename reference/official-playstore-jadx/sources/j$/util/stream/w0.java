package j$.util.stream;

import j$.util.Objects;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class w0 extends f5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j$.util.s f18937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ u0 f18938d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(u0 u0Var, l5 l5Var) {
        super(l5Var);
        this.f18938d = u0Var;
        l5 l5Var2 = this.f18691a;
        Objects.requireNonNull(l5Var2);
        this.f18937c = new j$.util.s(l5Var2, 1);
    }

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18691a.c(-1L);
    }

    @Override // j$.util.stream.j5, j$.util.stream.l5
    public final void accept(int i10) throws Exception {
        IntStream intStream = (IntStream) ((l0) this.f18938d.f18902t).apply(i10);
        if (intStream != null) {
            try {
                boolean z = this.f18936b;
                j$.util.s sVar = this.f18937c;
                if (!z) {
                    intStream.sequential().forEach(sVar);
                } else {
                    j$.util.h0 h0VarSpliterator = intStream.sequential().spliterator();
                    while (!this.f18691a.f() && h0VarSpliterator.tryAdvance((IntConsumer) sVar)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    intStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (intStream != null) {
            intStream.close();
        }
    }

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final boolean f() {
        this.f18936b = true;
        return this.f18691a.f();
    }
}
