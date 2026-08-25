package com.google.common.util.concurrent;

import androidx.media3.exoplayer.upstream.CmcdData;
import j$.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class p extends w4.a implements d1 {
    private static final a ATOMIC_HELPER;
    static final boolean GENERATE_CANCELLATION_CAUSES;
    private static final Object NULL;
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    static final c1 log;

    @q6.a
    private volatile d listeners;

    @q6.a
    private volatile Object value;

    @q6.a
    private volatile o waiters;

    static {
        boolean z;
        Throwable th;
        a gVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        GENERATE_CANCELLATION_CAUSES = z;
        log = new c1(p.class);
        Throwable th2 = null;
        try {
            gVar = new n();
            th = null;
        } catch (Error | Exception e5) {
            th = e5;
            try {
                gVar = new e(AtomicReferenceFieldUpdater.newUpdater(o.class, Thread.class, CmcdData.OBJECT_TYPE_AUDIO_ONLY), AtomicReferenceFieldUpdater.newUpdater(o.class, o.class, "b"), AtomicReferenceFieldUpdater.newUpdater(p.class, o.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(p.class, d.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(p.class, Object.class, "value"));
            } catch (Error | Exception e6) {
                th2 = e6;
                gVar = new g();
            }
        }
        ATOMIC_HELPER = gVar;
        if (th2 != null) {
            c1 c1Var = log;
            Logger loggerA = c1Var.a();
            Level level = Level.SEVERE;
            loggerA.log(level, "UnsafeAtomicHelper is broken!", th);
            c1Var.a().log(level, "SafeAtomicHelper is broken!", th2);
        }
        NULL = new Object();
    }

    public static void c(p pVar, boolean z) {
        d dVar = null;
        while (true) {
            pVar.getClass();
            for (o oVarE = ATOMIC_HELPER.e(pVar); oVarE != null; oVarE = oVarE.f14227b) {
                Thread thread = oVarE.f14226a;
                if (thread != null) {
                    oVarE.f14226a = null;
                    LockSupport.unpark(thread);
                }
            }
            if (z) {
                pVar.interruptTask();
                z = false;
            }
            pVar.afterDone();
            d dVar2 = dVar;
            d dVarD = ATOMIC_HELPER.d(pVar);
            d dVar3 = dVar2;
            while (dVarD != null) {
                d dVar4 = dVarD.f14199c;
                dVarD.f14199c = dVar3;
                dVar3 = dVarD;
                dVarD = dVar4;
            }
            while (dVar3 != null) {
                dVar = dVar3.f14199c;
                Runnable runnable = dVar3.f14197a;
                Objects.requireNonNull(runnable);
                if (runnable instanceof f) {
                    f fVar = (f) runnable;
                    pVar = fVar.f14205i;
                    if (pVar.value == fVar) {
                        if (ATOMIC_HELPER.b(pVar, fVar, f(fVar.f14206l))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = dVar3.f14198b;
                    Objects.requireNonNull(executor);
                    d(runnable, executor);
                }
                dVar3 = dVar;
            }
            return;
        }
    }

    public static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e5) {
            log.a().log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e5);
        }
    }

    public static Object e(Object obj) throws ExecutionException {
        if (obj instanceof b) {
            Throwable th = ((b) obj).f14188b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof c) {
            throw new ExecutionException(((c) obj).f14192a);
        }
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object f(d1 d1Var) {
        Object obj;
        Throwable thTryInternalFastPathGetFailure;
        if (d1Var instanceof h) {
            Object bVar = ((p) d1Var).value;
            if (bVar instanceof b) {
                b bVar2 = (b) bVar;
                if (bVar2.f14187a) {
                    bVar = bVar2.f14188b != null ? new b(bVar2.f14188b, false) : b.f14186d;
                }
            }
            Objects.requireNonNull(bVar);
            return bVar;
        }
        if ((d1Var instanceof w4.a) && (thTryInternalFastPathGetFailure = ((w4.a) d1Var).tryInternalFastPathGetFailure()) != null) {
            return new c(thTryInternalFastPathGetFailure);
        }
        boolean zIsCancelled = d1Var.isCancelled();
        boolean z = true;
        if ((!GENERATE_CANCELLATION_CAUSES) && zIsCancelled) {
            b bVar3 = b.f14186d;
            Objects.requireNonNull(bVar3);
            return bVar3;
        }
        boolean z5 = false;
        while (true) {
            try {
                try {
                    try {
                        obj = d1Var.get();
                        break;
                    } catch (Error e5) {
                        e = e5;
                        return new c(e);
                    }
                } catch (InterruptedException unused) {
                    z5 = z;
                } catch (Throwable th) {
                    if (z5) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (Error | Exception e6) {
                e = e6;
                return new c(e);
            } catch (CancellationException e10) {
                if (zIsCancelled) {
                    return new b(e10, false);
                }
                return new c(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + d1Var, e10));
            } catch (ExecutionException e11) {
                if (!zIsCancelled) {
                    return new c(e11.getCause());
                }
                return new b(new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + d1Var, e11), false);
            }
        }
        if (z5) {
            Thread.currentThread().interrupt();
        }
        if (!zIsCancelled) {
            return obj == null ? NULL : obj;
        }
        return new b(new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + d1Var), false);
    }

    public final void a(StringBuilder sb2) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                try {
                    obj = get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (CancellationException unused2) {
                sb2.append("CANCELLED");
                return;
            } catch (ExecutionException e5) {
                sb2.append("FAILURE, cause=[");
                sb2.append(e5.getCause());
                sb2.append("]");
                return;
            } catch (Exception e6) {
                sb2.append("UNKNOWN, cause=[");
                sb2.append(e6.getClass());
                sb2.append(" thrown from get()]");
                return;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        sb2.append("SUCCESS, result=[");
        b(sb2, obj);
        sb2.append("]");
    }

    @Override // com.google.common.util.concurrent.d1
    public void addListener(Runnable runnable, Executor executor) {
        d dVar;
        d dVar2 = d.f14196d;
        ac.b.p(runnable, "Runnable was null.");
        ac.b.p(executor, "Executor was null.");
        if (!isDone() && (dVar = this.listeners) != dVar2) {
            d dVar3 = new d(runnable, executor);
            do {
                dVar3.f14199c = dVar;
                if (ATOMIC_HELPER.a(this, dVar, dVar3)) {
                    return;
                } else {
                    dVar = this.listeners;
                }
            } while (dVar != dVar2);
        }
        d(runnable, executor);
    }

    public void afterDone() {
    }

    public final void b(StringBuilder sb2, Object obj) {
        if (obj == null) {
            sb2.append("null");
        } else {
            if (obj == this) {
                sb2.append("this future");
                return;
            }
            sb2.append(obj.getClass().getName());
            sb2.append("@");
            sb2.append(Integer.toHexString(System.identityHashCode(obj)));
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        b bVar;
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof f)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            bVar = new b(new CancellationException("Future.cancel() was called."), z);
        } else {
            bVar = z ? b.f14185c : b.f14186d;
            Objects.requireNonNull(bVar);
        }
        p pVar = this;
        boolean z5 = false;
        while (true) {
            if (ATOMIC_HELPER.b(pVar, obj, bVar)) {
                c(pVar, z);
                if (!(obj instanceof f)) {
                    break;
                }
                d1 d1Var = ((f) obj).f14206l;
                if (!(d1Var instanceof h)) {
                    d1Var.cancel(z);
                    break;
                }
                pVar = (p) d1Var;
                obj = pVar.value;
                if (!(obj == null) && !(obj instanceof f)) {
                    break;
                }
                z5 = true;
            } else {
                obj = pVar.value;
                if (!(obj instanceof f)) {
                    return z5;
                }
            }
        }
        return true;
    }

    public final void g(o oVar) {
        oVar.f14226a = null;
        while (true) {
            o oVar2 = this.waiters;
            if (oVar2 == o.f14225c) {
                return;
            }
            o oVar3 = null;
            while (oVar2 != null) {
                o oVar4 = oVar2.f14227b;
                if (oVar2.f14226a != null) {
                    oVar3 = oVar2;
                } else if (oVar3 != null) {
                    oVar3.f14227b = oVar4;
                    if (oVar3.f14226a == null) {
                        break;
                    }
                } else if (!ATOMIC_HELPER.c(this, oVar2, oVar4)) {
                    break;
                }
                oVar2 = oVar4;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x00ca -> B:37:0x0087). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    @com.google.common.util.concurrent.m1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object get(long r21, java.util.concurrent.TimeUnit r23) throws java.util.concurrent.ExecutionException, java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.p.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    public void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.value instanceof b;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return (!(r0 instanceof f)) & (this.value != null);
    }

    public final void maybePropagateCancellationTo(@q6.a Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @q6.a
    public String pendingToString() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public boolean set(@m1 Object obj) {
        if (obj == null) {
            obj = NULL;
        }
        if (!ATOMIC_HELPER.b(this, null, obj)) {
            return false;
        }
        c(this, false);
        return true;
    }

    public boolean setException(Throwable th) {
        th.getClass();
        if (!ATOMIC_HELPER.b(this, null, new c(th))) {
            return false;
        }
        c(this, false);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean setFuture(com.google.common.util.concurrent.d1<java.lang.Object> r6) {
        /*
            r5 = this;
            r6.getClass()
            java.lang.Object r0 = r5.value
            r1 = 0
            if (r0 != 0) goto L44
            boolean r0 = r6.isDone()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L20
            java.lang.Object r6 = f(r6)
            com.google.common.util.concurrent.a r0 = com.google.common.util.concurrent.p.ATOMIC_HELPER
            boolean r6 = r0.b(r5, r3, r6)
            if (r6 == 0) goto L4f
            c(r5, r1)
            return r2
        L20:
            com.google.common.util.concurrent.f r0 = new com.google.common.util.concurrent.f
            r0.<init>(r5, r6)
            com.google.common.util.concurrent.a r4 = com.google.common.util.concurrent.p.ATOMIC_HELPER
            boolean r3 = r4.b(r5, r3, r0)
            if (r3 == 0) goto L42
            com.google.common.util.concurrent.s0 r1 = com.google.common.util.concurrent.s0.f14233i     // Catch: java.lang.Throwable -> L33
            r6.addListener(r0, r1)     // Catch: java.lang.Throwable -> L33
            return r2
        L33:
            r6 = move-exception
            com.google.common.util.concurrent.c r1 = new com.google.common.util.concurrent.c     // Catch: java.lang.Throwable -> L3a
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L3a
            goto L3c
        L3a:
            com.google.common.util.concurrent.c r1 = com.google.common.util.concurrent.c.f14191b
        L3c:
            com.google.common.util.concurrent.a r6 = com.google.common.util.concurrent.p.ATOMIC_HELPER
            r6.b(r5, r0, r1)
            return r2
        L42:
            java.lang.Object r0 = r5.value
        L44:
            boolean r2 = r0 instanceof com.google.common.util.concurrent.b
            if (r2 == 0) goto L4f
            com.google.common.util.concurrent.b r0 = (com.google.common.util.concurrent.b) r0
            boolean r0 = r0.f14187a
            r6.cancel(r0)
        L4f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.p.setFuture(com.google.common.util.concurrent.d1):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.p.toString():java.lang.String");
    }

    @Override // w4.a
    @q6.a
    public final Throwable tryInternalFastPathGetFailure() {
        if (!(this instanceof h)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof c) {
            return ((c) obj).f14192a;
        }
        return null;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof b) && ((b) obj).f14187a;
    }

    @Override // java.util.concurrent.Future
    @m1
    public Object get() throws ExecutionException, InterruptedException {
        Object obj;
        o oVar = o.f14225c;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) & (!(obj2 instanceof f))) {
                return e(obj2);
            }
            o oVar2 = this.waiters;
            if (oVar2 != oVar) {
                o oVar3 = new o();
                do {
                    ATOMIC_HELPER.f(oVar3, oVar2);
                    if (ATOMIC_HELPER.c(this, oVar2, oVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                g(oVar3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof f))));
                        return e(obj);
                    }
                    oVar2 = this.waiters;
                } while (oVar2 != oVar);
            }
            Object obj3 = this.value;
            Objects.requireNonNull(obj3);
            return e(obj3);
        }
        throw new InterruptedException();
    }
}
