package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class g6 extends b5 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f18700s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Comparator f18701t;

    public g6(d5 d5Var) {
        super(d5Var, y6.f18973q | y6.f18971o);
        this.f18700s = true;
        this.f18701t = j$.util.e.INSTANCE;
    }

    public g6(d5 d5Var, Comparator comparator) {
        super(d5Var, y6.f18973q | y6.f18972p);
        this.f18700s = false;
        this.f18701t = (Comparator) Objects.requireNonNull(comparator);
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        Objects.requireNonNull(l5Var);
        if (y6.SORTED.r(i10) && this.f18700s) {
            return l5Var;
        }
        if (y6.SIZED.r(i10)) {
            return new l6(l5Var, this.f18701t);
        }
        return new h6(l5Var, this.f18701t);
    }

    @Override // j$.util.stream.a
    public final g2 M0(v3 v3Var, Spliterator spliterator, IntFunction intFunction) {
        if (y6.SORTED.r(((a) v3Var).f18607m) && this.f18700s) {
            return v3Var.j0(spliterator, false, intFunction);
        }
        Object[] objArrM = v3Var.j0(spliterator, true, intFunction).m(intFunction);
        Arrays.sort(objArrM, this.f18701t);
        return new j2(objArrM);
    }
}
