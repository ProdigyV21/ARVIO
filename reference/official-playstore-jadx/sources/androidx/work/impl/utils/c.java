package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import java.util.HashSet;
import java.util.Iterator;
import m2.a0;
import m2.x;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements Runnable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f7000m = m2.t.f("EnqueueRunnable");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.work.impl.m f7001i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f7002l;

    public c(androidx.work.impl.m mVar, io.sentry.internal.debugmeta.c cVar) {
        this.f7001i = mVar;
        this.f7002l = cVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0150 A[PHI: r1 r11 r16 r17 r18
      0x0150: PHI (r1v3 java.lang.String[]) = 
      (r1v2 java.lang.String[])
      (r1v2 java.lang.String[])
      (r1v2 java.lang.String[])
      (r1v6 java.lang.String[])
      (r1v6 java.lang.String[])
     binds: [B:30:0x0085, B:31:0x0087, B:33:0x0095, B:79:0x014f, B:78:0x014d] A[DONT_GENERATE, DONT_INLINE]
      0x0150: PHI (r11v2 boolean) = (r11v1 boolean), (r11v1 boolean), (r11v1 boolean), (r11v6 boolean), (r11v7 boolean) binds: [B:30:0x0085, B:31:0x0087, B:33:0x0095, B:79:0x014f, B:78:0x014d] A[DONT_GENERATE, DONT_INLINE]
      0x0150: PHI (r16v2 boolean) = (r16v1 boolean), (r16v1 boolean), (r16v1 boolean), (r16v4 boolean), (r16v4 boolean) binds: [B:30:0x0085, B:31:0x0087, B:33:0x0095, B:79:0x014f, B:78:0x014d] A[DONT_GENERATE, DONT_INLINE]
      0x0150: PHI (r17v2 boolean) = (r17v1 boolean), (r17v1 boolean), (r17v1 boolean), (r17v5 boolean), (r17v5 boolean) binds: [B:30:0x0085, B:31:0x0087, B:33:0x0095, B:79:0x014f, B:78:0x014d] A[DONT_GENERATE, DONT_INLINE]
      0x0150: PHI (r18v2 boolean) = (r18v1 boolean), (r18v1 boolean), (r18v1 boolean), (r18v5 boolean), (r18v5 boolean) binds: [B:30:0x0085, B:31:0x0087, B:33:0x0095, B:79:0x014f, B:78:0x014d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015c  */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r11v8, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(androidx.work.impl.m r57) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.c.a(androidx.work.impl.m):boolean");
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        io.sentry.internal.debugmeta.c cVar = this.f7002l;
        androidx.work.impl.m mVar = this.f7001i;
        androidx.work.impl.r rVar = mVar.f6897c;
        try {
            HashSet hashSet = new HashSet();
            hashSet.addAll(mVar.f6901g);
            HashSet hashSetK = androidx.work.impl.m.K(mVar);
            Iterator it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    hashSet.removeAll(mVar.f6901g);
                    z = false;
                    break;
                } else if (hashSetK.contains((String) it.next())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                throw new IllegalStateException("WorkContinuation has cycles (" + mVar + ")");
            }
            WorkDatabase workDatabase = rVar.f6966c;
            workDatabase.c();
            try {
                androidx.work.impl.t.e(workDatabase, rVar.f6965b, mVar);
                boolean zA = a(mVar);
                workDatabase.o();
                if (zA) {
                    j.a(rVar.f6964a, RescheduleReceiver.class, true);
                    androidx.work.impl.k.b(rVar.f6965b, rVar.f6966c, rVar.f6968e);
                }
                cVar.A(a0.f20171d);
            } finally {
                workDatabase.k();
            }
        } catch (Throwable th) {
            cVar.A(new x(th));
        }
    }
}
