package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class v5 extends b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a f18925j;
    public final IntFunction k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f18926l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f18927m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f18928n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile boolean f18929o;

    @Override // j$.util.stream.b
    public final void f() {
        this.f18632i = true;
        if (this.f18929o) {
            d(v3.i0(this.f18925j.L0()));
        }
    }

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        v5 v5Var;
        g2 g2VarI0;
        d dVar = this.f18659d;
        if (dVar != null) {
            this.f18928n = ((v5) dVar).f18928n + ((v5) this.f18660e).f18928n;
            if (this.f18632i) {
                this.f18928n = 0L;
                g2VarI0 = v3.i0(this.f18925j.L0());
            } else {
                g2VarI0 = this.f18928n == 0 ? v3.i0(this.f18925j.L0()) : ((v5) this.f18659d).f18928n == 0 ? (g2) ((v5) this.f18660e).i() : v3.e0(this.f18925j.L0(), (g2) ((v5) this.f18659d).i(), (g2) ((v5) this.f18660e).i());
            }
            g2 g2VarH = g2VarI0;
            if (b()) {
                g2VarH = g2VarH.h(this.f18926l, this.f18927m >= 0 ? Math.min(g2VarH.count(), this.f18926l + this.f18927m) : this.f18928n, this.k);
            }
            d(g2VarH);
            this.f18929o = true;
        }
        if (this.f18927m >= 0 && !b()) {
            long j10 = this.f18926l + this.f18927m;
            long j11 = this.f18929o ? this.f18928n : j(j10);
            if (j11 >= j10) {
                g();
            } else {
                v5 v5Var2 = (v5) ((d) getCompleter());
                Object obj = this;
                while (true) {
                    if (v5Var2 == null) {
                        if (j11 >= j10) {
                            break;
                        }
                    } else {
                        if (obj == v5Var2.f18660e && (v5Var = (v5) v5Var2.f18659d) != null) {
                            long j12 = v5Var.j(j10) + j11;
                            if (j12 >= j10) {
                                break;
                            } else {
                                j11 = j12;
                            }
                        }
                        obj = v5Var2;
                        v5Var2 = (v5) ((d) v5Var2.getCompleter());
                    }
                }
                g();
            }
        }
        super.onCompletion(countedCompleter);
    }

    public v5(a aVar, v3 v3Var, Spliterator spliterator, IntFunction intFunction, long j10, long j11) {
        super(v3Var, spliterator);
        this.f18925j = aVar;
        this.k = intFunction;
        this.f18926l = j10;
        this.f18927m = j11;
    }

    public v5(v5 v5Var, Spliterator spliterator) {
        super(v5Var, spliterator);
        this.f18925j = v5Var.f18925j;
        this.k = v5Var.k;
        this.f18926l = v5Var.f18926l;
        this.f18927m = v5Var.f18927m;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new v5(this, spliterator);
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return v3.i0(this.f18925j.L0());
    }

    @Override // j$.util.stream.d
    public final Object a() {
        if (b()) {
            y6 y6Var = y6.SIZED;
            a aVar = this.f18925j;
            int i10 = aVar.f18605j;
            int i11 = y6Var.f18983e;
            y1 y1VarA0 = this.f18925j.A0((i10 & i11) == i11 ? aVar.k0(this.f18657b) : -1L, this.k);
            l5 l5VarP0 = this.f18925j.P0(((a) this.f18656a).f18607m, y1VarA0);
            v3 v3Var = this.f18656a;
            v3Var.g0(this.f18657b, v3Var.F0(l5VarP0));
            return y1VarA0.build();
        }
        y1 y1VarA02 = this.f18925j.A0(-1L, this.k);
        if (this.f18926l == 0) {
            l5 l5VarP02 = this.f18925j.P0(((a) this.f18656a).f18607m, y1VarA02);
            v3 v3Var2 = this.f18656a;
            v3Var2.g0(this.f18657b, v3Var2.F0(l5VarP02));
        } else {
            this.f18656a.E0(this.f18657b, y1VarA02);
        }
        g2 g2VarBuild = y1VarA02.build();
        this.f18928n = g2VarBuild.count();
        this.f18929o = true;
        this.f18657b = null;
        return g2VarBuild;
    }

    public final long j(long j10) {
        if (this.f18929o) {
            return this.f18928n;
        }
        v5 v5Var = (v5) this.f18659d;
        v5 v5Var2 = (v5) this.f18660e;
        if (v5Var == null || v5Var2 == null) {
            return this.f18928n;
        }
        long j11 = v5Var.j(j10);
        return j11 >= j10 ? j11 : v5Var2.j(j10) + j11;
    }
}
