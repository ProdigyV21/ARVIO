package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends v3 implements g {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a f18603h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a f18604i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f18605j;
    public final a k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f18606l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f18607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Spliterator f18608n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f18609o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f18610p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Runnable f18611q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f18612r;

    public abstract g2 J0(a aVar, Spliterator spliterator, boolean z, IntFunction intFunction);

    public abstract boolean K0(Spliterator spliterator, l5 l5Var);

    public abstract z6 L0();

    public abstract boolean O0();

    public abstract l5 P0(int i10, l5 l5Var);

    public abstract Spliterator S0(a aVar, Supplier supplier, boolean z);

    public a(Spliterator spliterator, int i10, boolean z) {
        this.f18604i = null;
        this.f18608n = spliterator;
        this.f18603h = this;
        int i11 = y6.f18964g & i10;
        this.f18605j = i11;
        this.f18607m = (~(i11 << 1)) & y6.f18968l;
        this.f18606l = 0;
        this.f18612r = z;
    }

    public a(a aVar, int i10) {
        if (aVar.f18609o) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        aVar.f18609o = true;
        aVar.k = this;
        this.f18604i = aVar;
        this.f18605j = y6.f18965h & i10;
        this.f18607m = y6.l(i10, aVar.f18607m);
        a aVar2 = aVar.f18603h;
        this.f18603h = aVar2;
        if (O0()) {
            aVar2.f18610p = true;
        }
        this.f18606l = aVar.f18606l + 1;
    }

    public final Object H0(e8 e8Var) {
        if (this.f18609o) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.f18609o = true;
        if (this.f18603h.f18612r) {
            return e8Var.h(this, Q0(e8Var.t()));
        }
        return e8Var.e(this, Q0(e8Var.t()));
    }

    public final g2 I0(IntFunction intFunction) {
        if (this.f18609o) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.f18609o = true;
        if (this.f18603h.f18612r && this.f18604i != null && O0()) {
            this.f18606l = 0;
            a aVar = this.f18604i;
            return M0(aVar, aVar.Q0(0), intFunction);
        }
        return j0(Q0(0), true, intFunction);
    }

    public final Spliterator R0() {
        a aVar = this.f18603h;
        if (this != aVar) {
            throw new IllegalStateException();
        }
        if (this.f18609o) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.f18609o = true;
        Spliterator spliterator = aVar.f18608n;
        if (spliterator != null) {
            aVar.f18608n = null;
            return spliterator;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    @Override // j$.util.stream.g
    public final g sequential() {
        this.f18603h.f18612r = false;
        return this;
    }

    @Override // j$.util.stream.g
    public final g parallel() {
        this.f18603h.f18612r = true;
        return this;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.f18609o = true;
        this.f18608n = null;
        a aVar = this.f18603h;
        Runnable runnable = aVar.f18611q;
        if (runnable != null) {
            aVar.f18611q = null;
            runnable.run();
        }
    }

    @Override // j$.util.stream.g
    public final g onClose(Runnable runnable) {
        if (this.f18609o) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        Objects.requireNonNull(runnable);
        a aVar = this.f18603h;
        Runnable runnable2 = aVar.f18611q;
        if (runnable2 != null) {
            runnable = new d8(runnable2, runnable);
        }
        aVar.f18611q = runnable;
        return this;
    }

    @Override // j$.util.stream.g
    public Spliterator spliterator() {
        if (this.f18609o) {
            throw new IllegalStateException("stream has already been operated upon or closed");
        }
        this.f18609o = true;
        a aVar = this.f18603h;
        if (this == aVar) {
            Spliterator spliterator = aVar.f18608n;
            if (spliterator != null) {
                aVar.f18608n = null;
                return spliterator;
            }
            throw new IllegalStateException("source already consumed or closed");
        }
        return S0(this, new j$.util.function.i(this, 1), aVar.f18612r);
    }

    @Override // j$.util.stream.g
    public final boolean isParallel() {
        return this.f18603h.f18612r;
    }

    @Override // j$.util.stream.v3
    public final g2 j0(Spliterator spliterator, boolean z, IntFunction intFunction) {
        if (this.f18603h.f18612r) {
            return J0(this, spliterator, z, intFunction);
        }
        y1 y1VarA0 = A0(k0(spliterator), intFunction);
        E0(spliterator, y1VarA0);
        return y1VarA0.build();
    }

    public final Spliterator Q0(int i10) {
        int i11;
        int i12;
        a aVar = this.f18603h;
        Spliterator spliteratorN0 = aVar.f18608n;
        if (spliteratorN0 != null) {
            aVar.f18608n = null;
            if (aVar.f18612r && aVar.f18610p) {
                a aVar2 = aVar.k;
                int i13 = 1;
                while (aVar != this) {
                    int i14 = aVar2.f18605j;
                    if (aVar2.O0()) {
                        if (y6.SHORT_CIRCUIT.r(i14)) {
                            i14 &= ~y6.f18977u;
                        }
                        spliteratorN0 = aVar2.N0(aVar, spliteratorN0);
                        if (spliteratorN0.hasCharacteristics(64)) {
                            i11 = (~y6.f18976t) & i14;
                            i12 = y6.f18975s;
                        } else {
                            i11 = (~y6.f18975s) & i14;
                            i12 = y6.f18976t;
                        }
                        i14 = i11 | i12;
                        i13 = 0;
                    }
                    int i15 = i13 + 1;
                    aVar2.f18606l = i13;
                    aVar2.f18607m = y6.l(i14, aVar.f18607m);
                    a aVar3 = aVar2;
                    aVar2 = aVar2.k;
                    aVar = aVar3;
                    i13 = i15;
                }
            }
            if (i10 != 0) {
                this.f18607m = y6.l(i10, this.f18607m);
            }
            return spliteratorN0;
        }
        throw new IllegalStateException("source already consumed or closed");
    }

    @Override // j$.util.stream.v3
    public final long k0(Spliterator spliterator) {
        if (y6.SIZED.r(this.f18607m)) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1L;
    }

    @Override // j$.util.stream.v3
    public final l5 E0(Spliterator spliterator, l5 l5Var) {
        f0(spliterator, F0((l5) Objects.requireNonNull(l5Var)));
        return l5Var;
    }

    @Override // j$.util.stream.v3
    public final void f0(Spliterator spliterator, l5 l5Var) {
        Objects.requireNonNull(l5Var);
        if (!y6.SHORT_CIRCUIT.r(this.f18607m)) {
            l5Var.c(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(l5Var);
            l5Var.end();
            return;
        }
        g0(spliterator, l5Var);
    }

    @Override // j$.util.stream.v3
    public final boolean g0(Spliterator spliterator, l5 l5Var) {
        a aVar = this;
        while (aVar.f18606l > 0) {
            aVar = aVar.f18604i;
        }
        l5Var.c(spliterator.getExactSizeIfKnown());
        boolean zK0 = aVar.K0(spliterator, l5Var);
        l5Var.end();
        return zK0;
    }

    @Override // j$.util.stream.v3
    public final l5 F0(l5 l5Var) {
        Objects.requireNonNull(l5Var);
        for (a aVar = this; aVar.f18606l > 0; aVar = aVar.f18604i) {
            l5Var = aVar.P0(aVar.f18604i.f18607m, l5Var);
        }
        return l5Var;
    }

    @Override // j$.util.stream.v3
    public final Spliterator G0(Spliterator spliterator) {
        return this.f18606l == 0 ? spliterator : S0(this, new j$.util.function.i(spliterator, 2), this.f18603h.f18612r);
    }

    public g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    public Spliterator N0(a aVar, Spliterator spliterator) {
        return M0(aVar, spliterator, new j$.time.c(14)).spliterator();
    }
}
