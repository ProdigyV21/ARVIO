package io.sentry.android.core.internal.util;

import android.os.SystemClock;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f16606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f16607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f16608d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Serializable f16609e;

    public g(long j10, int i10) {
        this.f16608d = new AtomicInteger(0);
        this.f16609e = new AtomicLong(0L);
        this.f16607c = d.f16599i;
        this.f16606b = j10;
        this.f16605a = i10 <= 0 ? 1 : i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x002e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(gb.a r6, lb.i r7, java.util.List r8, boolean r9) {
        /*
            r5 = this;
            java.io.Serializable r0 = r5.f16609e
            java.util.concurrent.ConcurrentLinkedQueue r0 = (java.util.concurrent.ConcurrentLinkedQueue) r0
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L32
            java.lang.Object r1 = r0.next()
            lb.k r1 = (lb.k) r1
            monitor-enter(r1)
            r3 = 1
            if (r9 == 0) goto L23
            ob.o r4 = r1.f20130g     // Catch: java.lang.Throwable -> L21
            if (r4 == 0) goto L1e
            r2 = r3
        L1e:
            if (r2 == 0) goto L2e
            goto L23
        L21:
            r6 = move-exception
            goto L30
        L23:
            boolean r2 = r1.i(r6, r8)     // Catch: java.lang.Throwable -> L21
            if (r2 == 0) goto L2e
            r7.b(r1)     // Catch: java.lang.Throwable -> L21
            monitor-exit(r1)
            return r3
        L2e:
            monitor-exit(r1)
            goto L8
        L30:
            monitor-exit(r1)
            throw r6
        L32:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.util.g.a(gb.a, lb.i, java.util.List, boolean):boolean");
    }

    public boolean b() {
        AtomicInteger atomicInteger = (AtomicInteger) this.f16608d;
        ((d) this.f16607c).getClass();
        long jUptimeMillis = SystemClock.uptimeMillis();
        AtomicLong atomicLong = (AtomicLong) this.f16609e;
        if (atomicLong.get() == 0 || atomicLong.get() + this.f16606b <= jUptimeMillis) {
            atomicInteger.set(0);
            atomicLong.set(jUptimeMillis);
            return false;
        }
        if (atomicInteger.incrementAndGet() < this.f16605a) {
            return false;
        }
        atomicInteger.set(0);
        return true;
    }

    public int c(lb.k kVar, long j10) {
        byte[] bArr = ib.c.f16268a;
        ArrayList arrayList = kVar.f20138p;
        int i10 = 0;
        while (i10 < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i10);
            if (reference.get() != null) {
                i10++;
            } else {
                String str = "A connection to " + kVar.f20125b.f15816a.f15612h + " was leaked. Did you forget to close a response body?";
                qb.n nVar = qb.n.f21385a;
                qb.n.f21385a.k(((lb.g) reference).f20108a, str);
                arrayList.remove(i10);
                kVar.f20133j = true;
                if (arrayList.isEmpty()) {
                    kVar.f20139q = j10 - this.f16606b;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }

    public g(kb.e eVar, int i10, long j10, TimeUnit timeUnit) {
        this.f16605a = i10;
        this.f16606b = timeUnit.toNanos(j10);
        this.f16607c = eVar.e();
        this.f16608d = new jb.g(this, a0.c.p(new StringBuilder(), ib.c.f16275h, " ConnectionPool"), 2);
        this.f16609e = new ConcurrentLinkedQueue();
        if (j10 <= 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "keepAliveDuration <= 0: ").toString());
        }
    }
}
