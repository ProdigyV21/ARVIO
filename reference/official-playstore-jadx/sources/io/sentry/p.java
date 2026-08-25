package io.sentry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends TimerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ArrayList f17506i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r f17507l;

    public p(r rVar, ArrayList arrayList) {
        this.f17507l = rVar;
        this.f17506i = arrayList;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        r rVar = this.f17507l;
        if (jCurrentTimeMillis - rVar.f17771i <= 10) {
            return;
        }
        ArrayList arrayList = this.f17506i;
        arrayList.clear();
        rVar.f17771i = jCurrentTimeMillis;
        n3 n3Var = new n3(rVar.f17769g.getDateProvider().a().i());
        Iterator it = rVar.f17766d.iterator();
        while (it.hasNext()) {
            ((x0) it.next()).d(n3Var);
        }
        for (q qVar : rVar.f17765c.values()) {
            ArrayList arrayList2 = qVar.f17743a;
            m1 m1Var = qVar.f17744b;
            arrayList2.add(n3Var);
            if (m1Var != null) {
                if (qVar.f17746d.f17769g.getDateProvider().a().i() > TimeUnit.MILLISECONDS.toNanos(30000L) + qVar.f17745c) {
                    arrayList.add(m1Var);
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            rVar.e((m1) it2.next());
        }
    }
}
