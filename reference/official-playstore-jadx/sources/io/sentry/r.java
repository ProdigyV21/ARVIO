package io.sentry;

import io.sentry.android.core.SentryAndroidOptions;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public final class r implements l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f17766d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f17767e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f17768f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y6 f17769g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.util.b f17763a = new io.sentry.util.b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Timer f17764b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap f17765c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicBoolean f17770h = new AtomicBoolean(false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f17771i = 0;

    public r(SentryAndroidOptions sentryAndroidOptions) {
        boolean z = false;
        ac.b.V(sentryAndroidOptions, "The options object is required.");
        this.f17769g = sentryAndroidOptions;
        this.f17766d = new ArrayList();
        this.f17767e = new ArrayList();
        for (v0 v0Var : sentryAndroidOptions.getPerformanceCollectors()) {
            if (v0Var instanceof x0) {
                this.f17766d.add((x0) v0Var);
            }
            if (v0Var instanceof w0) {
                this.f17767e.add((w0) v0Var);
            }
        }
        if (this.f17766d.isEmpty() && this.f17767e.isEmpty()) {
            z = true;
        }
        this.f17768f = z;
    }

    @Override // io.sentry.l
    public final void a(k7 k7Var) {
        Iterator it = this.f17767e.iterator();
        while (it.hasNext()) {
            ((w0) it.next()).a(k7Var);
        }
    }

    @Override // io.sentry.l
    public final List b(String str) {
        ConcurrentHashMap concurrentHashMap = this.f17765c;
        q qVar = (q) concurrentHashMap.remove(str);
        this.f17769g.getLogger().q(w5.DEBUG, androidx.compose.material3.d.C("stop collecting performance info for ", str), new Object[0]);
        if (concurrentHashMap.isEmpty()) {
            close();
        }
        if (qVar != null) {
            return qVar.f17743a;
        }
        return null;
    }

    @Override // io.sentry.l
    public final void c(k7 k7Var) {
        Iterator it = this.f17767e.iterator();
        while (it.hasNext()) {
            ((w0) it.next()).b(k7Var);
        }
    }

    @Override // io.sentry.l
    public final void close() {
        this.f17769g.getLogger().q(w5.DEBUG, "stop collecting all performance info for transactions", new Object[0]);
        this.f17765c.clear();
        Iterator it = this.f17767e.iterator();
        while (it.hasNext()) {
            ((w0) it.next()).clear();
        }
        if (this.f17770h.getAndSet(false)) {
            io.sentry.util.a aVarA = this.f17763a.a();
            try {
                if (this.f17764b != null) {
                    this.f17764b.cancel();
                    this.f17764b = null;
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
        }
    }

    @Override // io.sentry.l
    public final void d(h7 h7Var) {
        if (this.f17768f) {
            this.f17769g.getLogger().q(w5.INFO, "No collector found. Performance stats will not be captured during transactions.", new Object[0]);
            return;
        }
        Iterator it = this.f17767e.iterator();
        while (it.hasNext()) {
            ((w0) it.next()).b(h7Var);
        }
        String string = h7Var.f17323a.toString();
        ConcurrentHashMap concurrentHashMap = this.f17765c;
        if (!concurrentHashMap.containsKey(string)) {
            concurrentHashMap.put(string, new q(this, h7Var));
        }
        f(string);
    }

    @Override // io.sentry.l
    public final List e(m1 m1Var) {
        this.f17769g.getLogger().q(w5.DEBUG, "stop collecting performance info for transactions %s (%s)", m1Var.getName(), m1Var.o().f17435i.toString());
        Iterator it = this.f17767e.iterator();
        while (it.hasNext()) {
            ((w0) it.next()).a(m1Var);
        }
        return b(m1Var.f().toString());
    }

    @Override // io.sentry.l
    public final void f(String str) {
        if (this.f17768f) {
            this.f17769g.getLogger().q(w5.INFO, "No collector found. Performance stats will not be captured during transactions.", new Object[0]);
            return;
        }
        if (!this.f17765c.containsKey(str)) {
            this.f17765c.put(str, new q(this, null));
        }
        if (this.f17770h.getAndSet(true)) {
            return;
        }
        io.sentry.util.a aVarA = this.f17763a.a();
        try {
            if (this.f17764b == null) {
                this.f17764b = new Timer(true);
            }
            this.f17764b.schedule(new o(this, 0), 0L);
            this.f17764b.scheduleAtFixedRate(new p(this, new ArrayList()), 100L, 100L);
            aVarA.close();
        } finally {
        }
    }
}
