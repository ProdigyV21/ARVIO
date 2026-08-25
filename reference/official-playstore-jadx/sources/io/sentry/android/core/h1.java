package io.sentry.android.core;

import androidx.appcompat.widget.u4;
import com.google.android.gms.cast.MediaTrack;
import io.sentry.a7;
import io.sentry.j5;
import io.sentry.l7;
import io.sentry.m2;
import io.sentry.o7;
import io.sentry.q7;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class h1 implements io.sentry.d0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u4 f16501i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final SentryAndroidOptions f16502l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.b f16503m = new io.sentry.util.b();

    public h1(SentryAndroidOptions sentryAndroidOptions, u4 u4Var) {
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16502l = sentryAndroidOptions;
        this.f16501i = u4Var;
    }

    public static void a(io.sentry.android.core.performance.g gVar, io.sentry.protocol.e0 e0Var) {
        o7 o7Var;
        if (gVar.f16706i != io.sentry.android.core.performance.f.COLD) {
            return;
        }
        io.sentry.protocol.c cVar = e0Var.f17985l;
        ArrayList arrayList = e0Var.C;
        l7 l7VarI = cVar.i();
        if (l7VarI == null) {
            return;
        }
        io.sentry.protocol.v vVar = l7VarI.f17435i;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                o7Var = null;
                break;
            }
            io.sentry.protocol.y yVar = (io.sentry.protocol.y) it.next();
            if (yVar.f17721p.contentEquals("app.start.cold")) {
                o7Var = yVar.f17719n;
                break;
            }
        }
        io.sentry.android.core.performance.h hVar = new io.sentry.android.core.performance.h();
        io.sentry.android.core.performance.h hVar2 = gVar.f16709n;
        long j10 = hVar2.f16721l;
        long j11 = hVar2.f16722m;
        long j12 = io.sentry.android.core.performance.g.z;
        hVar.f16720i = "Process Initialization";
        hVar.f16721l = j10;
        hVar.f16722m = j11;
        hVar.f16723n = j12;
        if (hVar.d() && Math.abs(hVar.a()) <= 10000) {
            arrayList.add(d(hVar, o7Var, vVar, "process.load"));
        }
        ArrayList arrayList2 = new ArrayList(gVar.f16712q.values());
        Collections.sort(arrayList2);
        if (!arrayList2.isEmpty()) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList.add(d((io.sentry.android.core.performance.h) it2.next(), o7Var, vVar, "contentprovider.load"));
            }
        }
        io.sentry.android.core.performance.h hVar3 = gVar.f16711p;
        if (hVar3.f16723n != 0) {
            arrayList.add(d(hVar3, o7Var, vVar, "application.load"));
        }
    }

    public static boolean b(io.sentry.protocol.e0 e0Var) {
        for (io.sentry.protocol.y yVar : e0Var.C) {
            if (yVar.f17721p.contentEquals("app.start.cold") || yVar.f17721p.contentEquals("app.start.warm")) {
                return true;
            }
        }
        l7 l7VarI = e0Var.f17985l.i();
        if (l7VarI == null) {
            return false;
        }
        String str = l7VarI.f17439o;
        return str.equals("app.start.cold") || str.equals("app.start.warm");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(io.sentry.protocol.e0 r11) {
        /*
            java.util.ArrayList r11 = r11.C
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r2 = r1
        L8:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L2f
            java.lang.Object r3 = r0.next()
            io.sentry.protocol.y r3 = (io.sentry.protocol.y) r3
            java.lang.String r4 = "ui.load.initial_display"
            java.lang.String r5 = r3.f17721p
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L20
            r1 = r3
            goto L2b
        L20:
            java.lang.String r4 = "ui.load.full_display"
            java.lang.String r5 = r3.f17721p
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L2b
            r2 = r3
        L2b:
            if (r1 == 0) goto L8
            if (r2 == 0) goto L8
        L2f:
            if (r1 != 0) goto L35
            if (r2 != 0) goto L35
            goto Lc7
        L35:
            java.util.Iterator r11 = r11.iterator()
        L39:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto Lc7
            java.lang.Object r0 = r11.next()
            io.sentry.protocol.y r0 = (io.sentry.protocol.y) r0
            if (r0 == r1) goto L39
            if (r0 != r2) goto L4a
            goto L39
        L4a:
            java.util.Map r3 = r0.f17726u
            java.lang.Double r4 = r0.f17716i
            r5 = 0
            r6 = 1
            if (r3 == 0) goto L65
            java.lang.String r7 = "thread.name"
            java.lang.Object r3 = r3.get(r7)
            if (r3 == 0) goto L65
            java.lang.String r7 = "main"
            boolean r3 = r7.equals(r3)
            if (r3 == 0) goto L63
            goto L65
        L63:
            r3 = r5
            goto L66
        L65:
            r3 = r6
        L66:
            if (r1 == 0) goto L86
            double r7 = r4.doubleValue()
            java.lang.Double r9 = r1.f17716i
            double r9 = r9.doubleValue()
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 < 0) goto L86
            java.lang.Double r9 = r1.f17717l
            if (r9 == 0) goto L82
            double r9 = r9.doubleValue()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 > 0) goto L86
        L82:
            if (r3 == 0) goto L86
            r3 = r6
            goto L87
        L86:
            r3 = r5
        L87:
            if (r2 == 0) goto La4
            double r7 = r4.doubleValue()
            java.lang.Double r4 = r2.f17716i
            double r9 = r4.doubleValue()
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 < 0) goto La4
            java.lang.Double r4 = r2.f17717l
            if (r4 == 0) goto La3
            double r9 = r4.doubleValue()
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 > 0) goto La4
        La3:
            r5 = r6
        La4:
            if (r3 != 0) goto La8
            if (r5 == 0) goto L39
        La8:
            java.util.Map r4 = r0.f17726u
            if (r4 != 0) goto Lb3
            j$.util.concurrent.ConcurrentHashMap r4 = new j$.util.concurrent.ConcurrentHashMap
            r4.<init>()
            r0.f17726u = r4
        Lb3:
            if (r3 == 0) goto Lbc
            java.lang.String r0 = "ui.contributes_to_ttid"
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r4.put(r0, r3)
        Lbc:
            if (r5 == 0) goto L39
            java.lang.String r0 = "ui.contributes_to_ttfd"
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            r4.put(r0, r3)
            goto L39
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.h1.c(io.sentry.protocol.e0):void");
    }

    public static io.sentry.protocol.y d(io.sentry.android.core.performance.h hVar, o7 o7Var, io.sentry.protocol.v vVar, String str) {
        long jA;
        HashMap map = new HashMap(2);
        map.put("thread.id", Long.valueOf(io.sentry.android.core.internal.util.e.f16601b));
        map.put("thread.name", MediaTrack.ROLE_MAIN);
        Boolean bool = Boolean.TRUE;
        map.put("ui.contributes_to_ttid", bool);
        map.put("ui.contributes_to_ttfd", bool);
        Double dValueOf = Double.valueOf(hVar.f16721l / 1000.0d);
        if (hVar.d()) {
            jA = hVar.a() + hVar.f16721l;
        } else {
            jA = 0;
        }
        return new io.sentry.protocol.y(dValueOf, Double.valueOf(jA / 1000.0d), vVar, new o7(), o7Var, str, hVar.f16720i, q7.OK, "auto.ui", new ConcurrentHashMap(), new ConcurrentHashMap(), map);
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, io.sentry.j0 j0Var) {
        return a7Var;
    }

    @Override // io.sentry.d0
    public final j5 j(j5 j5Var, io.sentry.j0 j0Var) {
        return j5Var;
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, io.sentry.j0 j0Var) {
        Map map;
        SentryAndroidOptions sentryAndroidOptions = this.f16502l;
        io.sentry.util.a aVarA = this.f16503m.a();
        try {
            if (!sentryAndroidOptions.isTracingEnabled()) {
                aVarA.close();
                return e0Var;
            }
            io.sentry.android.core.performance.g gVarC = io.sentry.android.core.performance.g.c();
            boolean zB = b(e0Var);
            HashMap map2 = e0Var.D;
            io.sentry.protocol.c cVar = e0Var.f17985l;
            if (zB) {
                if (gVarC.f16718w && ((Boolean) gVarC.f16707l.a()).booleanValue()) {
                    long jA = gVarC.b(sentryAndroidOptions).a();
                    if (jA != 0) {
                        map2.put(gVarC.f16706i == io.sentry.android.core.performance.f.COLD ? "app_start_cold" : "app_start_warm", new io.sentry.protocol.l(m2.a.MILLISECOND.apiName(), Float.valueOf(jA)));
                        a(gVarC, e0Var);
                        gVarC.f16718w = false;
                        gVarC.f16712q.clear();
                        gVarC.f16713r.clear();
                    }
                }
                io.sentry.protocol.a aVarD = cVar.d();
                if (aVarD == null) {
                    aVarD = new io.sentry.protocol.a();
                    cVar.m(aVarD);
                }
                aVarD.f17538t = gVarC.f16706i == io.sentry.android.core.performance.f.COLD ? "cold" : "warm";
            }
            c(e0Var);
            io.sentry.protocol.v vVar = e0Var.f17984i;
            l7 l7VarI = cVar.i();
            if (vVar != null && l7VarI != null && l7VarI.f17439o.contentEquals("ui.load")) {
                u4 u4Var = this.f16501i;
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) u4Var.f1775c;
                io.sentry.util.a aVarA2 = ((io.sentry.util.b) u4Var.f1778f).a();
                try {
                    if (u4Var.c()) {
                        Map map3 = (Map) concurrentHashMap.get(vVar);
                        concurrentHashMap.remove(vVar);
                        aVarA2.close();
                        map = map3;
                    } else {
                        aVarA2.close();
                        map = null;
                    }
                    if (map != null) {
                        map2.putAll(map);
                    }
                } finally {
                }
            }
            aVarA.close();
            return e0Var;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
