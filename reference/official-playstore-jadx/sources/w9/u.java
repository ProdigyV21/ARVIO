package w9;

import v9.d1;
import v9.m0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public abstract class u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final s f22530i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final q f22531l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t f22532m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final r f22533n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final /* synthetic */ u[] f22534o;

    static {
        s sVar = new s();
        f22530i = sVar;
        q qVar = new q();
        f22531l = qVar;
        t tVar = new t();
        f22532m = tVar;
        r rVar = new r();
        f22533n = rVar;
        f22534o = new u[]{sVar, qVar, tVar, rVar};
    }

    public static u d(d1 d1Var) {
        if (d1Var.J0()) {
            return f22531l;
        }
        if (d1Var instanceof v9.m) {
        }
        return v9.c.e(f.l(24, false), v9.c.h(d1Var), m0.f22225b) ? f22533n : f22532m;
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f22534o.clone();
    }

    public abstract u a(d1 d1Var);
}
