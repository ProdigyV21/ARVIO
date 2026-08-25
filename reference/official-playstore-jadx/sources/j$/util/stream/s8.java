package j$.util.stream;

import j$.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class s8 extends d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a f18872h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final IntFunction f18873i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f18874j;
    public long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f18875l;

    @Override // j$.util.stream.d, java.util.concurrent.CountedCompleter
    public final void onCompletion(CountedCompleter countedCompleter) {
        d dVar = this.f18659d;
        if (dVar != null) {
            if (this.f18874j) {
                s8 s8Var = (s8) dVar;
                long j10 = s8Var.f18875l;
                this.f18875l = j10;
                if (j10 == s8Var.k) {
                    this.f18875l = j10 + ((s8) this.f18660e).f18875l;
                }
            }
            s8 s8Var2 = (s8) dVar;
            long j11 = s8Var2.k;
            s8 s8Var3 = (s8) this.f18660e;
            this.k = j11 + s8Var3.k;
            g2 g2VarE0 = s8Var2.k == 0 ? (g2) s8Var3.f18661f : s8Var3.k == 0 ? (g2) s8Var2.f18661f : v3.e0(this.f18872h.L0(), (g2) ((s8) this.f18659d).f18661f, (g2) ((s8) this.f18660e).f18661f);
            if (b() && this.f18874j) {
                g2VarE0 = g2VarE0.h(this.f18875l, g2VarE0.count(), this.f18873i);
            }
            this.f18661f = g2VarE0;
        }
        super.onCompletion(countedCompleter);
    }

    public s8(a aVar, v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        super(v3Var, spliterator);
        this.f18872h = aVar;
        this.f18873i = intFunction;
        this.f18874j = y6.ORDERED.r(((a) v3Var).f18607m);
    }

    public s8(s8 s8Var, Spliterator spliterator) {
        super(s8Var, spliterator);
        this.f18872h = s8Var.f18872h;
        this.f18873i = s8Var.f18873i;
        this.f18874j = s8Var.f18874j;
    }

    @Override // j$.util.stream.d
    public final d c(Spliterator spliterator) {
        return new s8(this, spliterator);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c  */
    @Override // j$.util.stream.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a() {
        /*
            r5 = this;
            boolean r0 = r5.b()
            if (r0 != 0) goto L1c
            boolean r1 = r5.f18874j
            if (r1 == 0) goto L1c
            j$.util.stream.y6 r1 = j$.util.stream.y6.SIZED
            j$.util.stream.a r2 = r5.f18872h
            int r3 = r2.f18605j
            int r1 = r1.f18983e
            r3 = r3 & r1
            if (r3 != r1) goto L1c
            j$.util.Spliterator r1 = r5.f18657b
            long r1 = r2.k0(r1)
            goto L1e
        L1c:
            r1 = -1
        L1e:
            j$.util.stream.v3 r3 = r5.f18656a
            java.util.function.IntFunction r4 = r5.f18873i
            j$.util.stream.y1 r1 = r3.A0(r1, r4)
            j$.util.stream.a r2 = r5.f18872h
            j$.util.stream.q8 r2 = (j$.util.stream.q8) r2
            boolean r3 = r5.f18874j
            if (r3 == 0) goto L32
            if (r0 != 0) goto L32
            r0 = 1
            goto L33
        L32:
            r0 = 0
        L33:
            j$.util.stream.r8 r0 = r2.g(r1, r0)
            j$.util.stream.v3 r2 = r5.f18656a
            j$.util.Spliterator r3 = r5.f18657b
            r2.E0(r3, r0)
            j$.util.stream.g2 r1 = r1.build()
            long r2 = r1.count()
            r5.k = r2
            long r2 = r0.i()
            r5.f18875l = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.stream.s8.a():java.lang.Object");
    }
}
