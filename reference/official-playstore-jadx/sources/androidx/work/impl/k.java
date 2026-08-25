package androidx.work.impl;

import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6895a = m2.t.f("Schedulers");

    public static void a(androidx.work.impl.model.q qVar, m2.u uVar, List list) {
        if (list.size() > 0) {
            uVar.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                qVar.c(jCurrentTimeMillis, ((androidx.work.impl.model.p) it.next()).f6922a);
            }
        }
    }

    public static void b(m2.c cVar, WorkDatabase workDatabase, List list) {
        ArrayList arrayListV;
        if (list == null || list.size() == 0) {
            return;
        }
        androidx.work.impl.model.q qVarT = workDatabase.t();
        workDatabase.c();
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                arrayListV = qVarT.v();
                a(qVarT, cVar.f20175c, arrayListV);
            } else {
                arrayListV = null;
            }
            ArrayList arrayListR = qVarT.r(cVar.f20182j);
            a(qVarT, cVar.f20175c, arrayListR);
            if (arrayListV != null) {
                arrayListR.addAll(arrayListV);
            }
            ArrayList arrayListO = qVarT.o();
            workDatabase.o();
            workDatabase.k();
            if (arrayListR.size() > 0) {
                androidx.work.impl.model.p[] pVarArr = (androidx.work.impl.model.p[]) arrayListR.toArray(new androidx.work.impl.model.p[arrayListR.size()]);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    i iVar = (i) it.next();
                    if (iVar.d()) {
                        iVar.b(pVarArr);
                    }
                }
            }
            if (arrayListO.size() > 0) {
                androidx.work.impl.model.p[] pVarArr2 = (androidx.work.impl.model.p[]) arrayListO.toArray(new androidx.work.impl.model.p[arrayListO.size()]);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    i iVar2 = (i) it2.next();
                    if (!iVar2.d()) {
                        iVar2.b(pVarArr2);
                    }
                }
            }
        } catch (Throwable th) {
            workDatabase.k();
            throw th;
        }
    }
}
