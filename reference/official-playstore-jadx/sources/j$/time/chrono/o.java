package j$.time.chrono;

import j$.time.temporal.Temporal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class o implements j {
    public static final o AH;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ o[] f18174a;

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

    @Override // j$.time.chrono.j
    public final int getValue() {
        return 1;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.m(this, oVar);
    }

    public static o valueOf(String str) {
        return (o) Enum.valueOf(o.class, str);
    }

    public static o[] values() {
        return (o[]) f18174a.clone();
    }

    static {
        o oVar = new o("AH", 0);
        AH = oVar;
        f18174a = new o[]{oVar};
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.ERA) {
            return j$.time.temporal.s.f(1L, 1L);
        }
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(1, j$.time.temporal.a.ERA);
    }
}
