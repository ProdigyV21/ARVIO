package x8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.collections.d0;
import kotlin.collections.i0;
import kotlin.collections.s;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f22667a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public x f22668b = new x("V", null);

    public n(vb.f fVar, String str) {
    }

    public final void a(String str, d... dVarArr) {
        p pVar;
        if (dVarArr.length == 0) {
            pVar = null;
        } else {
            ab.k kVar = new ab.k(new com.arflix.tv.d(dVarArr, 5), 7);
            int iQ0 = i0.q0(s.U(kVar, 10));
            if (iQ0 < 16) {
                iQ0 = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
            Iterator it = kVar.iterator();
            while (true) {
                ga.d dVar = (ga.d) it;
                if (!dVar.f15570m.hasNext()) {
                    break;
                }
                d0 d0Var = (d0) dVar.next();
                linkedHashMap.put(Integer.valueOf(d0Var.f19688a), (d) d0Var.f19689b);
            }
            pVar = new p(linkedHashMap);
        }
        this.f22667a.add(new x(str, pVar));
    }

    public final void b(String str, d... dVarArr) {
        ab.k kVar = new ab.k(new com.arflix.tv.d(dVarArr, 5), 7);
        int iQ0 = i0.q0(s.U(kVar, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        Iterator it = kVar.iterator();
        while (true) {
            ga.d dVar = (ga.d) it;
            if (!dVar.f15570m.hasNext()) {
                this.f22668b = new x(str, new p(linkedHashMap));
                return;
            } else {
                d0 d0Var = (d0) dVar.next();
                linkedHashMap.put(Integer.valueOf(d0Var.f19688a), (d) d0Var.f19689b);
            }
        }
    }
}
