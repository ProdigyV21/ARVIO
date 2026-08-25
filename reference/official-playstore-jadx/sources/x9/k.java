package x9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.collections.z;
import v9.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f22706a = new k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e f22707b = e.f22676i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f22708c = new a(f9.f.l(String.format("<Error class: %s>", Arrays.copyOf(new Object[]{"unknown class"}, 1))));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f22709d = b(j.f22697r, new String[0]);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f22710e = b(j.E, new String[0]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Set f22711f = Collections.singleton(new f());

    public static final g a(int i10, boolean z, String... strArr) {
        if (!z) {
            return new g((String[]) Arrays.copyOf(strArr, strArr.length), i10);
        }
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        return new l((String[]) Arrays.copyOf(strArr2, strArr2.length), i10);
    }

    public static final h b(j jVar, String... strArr) {
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        return d(jVar, z.f19728i, c(jVar, (String[]) Arrays.copyOf(strArr2, strArr2.length)), (String[]) Arrays.copyOf(strArr2, strArr2.length));
    }

    public static i c(j jVar, String... strArr) {
        return new i(jVar, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static h d(j jVar, List list, o0 o0Var, String... strArr) {
        return new h(o0Var, a(7, false, (String[]) Arrays.copyOf(new String[]{o0Var.toString()}, 1)), jVar, list, false, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final boolean e(g8.k kVar) {
        if (kVar != null) {
            return (kVar instanceof a) || (kVar.d() instanceof a) || kVar == f22707b;
        }
        return false;
    }
}
