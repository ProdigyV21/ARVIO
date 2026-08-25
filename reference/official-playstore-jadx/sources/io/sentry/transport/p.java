package io.sentry.transport;

import io.sentry.y6;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Closeable;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements Closeable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y6 f17932l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ConcurrentHashMap f17933m = new ConcurrentHashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CopyOnWriteArrayList f17934n = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Timer f17935o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.util.b f17936p = new io.sentry.util.b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f17931i = d.f17915i;

    public p(y6 y6Var) {
        this.f17932l = y6Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = this.f17936p.a();
        try {
            Timer timer = this.f17935o;
            if (timer != null) {
                timer.cancel();
                this.f17935o = null;
            }
            aVarA.close();
            this.f17934n.clear();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void i(io.sentry.m mVar, Date date) {
        ConcurrentHashMap concurrentHashMap = this.f17933m;
        Date date2 = (Date) concurrentHashMap.get(mVar);
        if (date2 == null || date.after(date2)) {
            concurrentHashMap.put(mVar, date);
            Iterator it = this.f17934n.iterator();
            while (it.hasNext()) {
                ((o) it.next()).k(this);
            }
            io.sentry.util.a aVarA = this.f17936p.a();
            try {
                if (this.f17935o == null) {
                    this.f17935o = new Timer(true);
                }
                this.f17935o.schedule(new io.sentry.o(this, 2), date);
                aVarA.close();
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

    public final boolean j(io.sentry.m mVar) {
        Date date;
        this.f17931i.getClass();
        Date date2 = new Date(System.currentTimeMillis());
        io.sentry.m mVar2 = io.sentry.m.All;
        ConcurrentHashMap concurrentHashMap = this.f17933m;
        Date date3 = (Date) concurrentHashMap.get(mVar2);
        if (date3 != null && !date2.after(date3)) {
            return true;
        }
        if (io.sentry.m.Unknown.equals(mVar) || (date = (Date) concurrentHashMap.get(mVar)) == null) {
            return false;
        }
        return !date2.after(date);
    }
}
