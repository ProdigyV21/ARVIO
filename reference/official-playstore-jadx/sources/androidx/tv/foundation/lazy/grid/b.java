package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final kotlin.jvm.internal.r f5147i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f5148l = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5149m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public g0 f5150n;

    /* JADX WARN: Multi-variable type inference failed */
    public b(r7.p pVar) {
        this.f5147i = (kotlin.jvm.internal.r) pVar;
    }

    /* JADX WARN: Type inference failed for: r6v4, types: [kotlin.jvm.internal.r, r7.p] */
    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        Density density = (Density) obj;
        long value = ((Constraints) obj2).getValue();
        if (this.f5150n != null && Constraints.m5639equalsimpl0(this.f5148l, value) && this.f5149m == density.getDensity()) {
            return this.f5150n;
        }
        this.f5148l = value;
        this.f5149m = density.getDensity();
        g0 g0Var = (g0) this.f5147i.invoke(density, Constraints.m5634boximpl(value));
        this.f5150n = g0Var;
        return g0Var;
    }
}
