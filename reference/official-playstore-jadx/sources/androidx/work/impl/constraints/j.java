package androidx.work.impl.constraints;

import a9.j1;
import a9.k1;
import a9.u1;
import androidx.work.impl.model.p;
import j8.c0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f6812a;

    public j(ArrayList arrayList) {
        this.f6812a = Collections.unmodifiableList(arrayList);
    }

    public boolean a(p pVar) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.f6812a) {
            androidx.work.impl.constraints.controllers.d dVar = (androidx.work.impl.constraints.controllers.d) obj;
            if (dVar.b(pVar) && dVar.c(dVar.f6798a.a())) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            t.d().a(m.f6821a, "Work " + pVar.f6922a + " constrained by " + x.u0(arrayList, null, null, null, g.f6805i, 31));
        }
        return arrayList.isEmpty();
    }

    public k1 b(int i10) {
        return (k1) this.f6812a.get(i10);
    }

    public j(u1 u1Var) {
        List list = u1Var.f696m;
        if ((u1Var.f695l & 1) == 1) {
            int i10 = u1Var.f697n;
            ArrayList arrayList = new ArrayList(s.U(list, 10));
            int i11 = 0;
            for (Object obj : list) {
                int i12 = i11 + 1;
                if (i11 < 0) {
                    t7.a.Q();
                    throw null;
                }
                k1 k1VarH = (k1) obj;
                if (i11 >= i10) {
                    k1VarH.getClass();
                    j1 j1VarP = k1.p(k1VarH);
                    j1VarP.f506n |= 2;
                    j1VarP.f508p = true;
                    k1VarH = j1VarP.h();
                    if (!k1VarH.isInitialized()) {
                        throw new UninitializedMessageException();
                    }
                }
                arrayList.add(k1VarH);
                i11 = i12;
            }
            list = arrayList;
        }
        this.f6812a = list;
    }

    public j(androidx.work.impl.constraints.trackers.m mVar) {
        androidx.work.impl.constraints.controllers.a aVar = new androidx.work.impl.constraints.controllers.a(mVar.f6840a, 0);
        androidx.work.impl.constraints.controllers.a aVar2 = new androidx.work.impl.constraints.controllers.a(mVar.f6841b, 1);
        androidx.work.impl.constraints.controllers.a aVar3 = new androidx.work.impl.constraints.controllers.a(mVar.f6843d, 4);
        androidx.work.impl.constraints.trackers.f fVar = mVar.f6842c;
        this.f6812a = t7.a.E(aVar, aVar2, aVar3, new androidx.work.impl.constraints.controllers.a(fVar, 2), new androidx.work.impl.constraints.controllers.a(fVar, 3), new androidx.work.impl.constraints.controllers.f(fVar), new androidx.work.impl.constraints.controllers.e(fVar));
    }

    public j(List list) {
        this.f6812a = list;
    }
}
