package androidx.work.impl.utils.futures;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.p0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i implements d1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final boolean f7033n = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Logger f7034o = Logger.getLogger(i.class.getName());

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final p0 f7035p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Object f7036q;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile Object f7037i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile d f7038l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile h f7039m;

    static {
        p0 gVar;
        try {
            gVar = new e(AtomicReferenceFieldUpdater.newUpdater(h.class, Thread.class, CmcdData.OBJECT_TYPE_AUDIO_ONLY), AtomicReferenceFieldUpdater.newUpdater(h.class, h.class, "b"), AtomicReferenceFieldUpdater.newUpdater(i.class, h.class, CmcdData.OBJECT_TYPE_MANIFEST), AtomicReferenceFieldUpdater.newUpdater(i.class, d.class, CmcdData.STREAM_TYPE_LIVE), AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, CmcdData.OBJECT_TYPE_INIT_SEGMENT));
            th = null;
        } catch (Throwable th) {
            th = th;
            gVar = new g();
        }
        f7035p = gVar;
        if (th != null) {
            f7034o.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f7036q = new Object();
    }

    public static void b(i iVar) {
        d dVar;
        d dVar2;
        d dVar3 = null;
        while (true) {
            h hVar = iVar.f7039m;
            if (f7035p.f(iVar, hVar, h.f7030c)) {
                while (hVar != null) {
                    Thread thread = hVar.f7031a;
                    if (thread != null) {
                        hVar.f7031a = null;
                        LockSupport.unpark(thread);
                    }
                    hVar = hVar.f7032b;
                }
                do {
                    dVar = iVar.f7038l;
                } while (!f7035p.d(iVar, dVar, d.f7019d));
                while (true) {
                    dVar2 = dVar3;
                    dVar3 = dVar;
                    if (dVar3 == null) {
                        break;
                    }
                    dVar = dVar3.f7022c;
                    dVar3.f7022c = dVar2;
                }
                while (dVar2 != null) {
                    dVar3 = dVar2.f7022c;
                    Runnable runnable = dVar2.f7020a;
                    if (runnable instanceof f) {
                        f fVar = (f) runnable;
                        iVar = fVar.f7028i;
                        if (iVar.f7037i == fVar) {
                            if (f7035p.e(iVar, fVar, e(fVar.f7029l))) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        c(runnable, dVar2.f7021b);
                    }
                    dVar2 = dVar3;
                }
                return;
            }
        }
    }

    public static void c(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e5) {
            f7034o.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e5);
        }
    }

    public static Object d(Object obj) throws ExecutionException {
        if (obj instanceof a) {
            Throwable th = ((a) obj).f7015b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof c) {
            throw new ExecutionException(((c) obj).f7018a);
        }
        if (obj == f7036q) {
            return null;
        }
        return obj;
    }

    public static Object e(d1 d1Var) {
        V v2;
        if (d1Var instanceof i) {
            Object obj = ((i) d1Var).f7037i;
            if (!(obj instanceof a)) {
                return obj;
            }
            a aVar = (a) obj;
            return aVar.f7014a ? aVar.f7015b != null ? new a(aVar.f7015b, false) : a.f7013d : obj;
        }
        boolean zIsCancelled = d1Var.isCancelled();
        boolean z = true;
        if ((!f7033n) && zIsCancelled) {
            return a.f7013d;
        }
        boolean z5 = false;
        while (true) {
            try {
                try {
                    v2 = d1Var.get();
                    break;
                } catch (InterruptedException unused) {
                    z5 = z;
                } catch (Throwable th) {
                    if (z5) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (CancellationException e5) {
                if (zIsCancelled) {
                    return new a(e5, false);
                }
                return new c(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + d1Var, e5));
            } catch (ExecutionException e6) {
                return new c(e6.getCause());
            } catch (Throwable th2) {
                return new c(th2);
            }
        }
        if (z5) {
            Thread.currentThread().interrupt();
        }
        return v2 == 0 ? f7036q : v2;
    }

    public final void a(StringBuilder sb2) {
        V v2;
        boolean z = false;
        while (true) {
            try {
                try {
                    v2 = get();
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
            } catch (RuntimeException e5) {
                sb2.append("UNKNOWN, cause=[");
                sb2.append(e5.getClass());
                sb2.append(" thrown from get()]");
                return;
            } catch (ExecutionException e6) {
                sb2.append("FAILURE, cause=[");
                sb2.append(e6.getCause());
                sb2.append("]");
                return;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        sb2.append("SUCCESS, result=[");
        sb2.append(v2 == this ? "this future" : String.valueOf(v2));
        sb2.append("]");
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        executor.getClass();
        d dVar = this.f7038l;
        d dVar2 = d.f7019d;
        if (dVar != dVar2) {
            d dVar3 = new d(runnable, executor);
            do {
                dVar3.f7022c = dVar;
                if (f7035p.d(this, dVar, dVar3)) {
                    return;
                } else {
                    dVar = this.f7038l;
                }
            } while (dVar != dVar2);
        }
        c(runnable, executor);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0053, code lost:
    
        return true;
     */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.f7037i
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L8
            r3 = r1
            goto L9
        L8:
            r3 = r2
        L9:
            boolean r4 = r0 instanceof androidx.work.impl.utils.futures.f
            r3 = r3 | r4
            if (r3 == 0) goto L5b
            boolean r3 = androidx.work.impl.utils.futures.i.f7033n
            if (r3 == 0) goto L1f
            androidx.work.impl.utils.futures.a r3 = new androidx.work.impl.utils.futures.a
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r4, r8)
            goto L26
        L1f:
            if (r8 == 0) goto L24
            androidx.work.impl.utils.futures.a r3 = androidx.work.impl.utils.futures.a.f7012c
            goto L26
        L24:
            androidx.work.impl.utils.futures.a r3 = androidx.work.impl.utils.futures.a.f7013d
        L26:
            r4 = r7
            r5 = r2
        L28:
            com.google.common.util.concurrent.p0 r6 = androidx.work.impl.utils.futures.i.f7035p
            boolean r6 = r6.e(r4, r0, r3)
            if (r6 == 0) goto L54
            b(r4)
            boolean r4 = r0 instanceof androidx.work.impl.utils.futures.f
            if (r4 == 0) goto L53
            androidx.work.impl.utils.futures.f r0 = (androidx.work.impl.utils.futures.f) r0
            com.google.common.util.concurrent.d1 r0 = r0.f7029l
            boolean r4 = r0 instanceof androidx.work.impl.utils.futures.i
            if (r4 == 0) goto L50
            r4 = r0
            androidx.work.impl.utils.futures.i r4 = (androidx.work.impl.utils.futures.i) r4
            java.lang.Object r0 = r4.f7037i
            if (r0 != 0) goto L48
            r5 = r1
            goto L49
        L48:
            r5 = r2
        L49:
            boolean r6 = r0 instanceof androidx.work.impl.utils.futures.f
            r5 = r5 | r6
            if (r5 == 0) goto L53
            r5 = r1
            goto L28
        L50:
            r0.cancel(r8)
        L53:
            return r1
        L54:
            java.lang.Object r0 = r4.f7037i
            boolean r6 = r0 instanceof androidx.work.impl.utils.futures.f
            if (r6 != 0) goto L28
            return r5
        L5b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.futures.i.cancel(boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String f() {
        Object obj = this.f7037i;
        if (obj instanceof f) {
            StringBuilder sb2 = new StringBuilder("setFuture=[");
            d1 d1Var = ((f) obj).f7029l;
            return a0.c.p(sb2, d1Var == this ? "this future" : String.valueOf(d1Var), "]");
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public final void g(h hVar) {
        hVar.f7031a = null;
        while (true) {
            h hVar2 = this.f7039m;
            if (hVar2 == h.f7030c) {
                return;
            }
            h hVar3 = null;
            while (hVar2 != null) {
                h hVar4 = hVar2.f7032b;
                if (hVar2.f7031a != null) {
                    hVar3 = hVar2;
                } else if (hVar3 != null) {
                    hVar3.f7032b = hVar4;
                    if (hVar3.f7031a == null) {
                        break;
                    }
                } else if (!f7035p.f(this, hVar2, hVar4)) {
                    break;
                }
                hVar2 = hVar4;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00bd  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00b0 -> B:36:0x0078). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(long r19, java.util.concurrent.TimeUnit r21) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 363
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.futures.i.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f7037i instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r0 instanceof f)) & (this.f7037i != null);
    }

    public final String toString() {
        String strF;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append("[status=");
        if (this.f7037i instanceof a) {
            sb2.append("CANCELLED");
        } else if (isDone()) {
            a(sb2);
        } else {
            try {
                strF = f();
            } catch (RuntimeException e5) {
                strF = "Exception thrown from implementation: " + e5.getClass();
            }
            if (strF != null && !strF.isEmpty()) {
                sb2.append("PENDING, info=[");
                sb2.append(strF);
                sb2.append("]");
            } else if (isDone()) {
                a(sb2);
            } else {
                sb2.append("PENDING");
            }
        }
        sb2.append("]");
        return sb2.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        h hVar = h.f7030c;
        if (!Thread.interrupted()) {
            Object obj2 = this.f7037i;
            if ((obj2 != null) & (!(obj2 instanceof f))) {
                return d(obj2);
            }
            h hVar2 = this.f7039m;
            if (hVar2 != hVar) {
                h hVar3 = new h();
                do {
                    p0 p0Var = f7035p;
                    p0Var.x(hVar3, hVar2);
                    if (p0Var.f(this, hVar2, hVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f7037i;
                            } else {
                                g(hVar3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof f))));
                        return d(obj);
                    }
                    hVar2 = this.f7039m;
                } while (hVar2 != hVar);
            }
            return d(this.f7037i);
        }
        throw new InterruptedException();
    }
}
