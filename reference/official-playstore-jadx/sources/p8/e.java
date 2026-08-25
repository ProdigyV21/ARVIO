package p8;

import com.google.common.util.concurrent.r0;
import g8.e1;
import g8.u0;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f21126l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f21127m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f21128n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final e f21129o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f21130p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e f21131q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final e f21132r;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21133i;

    static {
        int i10 = 1;
        f21126l = new e(i10, 0);
        f21127m = new e(i10, 1);
        f21128n = new e(i10, 2);
        f21129o = new e(i10, 3);
        f21130p = new e(i10, 4);
        f21131q = new e(i10, 5);
        f21132r = new e(i10, 6);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i10, int i11) {
        super(i10);
        this.f21133i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        g8.c cVarB;
        String strG;
        boolean z = false;
        switch (this.f21133i) {
            case 0:
                int i10 = f.f21135l;
                return Boolean.valueOf(kotlin.collections.x.f0(j0.f21162f, r0.g((g8.c) obj)));
            case 1:
                g8.c cVar = (g8.c) obj;
                if (cVar instanceof g8.w) {
                    int i11 = f.f21135l;
                    if (kotlin.collections.x.f0(j0.f21162f, r0.g(cVar))) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            case 2:
                return Boolean.valueOf(k2.c.t((g8.c) obj));
            case 3:
                return ((e1) obj).getType();
            case 4:
                return Boolean.valueOf(k2.c.t(m9.d.i((g8.c) obj)));
            case 5:
                int i12 = d.f21123l;
                u0 u0Var = (u0) ((g8.c) obj);
                if (d8.k.z(u0Var) && m9.d.b(u0Var, new ab.h(u0Var, 28)) != null) {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                g8.c cVar2 = (g8.c) obj;
                if (d8.k.z(cVar2)) {
                    int i13 = f.f21135l;
                    if (j0.f21161e.contains(cVar2.getName()) && (cVarB = m9.d.b(cVar2, f21127m)) != null && (strG = r0.g(cVarB)) != null) {
                        if (!j0.f21158b.contains(strG)) {
                        }
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
        }
    }
}
