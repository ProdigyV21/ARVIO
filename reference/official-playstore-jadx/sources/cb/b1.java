package cb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ab.g[] f7463a = new ab.g[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ya.h[] f7464b = new ya.h[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f7465c = new Object();

    public static final h0 a(String str, ya.h hVar) {
        return new h0(str, new i0(hVar));
    }

    public static final Set b(ab.g gVar) {
        if (gVar instanceof l) {
            return ((l) gVar).a();
        }
        HashSet hashSet = new HashSet(gVar.d());
        int iD = gVar.d();
        for (int i10 = 0; i10 < iD; i10++) {
            hashSet.add(gVar.e(i10));
        }
        return hashSet;
    }

    public static final ab.g[] c(List list) {
        ab.g[] gVarArr;
        if (list == null || list.isEmpty()) {
            list = null;
        }
        return (list == null || (gVarArr = (ab.g[]) list.toArray(new ab.g[0])) == null) ? f7463a : gVarArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final ya.h d(kotlin.reflect.d r16, ya.h... r17) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cb.b1.d(kotlin.reflect.d, ya.h[]):ya.h");
    }

    public static final int e(ab.g gVar, ab.g[] gVarArr) {
        int iHashCode = (gVar.h().hashCode() * 31) + Arrays.hashCode(gVarArr);
        int iD = gVar.d();
        int i10 = 1;
        while (true) {
            int iHashCode2 = 0;
            if (!(iD > 0)) {
                break;
            }
            int i11 = iD - 1;
            int i12 = i10 * 31;
            String strH = gVar.g(gVar.d() - iD).h();
            if (strH != null) {
                iHashCode2 = strH.hashCode();
            }
            i10 = i12 + iHashCode2;
            iD = i11;
        }
        int iD2 = gVar.d();
        int iHashCode3 = 1;
        while (true) {
            if (!(iD2 > 0)) {
                return (((iHashCode * 31) + i10) * 31) + iHashCode3;
            }
            int i13 = iD2 - 1;
            int i14 = iHashCode3 * 31;
            a.a kind = gVar.g(gVar.d() - iD2).getKind();
            iHashCode3 = i14 + (kind != null ? kind.hashCode() : 0);
            iD2 = i13;
        }
    }

    public static final kotlin.reflect.d f(kotlin.reflect.q qVar) {
        kotlin.reflect.e eVarB = qVar.b();
        if (eVarB instanceof kotlin.reflect.d) {
            return (kotlin.reflect.d) eVarB;
        }
        if (!(eVarB instanceof kotlin.reflect.r)) {
            throw new IllegalStateException(("Only KClass supported as classifier, got " + eVarB).toString());
        }
        throw new IllegalStateException(("Captured type parameter " + eVarB + " from generic non-reified function. Such functionality cannot be supported as " + eVarB + " is erased, either specify serializer explicitly or make calling function inline with reified " + eVarB).toString());
    }

    public static final void g(kotlin.reflect.d dVar) {
        String strR = dVar.r();
        if (strR == null) {
            strR = "<local class name not available>";
        }
        throw new SerializationException(a0.c.l("Serializer for class '", strR, "' is not found.\nPlease ensure that class is marked as '@Serializable' and that the serialization compiler plugin is applied.\n"));
    }

    public static final void h(int i10, int i11, ab.g gVar) {
        ArrayList arrayList = new ArrayList();
        int i12 = (~i10) & i11;
        for (int i13 = 0; i13 < 32; i13++) {
            if ((i12 & 1) != 0) {
                arrayList.add(gVar.e(i13));
            }
            i12 >>>= 1;
        }
        String strH = gVar.h();
        throw new MissingFieldException(arrayList, arrayList.size() == 1 ? androidx.fragment.app.a2.n(new StringBuilder("Field '"), (String) arrayList.get(0), "' is required for type with serial name '", strH, "', but it was missing") : "Fields " + arrayList + " are required for type with serial name '" + strH + "', but they were missing", null);
    }

    public static final void i(String str, kotlin.reflect.d dVar) {
        String strP;
        String str2 = "in the scope of '" + dVar.r() + '\'';
        if (str == null) {
            strP = androidx.compose.material3.d.C("Class discriminator was missing and no default polymorphic serializers were registered ", str2);
        } else {
            StringBuilder sbR = androidx.fragment.app.a2.r("Class '", str, "' is not registered for polymorphic serialization ", str2, ".\nTo be registered automatically, class '");
            sbR.append(str);
            sbR.append("' has to be '@Serializable', and the base class '");
            sbR.append(dVar.r());
            sbR.append("' has to be sealed and '@Serializable'.\nAlternatively, register the serializer for '");
            strP = a0.c.p(sbR, str, "' explicitly in a corresponding SerializersModule.");
        }
        throw new SerializationException(strP);
    }
}
