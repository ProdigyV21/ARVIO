package j$.time.chrono;

import j$.time.temporal.Temporal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class a0 implements j {
    public static final a0 BEFORE_ROC;
    public static final a0 ROC;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a0[] f18137a;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object G(j$.time.c cVar) {
        return j$.com.android.tools.r8.a.v(this, cVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ long H(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.o(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ boolean e(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.r(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.m(this, oVar);
    }

    public static a0 valueOf(String str) {
        return (a0) Enum.valueOf(a0.class, str);
    }

    public static a0[] values() {
        return (a0[]) f18137a.clone();
    }

    static {
        a0 a0Var = new a0("BEFORE_ROC", 0);
        BEFORE_ROC = a0Var;
        a0 a0Var2 = new a0("ROC", 1);
        ROC = a0Var2;
        f18137a = new a0[]{a0Var, a0Var2};
    }

    @Override // j$.time.chrono.j
    public final int getValue() {
        return ordinal();
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(getValue(), j$.time.temporal.a.ERA);
    }
}
