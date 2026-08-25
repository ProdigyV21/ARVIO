package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class d6 extends y implements q8 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18666s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d6(a aVar, int i10, int i11) {
        super(aVar, i10);
        this.f18666s = i11;
    }

    @Override // j$.util.stream.a
    public Spliterator N0(a aVar, Spliterator spliterator) {
        switch (this.f18666s) {
            case 1:
                if (!y6.ORDERED.r(aVar.f18607m)) {
                    return new u8((j$.util.e0) aVar.G0(spliterator), 1);
                }
                final int i10 = 0;
                return M0(aVar, spliterator, new IntFunction() { // from class: j$.util.stream.n8
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i11) {
                        switch (i10) {
                            case 0:
                                return new Double[i11];
                            default:
                                return new Double[i11];
                        }
                    }
                }).spliterator();
            case 2:
                if (!y6.ORDERED.r(aVar.f18607m)) {
                    return new u8((j$.util.e0) aVar.G0(spliterator), 0);
                }
                final int i11 = 1;
                return M0(aVar, spliterator, new IntFunction() { // from class: j$.util.stream.n8
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i112) {
                        switch (i11) {
                            case 0:
                                return new Double[i112];
                            default:
                                return new Double[i112];
                        }
                    }
                }).spliterator();
            default:
                return super.N0(aVar, spliterator);
        }
    }

    @Override // j$.util.stream.a
    public final g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        switch (this.f18666s) {
            case 0:
                if (y6.SORTED.r(((a) v3Var).f18607m)) {
                    return v3Var.j0(spliterator, false, intFunction);
                }
                double[] dArr = (double[]) ((a2) v3Var.j0(spliterator, true, intFunction)).b();
                Arrays.sort(dArr);
                return new s2(dArr);
            case 1:
                return (g2) new t8(this, v3Var, spliterator, intFunction).invoke();
            default:
                return (g2) new s8(this, v3Var, spliterator, intFunction).invoke();
        }
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18666s) {
            case 0:
                Objects.requireNonNull(l5Var);
                if (y6.SORTED.r(i10)) {
                    return l5Var;
                }
                return y6.SIZED.r(i10) ? new i6(l5Var) : new a6(l5Var);
            case 1:
                return new o8(this, l5Var);
            default:
                return new p8(this, l5Var, false);
        }
    }

    @Override // j$.util.stream.q8
    public r8 g(y1 y1Var, boolean z) {
        return new p8(this, y1Var, z);
    }
}
