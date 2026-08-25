package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class t5 extends y {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ long f18882s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ long f18883t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t5(a0 a0Var, int i10, long j10, long j11) {
        super(a0Var, i10);
        this.f18882s = j10;
        this.f18883t = j11;
    }

    @Override // j$.util.stream.a
    public final Spliterator N0(a aVar, Spliterator spliterator) {
        long jK0 = aVar.k0(spliterator);
        if (jK0 > 0 && spliterator.hasCharacteristics(16384)) {
            j$.util.e0 e0Var = (j$.util.e0) aVar.G0(spliterator);
            long j10 = this.f18882s;
            return new o7(e0Var, j10, v3.Z(j10, this.f18883t));
        }
        if (y6.ORDERED.r(aVar.f18607m)) {
            return ((g2) new v5(this, aVar, spliterator, new b1(23), this.f18882s, this.f18883t).invoke()).spliterator();
        }
        j$.util.e0 e0Var2 = (j$.util.e0) aVar.G0(spliterator);
        long j11 = this.f18882s;
        long j12 = this.f18883t;
        if (j11 <= jK0) {
            long jMin = jK0 - j11;
            if (j12 >= 0) {
                jMin = Math.min(j12, jMin);
            }
            j12 = jMin;
            j11 = 0;
        }
        return new u7(e0Var2, j11, j12);
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
            return v3.b0(v3Var, v3.X(aVar.L0(), spliterator, this.f18882s, this.f18883t), true);
        }
        if (!y6.ORDERED.r(((a) v3Var).f18607m)) {
            j$.util.e0 e0Var = (j$.util.e0) v3Var.G0(spliterator);
            long j11 = this.f18882s;
            long j12 = this.f18883t;
            if (j11 <= jK0) {
                long j13 = jK0 - j11;
                jMin = j12 >= 0 ? Math.min(j12, j13) : j13;
                j10 = 0;
            } else {
                jMin = j12;
                j10 = j11;
            }
            return v3.b0(this, new u7(e0Var, j10, jMin), true);
        }
        return (g2) new v5(this, v3Var, spliterator, intFunction, this.f18882s, this.f18883t).invoke();
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        return new s5(this, l5Var);
    }
}
