package j$.util.stream;

import j$.util.Objects;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class y4 extends h5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18958b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f18959c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f18960d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a f18961e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y4(u0 u0Var, l5 l5Var) {
        super(l5Var);
        this.f18961e = u0Var;
        l5 l5Var2 = this.f18711a;
        Objects.requireNonNull(l5Var2);
        this.f18960d = new j$.util.s(l5Var2, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y4(s sVar, l5 l5Var) {
        super(l5Var);
        this.f18961e = sVar;
        l5 l5Var2 = this.f18711a;
        Objects.requireNonNull(l5Var2);
        this.f18960d = new j$.util.o(l5Var2, 1);
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final void c(long j10) {
        switch (this.f18958b) {
            case 0:
                this.f18711a.c(-1L);
                break;
            case 1:
                this.f18711a.c(-1L);
                break;
            default:
                this.f18711a.c(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) throws Exception {
        switch (this.f18958b) {
            case 0:
                j$.util.w wVar = (j$.util.w) this.f18960d;
                m1 m1Var = (m1) ((j$.util.function.i) ((f1) this.f18961e).f18687t).apply(obj);
                if (m1Var != null) {
                    try {
                        if (!this.f18959c) {
                            m1Var.sequential().forEach(wVar);
                        } else {
                            j$.util.k0 k0VarSpliterator = m1Var.sequential().spliterator();
                            while (!this.f18711a.f() && k0VarSpliterator.tryAdvance((LongConsumer) wVar)) {
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            m1Var.close();
                            break;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                    break;
                }
                if (m1Var != null) {
                    m1Var.close();
                    return;
                }
                return;
            case 1:
                j$.util.s sVar = (j$.util.s) this.f18960d;
                IntStream intStream = (IntStream) ((j$.util.function.i) ((u0) this.f18961e).f18902t).apply(obj);
                if (intStream != null) {
                    try {
                        if (!this.f18959c) {
                            intStream.sequential().forEach(sVar);
                        } else {
                            j$.util.h0 h0VarSpliterator = intStream.sequential().spliterator();
                            while (!this.f18711a.f() && h0VarSpliterator.tryAdvance((IntConsumer) sVar)) {
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            intStream.close();
                            break;
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                        throw th3;
                    }
                    break;
                }
                if (intStream != null) {
                    intStream.close();
                    return;
                }
                return;
            default:
                j$.util.o oVar = (j$.util.o) this.f18960d;
                d0 d0Var = (d0) ((j$.util.function.i) ((s) this.f18961e).f18860t).apply(obj);
                if (d0Var != null) {
                    try {
                        if (!this.f18959c) {
                            d0Var.sequential().forEach(oVar);
                        } else {
                            j$.util.e0 e0VarSpliterator = d0Var.sequential().spliterator();
                            while (!this.f18711a.f() && e0VarSpliterator.tryAdvance((DoubleConsumer) oVar)) {
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            d0Var.close();
                            break;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                        }
                        throw th5;
                    }
                    break;
                }
                if (d0Var != null) {
                    d0Var.close();
                    return;
                }
                return;
        }
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final boolean f() {
        switch (this.f18958b) {
            case 0:
                this.f18959c = true;
                break;
            case 1:
                this.f18959c = true;
                break;
            default:
                this.f18959c = true;
                break;
        }
        return this.f18711a.f();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y4(f1 f1Var, l5 l5Var) {
        super(l5Var);
        this.f18961e = f1Var;
        l5 l5Var2 = this.f18711a;
        Objects.requireNonNull(l5Var2);
        this.f18960d = new j$.util.w(l5Var2, 1);
    }
}
