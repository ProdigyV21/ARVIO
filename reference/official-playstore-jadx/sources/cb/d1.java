package cb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class d1 implements ab.g, l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f7478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7479c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7480d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f7481e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List[] f7482f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean[] f7483g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f7484h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f7485i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f7486j;
    public final Object k;

    public d1(String str, e0 e0Var, int i10) {
        this.f7477a = str;
        this.f7478b = e0Var;
        this.f7479c = i10;
        String[] strArr = new String[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            strArr[i11] = "[UNINITIALIZED]";
        }
        this.f7481e = strArr;
        int i12 = this.f7479c;
        this.f7482f = new List[i12];
        this.f7483g = new boolean[i12];
        this.f7484h = kotlin.collections.a0.f19683i;
        this.f7485i = f4.f.o(2, new c1(this, 1));
        this.f7486j = f4.f.o(2, new c1(this, 2));
        this.k = f4.f.o(2, new c1(this, 0));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    @Override // cb.l
    public final Set a() {
        return this.f7484h.keySet();
    }

    @Override // ab.g
    public final boolean b() {
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Map] */
    @Override // ab.g
    public final int c(String str) {
        Integer num = (Integer) this.f7484h.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @Override // ab.g
    public final int d() {
        return this.f7479c;
    }

    @Override // ab.g
    public final String e(int i10) {
        return this.f7481e[i10];
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object, x6.s] */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d1) {
            ab.g gVar = (ab.g) obj;
            if (kotlin.jvm.internal.p.a(this.f7477a, gVar.h()) && Arrays.equals((ab.g[]) this.f7486j.getValue(), (ab.g[]) ((d1) obj).f7486j.getValue())) {
                int iD = gVar.d();
                int i10 = this.f7479c;
                if (i10 == iD) {
                    for (int i11 = 0; i11 < i10; i11++) {
                        if (kotlin.jvm.internal.p.a(g(i11).h(), gVar.g(i11).h()) && kotlin.jvm.internal.p.a(g(i11).getKind(), gVar.g(i11).getKind())) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // ab.g
    public final List f(int i10) {
        List list = this.f7482f[i10];
        return list == null ? kotlin.collections.z.f19728i : list;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // ab.g
    public ab.g g(int i10) {
        return ((ya.h[]) this.f7485i.getValue())[i10].getDescriptor();
    }

    @Override // ab.g
    public final List getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // ab.g
    public a.a getKind() {
        return ab.n.f818b;
    }

    @Override // ab.g
    public final String h() {
        return this.f7477a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    public int hashCode() {
        return ((Number) this.k.getValue()).intValue();
    }

    @Override // ab.g
    public final boolean i(int i10) {
        return this.f7483g[i10];
    }

    @Override // ab.g
    public boolean isInline() {
        return false;
    }

    public final void j(String str, boolean z) {
        int i10 = this.f7480d + 1;
        this.f7480d = i10;
        String[] strArr = this.f7481e;
        strArr[i10] = str;
        this.f7483g[i10] = z;
        this.f7482f[i10] = null;
        if (i10 == this.f7479c - 1) {
            HashMap map = new HashMap();
            int length = strArr.length;
            for (int i11 = 0; i11 < length; i11++) {
                map.put(strArr[i11], Integer.valueOf(i11));
            }
            this.f7484h = map;
        }
    }

    public String toString() {
        return kotlin.collections.x.u0(qb.d.X(0, this.f7479c), ", ", androidx.compose.foundation.c.u(new StringBuilder(), this.f7477a, '('), ")", new ab.h(this, 10), 24);
    }
}
