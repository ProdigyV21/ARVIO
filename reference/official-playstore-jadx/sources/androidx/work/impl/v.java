package androidx.work.impl;

import android.content.Context;
import androidx.appcompat.app.i1;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public final class v implements Runnable {
    public static final String C = m2.t.f("WorkerWrapper");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f7087i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f7088l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i1 f7089m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.work.impl.model.p f7090n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public m2.s f7091o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.d f7092p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final m2.c f7094r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final m2.u f7095s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final g f7096t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final WorkDatabase f7097u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final androidx.work.impl.model.q f7098v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final androidx.work.impl.model.b f7099w;
    public final ArrayList x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f7100y;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public m2.r f7093q = new m2.o();
    public final androidx.work.impl.utils.futures.k z = new androidx.work.impl.utils.futures.k();
    public final androidx.work.impl.utils.futures.k A = new androidx.work.impl.utils.futures.k();
    public volatile int B = -256;

    public v(u uVar) {
        this.f7087i = (Context) uVar.f6986a;
        this.f7092p = (androidx.work.impl.utils.taskexecutor.d) uVar.f6988c;
        this.f7096t = (g) uVar.f6987b;
        androidx.work.impl.model.p pVar = (androidx.work.impl.model.p) uVar.f6991f;
        this.f7090n = pVar;
        this.f7088l = pVar.f6922a;
        this.f7089m = (i1) uVar.f6993h;
        this.f7091o = null;
        m2.c cVar = (m2.c) uVar.f6989d;
        this.f7094r = cVar;
        this.f7095s = cVar.f20175c;
        WorkDatabase workDatabase = (WorkDatabase) uVar.f6990e;
        this.f7097u = workDatabase;
        this.f7098v = workDatabase.t();
        this.f7099w = workDatabase.f();
        this.x = (ArrayList) uVar.f6992g;
    }

    public final void a(m2.r rVar) {
        boolean z = rVar instanceof m2.q;
        androidx.work.impl.model.p pVar = this.f7090n;
        String str = C;
        if (!z) {
            if (rVar instanceof m2.p) {
                m2.t.d().e(str, "Worker result RETRY for " + this.f7100y);
                c();
                return;
            }
            m2.t.d().e(str, "Worker result FAILURE for " + this.f7100y);
            if (pVar.c()) {
                d();
                return;
            } else {
                g();
                return;
            }
        }
        m2.t.d().e(str, "Worker result SUCCESS for " + this.f7100y);
        if (pVar.c()) {
            d();
            return;
        }
        androidx.work.impl.model.b bVar = this.f7099w;
        String str2 = this.f7088l;
        androidx.work.impl.model.q qVar = this.f7098v;
        WorkDatabase workDatabase = this.f7097u;
        workDatabase.c();
        try {
            qVar.h(3, str2);
            qVar.p(str2, ((m2.q) this.f7093q).f20213a);
            this.f7095s.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (String str3 : bVar.d(str2)) {
                if (qVar.j(str3) == 5 && bVar.h(str3)) {
                    m2.t.d().e(str, "Setting status to enqueued for " + str3);
                    qVar.h(1, str3);
                    qVar.s(jCurrentTimeMillis, str3);
                }
            }
            workDatabase.o();
            workDatabase.k();
            e(false);
        } catch (Throwable th) {
            workDatabase.k();
            e(false);
            throw th;
        }
    }

    public final void b() {
        if (h()) {
            return;
        }
        this.f7097u.c();
        try {
            int iJ = this.f7098v.j(this.f7088l);
            this.f7097u.s().a(this.f7088l);
            if (iJ == 0) {
                e(false);
            } else if (iJ == 2) {
                a(this.f7093q);
            } else if (!g8.b.b(iJ)) {
                this.B = -512;
                c();
            }
            this.f7097u.o();
            this.f7097u.k();
        } catch (Throwable th) {
            this.f7097u.k();
            throw th;
        }
    }

    public final void c() {
        String str = this.f7088l;
        androidx.work.impl.model.q qVar = this.f7098v;
        WorkDatabase workDatabase = this.f7097u;
        workDatabase.c();
        try {
            qVar.h(1, str);
            this.f7095s.getClass();
            qVar.s(System.currentTimeMillis(), str);
            qVar.f(this.f7090n.f6942v, str);
            qVar.c(-1L, str);
            workDatabase.o();
        } finally {
            workDatabase.k();
            e(true);
        }
    }

    public final void d() {
        String str = this.f7088l;
        androidx.work.impl.model.q qVar = this.f7098v;
        WorkDatabase workDatabase = this.f7097u;
        workDatabase.c();
        try {
            this.f7095s.getClass();
            qVar.s(System.currentTimeMillis(), str);
            qVar.h(1, str);
            qVar.w(str);
            qVar.f(this.f7090n.f6942v, str);
            qVar.b(str);
            qVar.c(-1L, str);
            workDatabase.o();
        } finally {
            workDatabase.k();
            e(false);
        }
    }

    public final void e(boolean z) {
        this.f7097u.c();
        try {
            if (!this.f7097u.t().u()) {
                androidx.work.impl.utils.j.a(this.f7087i, RescheduleReceiver.class, false);
            }
            if (z) {
                this.f7098v.h(1, this.f7088l);
                this.f7098v.setStopReason(this.f7088l, this.B);
                this.f7098v.c(-1L, this.f7088l);
            }
            this.f7097u.o();
            this.f7097u.k();
            this.z.h(Boolean.valueOf(z));
        } catch (Throwable th) {
            this.f7097u.k();
            throw th;
        }
    }

    public final void f() {
        androidx.work.impl.model.q qVar = this.f7098v;
        String str = this.f7088l;
        int iJ = qVar.j(str);
        String str2 = C;
        if (iJ == 2) {
            m2.t.d().a(str2, "Status for " + str + " is RUNNING; not doing any work and rescheduling for later execution");
            e(true);
            return;
        }
        m2.t tVarD = m2.t.d();
        StringBuilder sbT = a0.c.t("Status for ", str, " is ");
        sbT.append(g8.b.u(iJ));
        sbT.append(" ; not doing any work");
        tVarD.a(str2, sbT.toString());
        e(false);
    }

    public final void g() {
        String str = this.f7088l;
        WorkDatabase workDatabase = this.f7097u;
        workDatabase.c();
        try {
            LinkedList linkedList = new LinkedList();
            linkedList.add(str);
            while (true) {
                boolean zIsEmpty = linkedList.isEmpty();
                androidx.work.impl.model.q qVar = this.f7098v;
                if (zIsEmpty) {
                    m2.h hVar = ((m2.o) this.f7093q).f20212a;
                    qVar.f(this.f7090n.f6942v, str);
                    qVar.p(str, hVar);
                    workDatabase.o();
                    return;
                }
                String str2 = (String) linkedList.remove();
                if (qVar.j(str2) != 6) {
                    qVar.h(4, str2);
                }
                linkedList.addAll(this.f7099w.d(str2));
            }
        } finally {
            workDatabase.k();
            e(false);
        }
    }

    public final boolean h() {
        if (this.B == -256) {
            return false;
        }
        m2.t.d().a(C, "Work interrupted for " + this.f7100y);
        if (this.f7098v.j(this.f7088l) == 0) {
            e(false);
            return true;
        }
        e(!g8.b.b(r0));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0092 A[Catch: all -> 0x007e, TryCatch #2 {all -> 0x007e, blocks: (B:14:0x0050, B:17:0x005c, B:22:0x0081, B:24:0x0087, B:35:0x00c5, B:30:0x0092, B:32:0x00a3), top: B:80:0x0050 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.v.run():void");
    }
}
