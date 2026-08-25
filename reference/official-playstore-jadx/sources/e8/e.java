package e8;

import ba.t;
import com.google.common.util.concurrent.p0;
import g8.e1;
import g8.v0;
import j8.o0;
import j8.v;
import j8.w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import v9.y0;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends o0 {
    public e(g8.k kVar, e eVar, int i10, boolean z) {
        super(kVar, eVar, h8.g.f15863a, t.f7315g, i10, v0.f15554b);
        this.f19385w = true;
        this.F = z;
        this.G = false;
    }

    @Override // j8.w, g8.w
    public final boolean A() {
        return false;
    }

    @Override // j8.o0, j8.w
    public final w I0(int i10, f9.f fVar, g8.k kVar, g8.w wVar, v0 v0Var, h8.h hVar) {
        return new e(kVar, (e) wVar, i10, this.F);
    }

    @Override // j8.w
    public final w J0(v vVar) {
        f9.f fVar;
        e eVar = (e) super.J0(vVar);
        if (eVar == null) {
            return null;
        }
        List listE = eVar.e();
        if (listE.isEmpty()) {
            return eVar;
        }
        Iterator it = listE.iterator();
        while (it.hasNext()) {
            if (p0.j(((e1) it.next()).getType()) != null) {
                List listE2 = eVar.e();
                ArrayList arrayList = new ArrayList(s.U(listE2, 10));
                Iterator it2 = listE2.iterator();
                while (it2.hasNext()) {
                    arrayList.add(p0.j(((e1) it2.next()).getType()));
                }
                int size = eVar.e().size() - arrayList.size();
                boolean z = true;
                if (size == 0) {
                    ArrayList<x> arrayListI1 = kotlin.collections.x.i1(arrayList, eVar.e());
                    if (arrayListI1.isEmpty()) {
                        return eVar;
                    }
                    for (x xVar : arrayListI1) {
                        if (!p.a((f9.f) xVar.f22608i, ((e1) xVar.f22609l).getName())) {
                        }
                    }
                    return eVar;
                }
                List<e1> listE3 = eVar.e();
                ArrayList arrayList2 = new ArrayList(s.U(listE3, 10));
                for (e1 e1Var : listE3) {
                    f9.f name = e1Var.getName();
                    int index = e1Var.getIndex();
                    int i10 = index - size;
                    if (i10 >= 0 && (fVar = (f9.f) arrayList.get(i10)) != null) {
                        name = fVar;
                    }
                    arrayList2.add(e1Var.e0(eVar, name, index));
                }
                v vVarM0 = eVar.M0(y0.f22262b);
                if (arrayList.isEmpty()) {
                    z = false;
                } else {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        if (((f9.f) it3.next()) == null) {
                            break;
                        }
                    }
                    z = false;
                }
                vVarM0.f19369v = Boolean.valueOf(z);
                vVarM0.f19355g = arrayList2;
                vVarM0.f19353e = eVar.a();
                return super.J0(vVarM0);
            }
        }
        return eVar;
    }

    @Override // j8.w, g8.a0
    public final boolean isExternal() {
        return false;
    }

    @Override // j8.w, g8.w
    public final boolean isInline() {
        return false;
    }
}
