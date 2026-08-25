package j$.util.stream;

import j$.util.Objects;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class e1 extends g5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18675b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j$.util.w f18676c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f1 f18677d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e1(f1 f1Var, l5 l5Var) {
        super(l5Var);
        this.f18677d = f1Var;
        l5 l5Var2 = this.f18699a;
        Objects.requireNonNull(l5Var2);
        this.f18676c = new j$.util.w(l5Var2, 1);
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18699a.c(-1L);
    }

    @Override // j$.util.stream.k5, j$.util.stream.l5
    public final void accept(long j10) throws Exception {
        m1 m1Var = (m1) ((j$.util.function.i) this.f18677d.f18687t).apply(j10);
        if (m1Var != null) {
            try {
                boolean z = this.f18675b;
                j$.util.w wVar = this.f18676c;
                if (!z) {
                    m1Var.sequential().forEach(wVar);
                } else {
                    j$.util.k0 k0VarSpliterator = m1Var.sequential().spliterator();
                    while (!this.f18699a.f() && k0VarSpliterator.tryAdvance((LongConsumer) wVar)) {
                    }
                }
            } catch (Throwable th) {
                try {
                    m1Var.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (m1Var != null) {
            m1Var.close();
        }
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final boolean f() {
        this.f18675b = true;
        return this.f18699a.f();
    }
}
