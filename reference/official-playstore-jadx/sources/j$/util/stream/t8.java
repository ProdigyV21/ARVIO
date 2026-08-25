package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class t8 extends b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a f18895j;
    public final IntFunction k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f18896l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f18897m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f18898n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile boolean f18899o;

    @Override // j$.util.stream.b
    public final void f() {
        this.f18632i = true;
        if (this.f18896l && this.f18899o) {
            d(v3.i0(this.f18895j.L0()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCompletion(java.util.concurrent.CountedCompleter r9) {
        /*
            r8 = this;
            j$.util.stream.d r0 = r8.f18659d
            if (r0 != 0) goto L6
            goto L8b
        L6:
            j$.util.stream.t8 r0 = (j$.util.stream.t8) r0
            boolean r0 = r0.f18898n
            j$.util.stream.d r1 = r8.f18660e
            j$.util.stream.t8 r1 = (j$.util.stream.t8) r1
            boolean r1 = r1.f18898n
            r0 = r0 | r1
            r8.f18898n = r0
            boolean r0 = r8.f18896l
            r1 = 0
            if (r0 == 0) goto L2a
            boolean r0 = r8.f18632i
            if (r0 == 0) goto L2a
            r8.f18897m = r1
            j$.util.stream.a r0 = r8.f18895j
            j$.util.stream.z6 r0 = r0.L0()
            j$.util.stream.z2 r0 = j$.util.stream.v3.i0(r0)
            goto L88
        L2a:
            boolean r0 = r8.f18896l
            if (r0 == 0) goto L41
            j$.util.stream.d r0 = r8.f18659d
            j$.util.stream.t8 r0 = (j$.util.stream.t8) r0
            boolean r3 = r0.f18898n
            if (r3 == 0) goto L41
            long r1 = r0.f18897m
            r8.f18897m = r1
            java.lang.Object r0 = r0.i()
            j$.util.stream.g2 r0 = (j$.util.stream.g2) r0
            goto L88
        L41:
            j$.util.stream.d r0 = r8.f18659d
            j$.util.stream.t8 r0 = (j$.util.stream.t8) r0
            long r3 = r0.f18897m
            j$.util.stream.d r5 = r8.f18660e
            j$.util.stream.t8 r5 = (j$.util.stream.t8) r5
            long r6 = r5.f18897m
            long r3 = r3 + r6
            r8.f18897m = r3
            long r3 = r0.f18897m
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 != 0) goto L5d
            java.lang.Object r0 = r5.i()
            j$.util.stream.g2 r0 = (j$.util.stream.g2) r0
            goto L88
        L5d:
            long r3 = r5.f18897m
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 != 0) goto L6a
            java.lang.Object r0 = r0.i()
            j$.util.stream.g2 r0 = (j$.util.stream.g2) r0
            goto L88
        L6a:
            j$.util.stream.a r0 = r8.f18895j
            j$.util.stream.z6 r0 = r0.L0()
            j$.util.stream.d r1 = r8.f18659d
            j$.util.stream.t8 r1 = (j$.util.stream.t8) r1
            java.lang.Object r1 = r1.i()
            j$.util.stream.g2 r1 = (j$.util.stream.g2) r1
            j$.util.stream.d r2 = r8.f18660e
            j$.util.stream.t8 r2 = (j$.util.stream.t8) r2
            java.lang.Object r2 = r2.i()
            j$.util.stream.g2 r2 = (j$.util.stream.g2) r2
            j$.util.stream.i2 r0 = j$.util.stream.v3.e0(r0, r1, r2)
        L88:
            r8.d(r0)
        L8b:
            r0 = 1
            r8.f18899o = r0
            super.onCompletion(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.t8.onCompletion(java.util.concurrent.CountedCompleter):void");
    }

    public t8(a aVar, v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        super(v3Var, spliterator);
        this.f18895j = aVar;
        this.k = intFunction;
        this.f18896l = y6.ORDERED.r(((a) v3Var).f18607m);
    }

    public t8(t8 t8Var, Spliterator spliterator) {
        super(t8Var, spliterator);
        this.f18895j = t8Var.f18895j;
        this.k = t8Var.k;
        this.f18896l = t8Var.f18896l;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new t8(this, spliterator);
    }

    @Override // j$.util.stream.b
    public final Object h() {
        return v3.i0(this.f18895j.L0());
    }

    @Override // j$.util.stream.d
    public final Object a() {
        y1 y1VarA0 = this.f18656a.A0(-1L, this.k);
        l5 l5VarP0 = this.f18895j.P0(((a) this.f18656a).f18607m, y1VarA0);
        v3 v3Var = this.f18656a;
        boolean zG0 = v3Var.g0(this.f18657b, v3Var.F0(l5VarP0));
        this.f18898n = zG0;
        if (zG0) {
            g();
        }
        g2 g2VarBuild = y1VarA0.build();
        this.f18897m = g2VarBuild.count();
        return g2VarBuild;
    }
}
