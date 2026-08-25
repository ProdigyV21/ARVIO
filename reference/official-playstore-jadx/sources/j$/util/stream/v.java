package j$.util.stream;

import j$.util.Objects;
import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class v extends e5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j$.util.o f18915c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ s f18916d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(s sVar, l5 l5Var) {
        super(l5Var);
        this.f18916d = sVar;
        l5 l5Var2 = this.f18680a;
        Objects.requireNonNull(l5Var2);
        this.f18915c = new j$.util.o(l5Var2, 1);
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18680a.c(-1L);
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) throws Exception {
        d0 d0Var = (d0) ((j$.util.function.i) this.f18916d.f18860t).apply(d4);
        if (d0Var != null) {
            try {
                boolean z = this.f18914b;
                j$.util.o oVar = this.f18915c;
                if (!z) {
                    d0Var.sequential().forEach(oVar);
                } else {
                    j$.util.e0 e0VarSpliterator = d0Var.sequential().spliterator();
                    while (!this.f18680a.f() && e0VarSpliterator.tryAdvance((DoubleConsumer) oVar)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    d0Var.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (d0Var != null) {
            d0Var.close();
        }
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final boolean f() {
        this.f18914b = true;
        return this.f18680a.f();
    }
}
