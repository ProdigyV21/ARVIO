package androidx.navigation;

import android.os.Bundle;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@z0("navigation")
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/navigation/j0;", "Landroidx/navigation/a1;", "Landroidx/navigation/h0;", "navigation-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class j0 extends a1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b1 f4705c;

    public j0(b1 b1Var) {
        this.f4705c = b1Var;
    }

    @Override // androidx.navigation.a1
    public final void d(List list, n0 n0Var) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            h0 h0Var = (h0) iVar.f4681l;
            Bundle bundleA = iVar.a();
            int i10 = h0Var.f4677t;
            String str = h0Var.f4679v;
            if (i10 == 0 && str == null) {
                StringBuilder sb2 = new StringBuilder("no start destination defined via app:startDestination for ");
                int i11 = h0Var.f4662p;
                sb2.append(i11 != 0 ? String.valueOf(i11) : "the root navigation");
                throw new IllegalStateException(sb2.toString().toString());
            }
            d0 d0VarG = str != null ? h0Var.g(str, false) : h0Var.f(i10, false);
            if (d0VarG == null) {
                if (h0Var.f4678u == null) {
                    String strValueOf = h0Var.f4679v;
                    if (strValueOf == null) {
                        strValueOf = String.valueOf(h0Var.f4677t);
                    }
                    h0Var.f4678u = strValueOf;
                }
                throw new IllegalArgumentException(a0.c.l("navigation destination ", h0Var.f4678u, " is not a direct child of this NavGraph"));
            }
            a1 a1VarB = this.f4705c.b(d0VarG.f4657i);
            k kVarB = b();
            Bundle bundleB = d0VarG.b(bundleA);
            s sVar = kVarB.f4713h;
            a1VarB.d(Collections.singletonList(a1.a.j(sVar.f4760a, d0VarG, bundleB, sVar.f(), sVar.f4774p)), n0Var);
        }
    }

    @Override // androidx.navigation.a1
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public h0 a() {
        return new h0(this);
    }
}
