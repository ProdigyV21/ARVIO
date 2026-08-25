package ab;

import a8.g0;
import cb.b1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.d0;
import kotlin.collections.h0;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements g, cb.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.a f801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f803d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashSet f804e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String[] f805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g[] f806g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List[] f807h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean[] f808i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Map f809j;
    public final g[] k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i0 f810l;

    public i(String str, a.a aVar, int i10, List list, a aVar2) {
        this.f800a = str;
        this.f801b = aVar;
        this.f802c = i10;
        this.f803d = aVar2.f778b;
        ArrayList arrayList = aVar2.f779c;
        this.f804e = x.a1(arrayList);
        int i11 = 0;
        this.f805f = (String[]) arrayList.toArray(new String[0]);
        this.f806g = b1.c(aVar2.f781e);
        this.f807h = (List[]) aVar2.f782f.toArray(new List[0]);
        ArrayList arrayList2 = aVar2.f783g;
        boolean[] zArr = new boolean[arrayList2.size()];
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            zArr[i11] = ((Boolean) it.next()).booleanValue();
            i11++;
        }
        this.f808i = zArr;
        k kVar = new k(new com.arflix.tv.d(this.f805f, 5), 7);
        ArrayList arrayList3 = new ArrayList(s.U(kVar, 10));
        Iterator it2 = kVar.iterator();
        while (true) {
            ga.d dVar = (ga.d) it2;
            if (!dVar.f15570m.hasNext()) {
                this.f809j = h0.A0(arrayList3);
                this.k = b1.c(list);
                this.f810l = new i0(new g0(this, 5));
                return;
            }
            d0 d0Var = (d0) dVar.next();
            arrayList3.add(new x6.x(d0Var.f19689b, Integer.valueOf(d0Var.f19688a)));
        }
    }

    @Override // cb.l
    public final Set a() {
        return this.f804e;
    }

    @Override // ab.g
    public final boolean b() {
        return false;
    }

    @Override // ab.g
    public final int c(String str) {
        Integer num = (Integer) this.f809j.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @Override // ab.g
    public final int d() {
        return this.f802c;
    }

    @Override // ab.g
    public final String e(int i10) {
        return this.f805f[i10];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            g gVar = (g) obj;
            if (p.a(this.f800a, gVar.h()) && Arrays.equals(this.k, ((i) obj).k)) {
                int iD = gVar.d();
                int i10 = this.f802c;
                if (i10 == iD) {
                    for (int i11 = 0; i11 < i10; i11++) {
                        g[] gVarArr = this.f806g;
                        if (p.a(gVarArr[i11].h(), gVar.g(i11).h()) && p.a(gVarArr[i11].getKind(), gVar.g(i11).getKind())) {
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
        return this.f807h[i10];
    }

    @Override // ab.g
    public final g g(int i10) {
        return this.f806g[i10];
    }

    @Override // ab.g
    public final List getAnnotations() {
        return this.f803d;
    }

    @Override // ab.g
    public final a.a getKind() {
        return this.f801b;
    }

    @Override // ab.g
    public final String h() {
        return this.f800a;
    }

    public final int hashCode() {
        return ((Number) this.f810l.getValue()).intValue();
    }

    @Override // ab.g
    public final boolean i(int i10) {
        return this.f808i[i10];
    }

    @Override // ab.g
    public final boolean isInline() {
        return false;
    }

    public final String toString() {
        return x.u0(qb.d.X(0, this.f802c), ", ", androidx.compose.foundation.c.u(new StringBuilder(), this.f800a, '('), ")", new h(this, 0), 24);
    }
}
