package io.sentry;

import io.sentry.android.core.SentryAndroidOptions;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class n implements d0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17479i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f17480l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f17481m;

    public n(y6 y6Var) {
        this.f17479i = 1;
        this.f17480l = DesugarCollections.synchronizedMap(new WeakHashMap());
        this.f17481m = y6Var;
    }

    public void a(v4 v4Var) {
        io.sentry.protocol.c cVar = v4Var.f17985l;
        if (cVar.h() == null) {
            cVar.t(new io.sentry.protocol.x());
        }
        io.sentry.protocol.x xVarH = cVar.h();
        if (xVarH != null && xVarH.f17712i == null && xVarH.f17713l == null) {
            xVarH.f17712i = (String) this.f17481m;
            xVarH.f17713l = (String) this.f17480l;
        }
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, j0 j0Var) {
        int i10 = this.f17479i;
        return a7Var;
    }

    @Override // io.sentry.d0
    public final j5 j(j5 j5Var, j0 j0Var) {
        io.sentry.protocol.u uVarF;
        String str;
        Long l10;
        int i10 = this.f17479i;
        Object obj = this.f17481m;
        Object obj2 = this.f17480l;
        switch (i10) {
            case 0:
                Map map = (Map) obj2;
                if (y7.class.isInstance(j0Var.b("sentry:typeCheckHint")) && (uVarF = j5Var.f()) != null && (str = uVarF.f17700i) != null && (l10 = uVarF.f17703n) != null) {
                    Long l11 = (Long) map.get(str);
                    if (l11 == null || l11.equals(l10)) {
                        map.put(str, l10);
                    } else {
                        ((y6) obj).getLogger().q(w5.INFO, "Event %s has been dropped due to multi-threaded deduplication", j5Var.f17984i);
                        j0Var.d("sentry:eventDropReason", io.sentry.hints.h.MULTITHREADED_DEDUPLICATION);
                    }
                }
                break;
            case 1:
                Map map2 = (Map) obj2;
                y6 y6Var = (y6) obj;
                if (y6Var.isEnableDeduplication()) {
                    Throwable thA = j5Var.a();
                    if (thA != null) {
                        if (!map2.containsKey(thA)) {
                            ArrayList arrayList = new ArrayList();
                            for (Throwable cause = thA; cause.getCause() != null; cause = cause.getCause()) {
                                arrayList.add(cause.getCause());
                            }
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                if (map2.containsKey(it.next())) {
                                }
                            }
                            map2.put(thA, null);
                        }
                        y6Var.getLogger().q(w5.DEBUG, "Duplicate Exception detected. Event %s will be discarded.", j5Var.f17984i);
                    }
                } else {
                    y6Var.getLogger().q(w5.DEBUG, "Event deduplication is disabled.", new Object[0]);
                }
                break;
            default:
                a(j5Var);
                break;
        }
        return j5Var;
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, j0 j0Var) {
        switch (this.f17479i) {
            default:
                a(e0Var);
            case 0:
            case 1:
                return e0Var;
        }
    }

    public n() {
        this.f17479i = 2;
        String property = System.getProperty("java.version");
        String property2 = System.getProperty("java.vendor");
        this.f17480l = property;
        this.f17481m = property2;
    }

    public n(SentryAndroidOptions sentryAndroidOptions) {
        this.f17479i = 0;
        this.f17480l = DesugarCollections.synchronizedMap(new HashMap());
        this.f17481m = sentryAndroidOptions;
    }
}
