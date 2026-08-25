package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;
import m2.a0;
import m2.x;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f6996i = new io.sentry.internal.debugmeta.c(4);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f6997l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ androidx.work.impl.r f6998m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6999n;

    public b(androidx.work.impl.r rVar, Object obj, int i10) {
        this.f6997l = i10;
        this.f6998m = rVar;
        this.f6999n = obj;
    }

    public static void a(androidx.work.impl.r rVar, String str) {
        androidx.work.impl.v vVarC;
        WorkDatabase workDatabase = rVar.f6966c;
        androidx.work.impl.model.q qVarT = workDatabase.t();
        androidx.work.impl.model.b bVarF = workDatabase.f();
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            int iJ = qVarT.j(str2);
            if (iJ != 3 && iJ != 4) {
                qVarT.l(str2);
            }
            linkedList.addAll(bVarF.d(str2));
        }
        androidx.work.impl.g gVar = rVar.f6969f;
        synchronized (gVar.k) {
            m2.t.d().a(androidx.work.impl.g.f6878l, "Processor cancelling " + str);
            gVar.f6887i.add(str);
            vVarC = gVar.c(str);
        }
        androidx.work.impl.g.f(str, vVarC, 1);
        Iterator it = rVar.f6968e.iterator();
        while (it.hasNext()) {
            ((androidx.work.impl.i) it.next()).a(str);
        }
    }

    public final void b() {
        WorkDatabase workDatabase;
        switch (this.f6997l) {
            case 0:
                androidx.work.impl.r rVar = this.f6998m;
                workDatabase = rVar.f6966c;
                workDatabase.c();
                try {
                    a(rVar, ((UUID) this.f6999n).toString());
                    workDatabase.o();
                    workDatabase.k();
                    androidx.work.impl.k.b(rVar.f6965b, rVar.f6966c, rVar.f6968e);
                    return;
                } finally {
                    workDatabase.k();
                }
            default:
                androidx.work.impl.r rVar2 = this.f6998m;
                workDatabase = rVar2.f6966c;
                workDatabase.c();
                try {
                    Iterator it = workDatabase.t().i((String) this.f6999n).iterator();
                    while (it.hasNext()) {
                        a(rVar2, (String) it.next());
                    }
                    workDatabase.o();
                    return;
                } catch (Throwable th) {
                    throw th;
                }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        io.sentry.internal.debugmeta.c cVar = this.f6996i;
        try {
            b();
            cVar.A(a0.f20171d);
        } catch (Throwable th) {
            cVar.A(new x(th));
        }
    }
}
