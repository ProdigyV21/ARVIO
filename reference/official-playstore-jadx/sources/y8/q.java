package y8;

import a9.k1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements s9.p, w, b0, d0, p8.t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q f22969b = new q();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final q f22970c = new q();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final q f22971d = new q();

    public static String[] a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add("<init>(" + str + ")V");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static v b(String str) {
        n9.c cVar;
        char cCharAt = str.charAt(0);
        n9.c[] cVarArrValues = n9.c.values();
        int length = cVarArrValues.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                cVar = null;
                break;
            }
            cVar = cVarArrValues[i10];
            if (cVar.f().charAt(0) == cCharAt) {
                break;
            }
            i10++;
        }
        if (cVar != null) {
            return new u(cVar);
        }
        if (cCharAt == 'V') {
            return new u(null);
        }
        if (cCharAt == '[') {
            return new s(b(str.substring(1)));
        }
        if (cCharAt == 'L') {
            kotlin.text.o.X(str, ';');
        }
        return new t(str.substring(1, str.length() - 1));
    }

    public static LinkedHashSet d(String str, String... strArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : strArr) {
            linkedHashSet.add(str + '.' + str2);
        }
        return linkedHashSet;
    }

    public static LinkedHashSet e(String str, String... strArr) {
        return d("java/lang/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static LinkedHashSet f(String str, String... strArr) {
        return d("java/util/".concat(str), (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static String g(v vVar) {
        String strF;
        if (vVar instanceof s) {
            return "[" + g(((s) vVar).f22975i);
        }
        if (vVar instanceof u) {
            n9.c cVar = ((u) vVar).f22977i;
            return (cVar == null || (strF = cVar.f()) == null) ? "V" : strF;
        }
        if (vVar instanceof t) {
            return androidx.compose.foundation.c.u(new StringBuilder("L"), ((t) vVar).f22976i, ';');
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // s9.p
    public v9.w c(k1 k1Var, String str, v9.b0 b0Var, v9.b0 b0Var2) {
        if (str.equals("kotlin.jvm.PlatformType")) {
            return k1Var.k(d9.q.f14869g) ? new u8.f(b0Var, b0Var2, 0) : v9.e.j(b0Var, b0Var2);
        }
        return x9.k.b(x9.j.f22702w, str, b0Var.toString(), b0Var2.toString());
    }
}
