package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class f6 extends h1 implements q8 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18692s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f6(a aVar, int i10, int i11) {
        super(aVar, i10);
        this.f18692s = i11;
    }

    @Override // j$.util.stream.a
    public Spliterator N0(a aVar, Spliterator spliterator) {
        switch (this.f18692s) {
            case 1:
                return y6.ORDERED.r(aVar.f18607m) ? M0(aVar, spliterator, new b1(28)).spliterator() : new w8((j$.util.k0) aVar.G0(spliterator), 1);
            case 2:
                return y6.ORDERED.r(aVar.f18607m) ? M0(aVar, spliterator, new b1(29)).spliterator() : new w8((j$.util.k0) aVar.G0(spliterator), 0);
            default:
                return super.N0(aVar, spliterator);
        }
    }

    @Override // j$.util.stream.a
    public final g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        switch (this.f18692s) {
            case 0:
                if (y6.SORTED.r(((a) v3Var).f18607m)) {
                    return v3Var.j0(spliterator, false, intFunction);
                }
                long[] jArr = (long[]) ((e2) v3Var.j0(spliterator, true, intFunction)).b();
                Arrays.sort(jArr);
                return new k3(jArr);
            case 1:
                return (g2) new t8(this, v3Var, spliterator, intFunction).invoke();
            default:
                return (g2) new s8(this, v3Var, spliterator, intFunction).invoke();
        }
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18692s) {
            case 0:
                Objects.requireNonNull(l5Var);
                if (y6.SORTED.r(i10)) {
                    return l5Var;
                }
                return y6.SIZED.r(i10) ? new k6(l5Var) : new c6(l5Var);
            case 1:
                return new l8(this, l5Var);
            default:
                return new m8(this, l5Var, false);
        }
    }

    @Override // j$.util.stream.q8
    public r8 g(y1 y1Var, boolean z) {
        return new m8(this, y1Var, z);
    }
}
