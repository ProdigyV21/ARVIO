package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final class h8 extends b5 implements q8 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18717s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Predicate f18718t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h8(d5 d5Var, int i10, Predicate predicate, int i11) {
        super(d5Var, i10);
        this.f18717s = i11;
        this.f18718t = predicate;
    }

    @Override // j$.util.stream.a
    public final Spliterator N0(a aVar, Spliterator spliterator) {
        switch (this.f18717s) {
            case 0:
                return y6.ORDERED.r(aVar.f18607m) ? M0(aVar, spliterator, new b1(12)).spliterator() : new x8(aVar.G0(spliterator), this.f18718t, 1);
            default:
                return y6.ORDERED.r(aVar.f18607m) ? M0(aVar, spliterator, new b1(12)).spliterator() : new x8(aVar.G0(spliterator), this.f18718t, 0);
        }
    }

    @Override // j$.util.stream.a
    public final g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        switch (this.f18717s) {
            case 0:
                return (g2) new t8(this, v3Var, spliterator, intFunction).invoke();
            default:
                return (g2) new s8(this, v3Var, spliterator, intFunction).invoke();
        }
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18717s) {
            case 0:
                return new l(this, l5Var);
            default:
                return new i8(this, l5Var, false);
        }
    }

    @Override // j$.util.stream.q8
    public r8 g(y1 y1Var, boolean z) {
        return new i8(this, y1Var, z);
    }
}
