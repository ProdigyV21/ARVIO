package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class r5 extends h1 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ long f18856s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ long f18857t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r5(j1 j1Var, int i10, long j10, long j11) {
        super(j1Var, i10);
        this.f18856s = j10;
        this.f18857t = j11;
    }

    @Override // j$.util.stream.a
    public final Spliterator N0(a aVar, Spliterator spliterator) {
        long jK0 = aVar.k0(spliterator);
        if (jK0 > 0 && spliterator.hasCharacteristics(16384)) {
            j$.util.k0 k0Var = (j$.util.k0) aVar.G0(spliterator);
            long j10 = this.f18856s;
            return new q7(k0Var, j10, v3.Z(j10, this.f18857t));
        }
        if (y6.ORDERED.r(aVar.f18607m)) {
            return ((g2) new v5(this, aVar, spliterator, new b1(22), this.f18856s, this.f18857t).invoke()).spliterator();
        }
        j$.util.k0 k0Var2 = (j$.util.k0) aVar.G0(spliterator);
        long j11 = this.f18856s;
        long j12 = this.f18857t;
        if (j11 <= jK0) {
            long jMin = jK0 - j11;
            if (j12 >= 0) {
                jMin = Math.min(j12, jMin);
            }
            j12 = jMin;
            j11 = 0;
        }
        return new w7(k0Var2, j11, j12);
    }

    @Override // j$.util.stream.a
    public final g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        long jMin;
        long j10;
        long jK0 = v3Var.k0(spliterator);
        if (jK0 > 0 && spliterator.hasCharacteristics(16384)) {
            a aVar = (a) v3Var;
            while (aVar.f18606l > 0) {
                aVar = aVar.f18604i;
            }
            return v3.d0(v3Var, v3.X(aVar.L0(), spliterator, this.f18856s, this.f18857t), true);
        }
        if (!y6.ORDERED.r(((a) v3Var).f18607m)) {
            j$.util.k0 k0Var = (j$.util.k0) v3Var.G0(spliterator);
            long j11 = this.f18856s;
            long j12 = this.f18857t;
            if (j11 <= jK0) {
                long j13 = jK0 - j11;
                jMin = j12 >= 0 ? Math.min(j12, j13) : j13;
                j10 = 0;
            } else {
                jMin = j12;
                j10 = j11;
            }
            return v3.d0(this, new w7(k0Var, j10, jMin), true);
        }
        return (g2) new v5(this, v3Var, spliterator, intFunction, this.f18856s, this.f18857t).invoke();
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        return new q5(this, l5Var);
    }
}
