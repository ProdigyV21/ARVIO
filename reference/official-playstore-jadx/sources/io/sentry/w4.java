package io.sentry;

import android.app.Activity;
import io.sentry.android.core.ActivityLifecycleIntegration;
import io.sentry.android.core.SentryAndroidOptions;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w4 implements a4, n7 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f18037i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f18038l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f18039m;

    public /* synthetic */ w4(Object obj, Object obj2, Object obj3) {
        this.f18037i = obj;
        this.f18038l = obj2;
        this.f18039m = obj3;
    }

    @Override // io.sentry.a4
    public void a(j7 j7Var) {
        ConcurrentHashMap concurrentHashMap;
        com.google.android.gms.common.api.internal.g0 g0Var = (com.google.android.gms.common.api.internal.g0) this.f18037i;
        j5 j5Var = (j5) this.f18038l;
        j0 j0Var = (j0) this.f18039m;
        if (j7Var == null) {
            ((y6) g0Var.f12734b).getLogger().q(w5.INFO, "Session is null on scope.withSession", new Object[0]);
            return;
        }
        String strH = null;
        i7 i7Var = j5Var.f() != null ? i7.Crashed : null;
        boolean z = i7.Crashed == i7Var || j5Var.g();
        io.sentry.protocol.p pVar = j5Var.f17987n;
        String str = (pVar == null || (concurrentHashMap = pVar.f17665p) == null || !concurrentHashMap.containsKey("user-agent")) ? null : (String) j5Var.f17987n.f17665p.get("user-agent");
        Object objB = j0Var.b("sentry:typeCheckHint");
        if (objB instanceof io.sentry.hints.a) {
            strH = ((io.sentry.hints.a) objB).h();
            i7Var = i7.Abnormal;
        }
        if (!j7Var.d(i7Var, str, z, strH) || j7Var.f17408q == i7.Ok) {
            return;
        }
        j7Var.b(k2.c.m());
    }

    @Override // io.sentry.n7
    public void b(k7 k7Var) {
        io.sentry.android.core.d dVarB;
        h7 h7Var = (h7) this.f18037i;
        n7 n7Var = (n7) this.f18038l;
        AtomicReference atomicReference = (AtomicReference) this.f18039m;
        if (n7Var != null) {
            n7Var.b(k7Var);
        }
        io.sentry.android.core.g gVar = h7Var.f17339r.f18044i;
        if (gVar != null) {
            ActivityLifecycleIntegration activityLifecycleIntegration = (ActivityLifecycleIntegration) gVar.f16485i;
            WeakReference weakReference = (WeakReference) gVar.f16486l;
            String str = (String) gVar.f16487m;
            Activity activity = (Activity) weakReference.get();
            if (activity != null) {
                androidx.appcompat.widget.u4 u4Var = activityLifecycleIntegration.A;
                io.sentry.protocol.v vVarF = h7Var.f();
                io.sentry.util.a aVarA = ((io.sentry.util.b) u4Var.f1778f).a();
                try {
                    if (u4Var.c()) {
                        io.sentry.android.core.d dVar = null;
                        u4Var.d(null, new io.sentry.android.core.c(u4Var, activity, 1));
                        io.sentry.android.core.d dVar2 = (io.sentry.android.core.d) ((WeakHashMap) u4Var.f1776d).remove(activity);
                        if (dVar2 != null && (dVarB = u4Var.b()) != null) {
                            dVar = new io.sentry.android.core.d(dVarB.f16467a - dVar2.f16467a, dVarB.f16468b - dVar2.f16468b, dVarB.f16469c - dVar2.f16469c);
                        }
                        if (dVar != null) {
                            int i10 = dVar.f16469c;
                            int i11 = dVar.f16468b;
                            int i12 = dVar.f16467a;
                            if (i12 != 0 || i11 != 0 || i10 != 0) {
                                io.sentry.protocol.l lVar = new io.sentry.protocol.l("none", Integer.valueOf(i12));
                                io.sentry.protocol.l lVar2 = new io.sentry.protocol.l("none", Integer.valueOf(i11));
                                io.sentry.protocol.l lVar3 = new io.sentry.protocol.l("none", Integer.valueOf(i10));
                                HashMap map = new HashMap();
                                map.put("frames_total", lVar);
                                map.put("frames_slow", lVar2);
                                map.put("frames_frozen", lVar3);
                                ((ConcurrentHashMap) u4Var.f1775c).put(vVarF, map);
                            }
                        }
                    }
                    aVarA.close();
                } catch (Throwable th) {
                    try {
                        aVarA.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } else {
                SentryAndroidOptions sentryAndroidOptions = activityLifecycleIntegration.f16307n;
                if (sentryAndroidOptions != null) {
                    sentryAndroidOptions.getLogger().q(w5.WARNING, "Unable to track activity frames as the Activity %s has been destroyed.", str);
                }
            }
        }
        l lVar4 = h7Var.f17338q;
        if (lVar4 != null) {
            atomicReference.set(lVar4.e(h7Var));
        }
    }
}
