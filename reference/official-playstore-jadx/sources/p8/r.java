package p8;

import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g8.p f21176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g8.p f21177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g8.p f21178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashMap f21179d;

    static {
        k8.a aVar = k8.a.f19522d;
        g8.p pVar = new g8.p(aVar, 9);
        f21176a = pVar;
        k8.a aVar2 = k8.a.f19524f;
        g8.p pVar2 = new g8.p(aVar2, 10);
        f21177b = pVar2;
        k8.a aVar3 = k8.a.f19523e;
        g8.p pVar3 = new g8.p(aVar3, 11);
        f21178c = pVar3;
        HashMap map = new HashMap();
        f21179d = map;
        map.put(aVar, pVar);
        map.put(aVar2, pVar2);
        map.put(aVar3, pVar3);
    }

    public static /* synthetic */ void a(int i10) {
        String str = (i10 == 5 || i10 == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 5 || i10 == 6) ? 2 : 3];
        switch (i10) {
            case 1:
                objArr[0] = "from";
                break;
            case 2:
                objArr[0] = "first";
                break;
            case 3:
                objArr[0] = "second";
                break;
            case 4:
                objArr[0] = "visibility";
                break;
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
                break;
            default:
                objArr[0] = "what";
                break;
        }
        if (i10 == 5 || i10 == 6) {
            objArr[1] = "toDescriptorVisibility";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
        }
        if (i10 == 2 || i10 == 3) {
            objArr[2] = "areInSamePackage";
        } else if (i10 == 4) {
            objArr[2] = "toDescriptorVisibility";
        } else if (i10 != 5 && i10 != 6) {
            objArr[2] = "isVisibleForProtectedAndPackage";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 5 && i10 != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static boolean b(q9.f fVar, g8.o oVar, g8.k kVar) {
        g8.o oVarT;
        if (kVar == null) {
            a(1);
            throw null;
        }
        if (oVar instanceof g8.c) {
            oVarT = i9.f.t((g8.c) oVar);
        } else {
            int i10 = i9.f.f16249a;
            oVarT = oVar;
        }
        if (c(oVarT, kVar)) {
            return true;
        }
        return g8.q.f15526c.a(fVar, oVar, kVar);
    }

    public static boolean c(g8.o oVar, g8.k kVar) {
        if (oVar == null) {
            a(2);
            throw null;
        }
        if (kVar == null) {
            a(3);
            throw null;
        }
        g8.h0 h0Var = (g8.h0) i9.f.i(oVar, g8.h0.class, false);
        g8.h0 h0Var2 = (g8.h0) i9.f.i(kVar, g8.h0.class, false);
        return (h0Var2 == null || h0Var == null || !h0Var.c().equals(h0Var2.c())) ? false : true;
    }
}
