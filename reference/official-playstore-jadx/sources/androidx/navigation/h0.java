package androidx.navigation;

import androidx.appcompat.app.i1;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class h0 extends d0 implements Iterable, s7.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final /* synthetic */ int f4675w = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final t.m0 f4676s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f4677t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f4678u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f4679v;

    public h0(j0 j0Var) {
        super(j0Var);
        this.f4676s = new t.m0(0);
    }

    @Override // androidx.navigation.d0
    public final c0 c(i1 i1Var) {
        c0 c0VarC = super.c(i1Var);
        ArrayList arrayList = new ArrayList();
        g0 g0Var = new g0(this);
        while (g0Var.hasNext()) {
            c0 c0VarC2 = ((d0) g0Var.next()).c(i1Var);
            if (c0VarC2 != null) {
                arrayList.add(c0VarC2);
            }
        }
        return (c0) kotlin.collections.x.z0(kotlin.collections.r.X(new c0[]{c0VarC, (c0) kotlin.collections.x.z0(arrayList)}));
    }

    @Override // androidx.navigation.d0
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof h0) || !super.equals(obj)) {
            return false;
        }
        t.m0 m0Var = this.f4676s;
        int iF = m0Var.f();
        h0 h0Var = (h0) obj;
        t.m0 m0Var2 = h0Var.f4676s;
        if (iF != m0Var2.f() || this.f4677t != h0Var.f4677t) {
            return false;
        }
        for (d0 d0Var : (ga.a) ga.r.E(new ab.j(m0Var, 5))) {
            if (!d0Var.equals(m0Var2.c(d0Var.f4662p))) {
                return false;
            }
        }
        return true;
    }

    public final d0 f(int i10, boolean z) {
        h0 h0Var;
        d0 d0Var = (d0) this.f4676s.c(i10);
        if (d0Var != null) {
            return d0Var;
        }
        if (!z || (h0Var = this.f4658l) == null) {
            return null;
        }
        return h0Var.f(i10, true);
    }

    public final d0 g(String str, boolean z) {
        h0 h0Var;
        Object next;
        int iHashCode = "android-app://androidx.navigation/".concat(str).hashCode();
        t.m0 m0Var = this.f4676s;
        d0 d0Var = (d0) m0Var.c(iHashCode);
        if (d0Var == null) {
            Iterator it = ((ga.a) ga.r.E(new ab.j(m0Var, 5))).iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (((d0) next).d(str) != null) {
                    break;
                }
            }
            d0Var = (d0) next;
        }
        if (d0Var != null) {
            return d0Var;
        }
        if (!z || (h0Var = this.f4658l) == null || str == null || kotlin.text.o.h0(str)) {
            return null;
        }
        return h0Var.g(str, true);
    }

    public final c0 h(i1 i1Var) {
        return super.c(i1Var);
    }

    @Override // androidx.navigation.d0
    public final int hashCode() {
        int iD = this.f4677t;
        t.m0 m0Var = this.f4676s;
        int iF = m0Var.f();
        for (int i10 = 0; i10 < iF; i10++) {
            iD = (((iD * 31) + m0Var.d(i10)) * 31) + ((d0) m0Var.g(i10)).hashCode();
        }
        return iD;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new g0(this);
    }

    @Override // androidx.navigation.d0
    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        String str = this.f4679v;
        d0 d0VarG = (str == null || kotlin.text.o.h0(str)) ? null : g(str, true);
        if (d0VarG == null) {
            d0VarG = f(this.f4677t, true);
        }
        sb2.append(" startDestination=");
        if (d0VarG == null) {
            String str2 = this.f4679v;
            if (str2 != null) {
                sb2.append(str2);
            } else {
                String str3 = this.f4678u;
                if (str3 != null) {
                    sb2.append(str3);
                } else {
                    sb2.append("0x" + Integer.toHexString(this.f4677t));
                }
            }
        } else {
            sb2.append("{");
            sb2.append(d0VarG.toString());
            sb2.append("}");
        }
        return sb2.toString();
    }
}
