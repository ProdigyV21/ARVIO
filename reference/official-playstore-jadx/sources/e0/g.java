package e0;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.util.concurrent.d1;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g implements d1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final boolean f14986n = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Logger f14987o = Logger.getLogger(g.class.getName());

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final f0 f14988p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Object f14989q;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile Object f14990i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile c f14991l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile f f14992m;

    static {
        f0 eVar;
        try {
            eVar = new d(AtomicReferenceFieldUpdater.newUpdater(f.class, Thread.class, CmcdData.OBJECT_TYPE_AUDIO_ONLY), AtomicReferenceFieldUpdater.newUpdater(f.class, f.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g.class, f.class, CmcdData.OBJECT_TYPE_MANIFEST), AtomicReferenceFieldUpdater.newUpdater(g.class, c.class, CmcdData.STREAM_TYPE_LIVE), AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, CmcdData.OBJECT_TYPE_INIT_SEGMENT));
            th = null;
        } catch (Throwable th) {
            th = th;
            eVar = new e();
        }
        f14988p = eVar;
        if (th != null) {
            f14987o.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f14989q = new Object();
    }

    public static void f(g gVar) {
        f fVar;
        c cVar;
        c cVar2;
        c cVar3;
        do {
            fVar = gVar.f14992m;
        } while (!f14988p.e(gVar, fVar, f.f14983c));
        while (true) {
            cVar = null;
            if (fVar == null) {
                break;
            }
            Thread thread = fVar.f14984a;
            if (thread != null) {
                fVar.f14984a = null;
                LockSupport.unpark(thread);
            }
            fVar = fVar.f14985b;
        }
        gVar.d();
        do {
            cVar2 = gVar.f14991l;
        } while (!f14988p.c(gVar, cVar2, c.f14974d));
        while (true) {
            cVar3 = cVar;
            cVar = cVar2;
            if (cVar == null) {
                break;
            }
            cVar2 = cVar.f14977c;
            cVar.f14977c = cVar3;
        }
        while (cVar3 != null) {
            c cVar4 = cVar3.f14977c;
            i(cVar3.f14975a, cVar3.f14976b);
            cVar3 = cVar4;
        }
    }

    public static void i(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e5) {
            f14987o.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e5);
        }
    }

    public static Object j(Object obj) throws ExecutionException {
        if (obj instanceof a) {
            Throwable th = ((a) obj).f14972b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof b) {
            throw new ExecutionException(((b) obj).f14973a);
        }
        if (obj == f14989q) {
            return null;
        }
        return obj;
    }

    public static Object k(g gVar) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = gVar.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public final void a(StringBuilder sb2) {
        try {
            Object objK = k(this);
            sb2.append("SUCCESS, result=[");
            sb2.append(objK == this ? "this future" : String.valueOf(objK));
            sb2.append("]");
        } catch (CancellationException unused) {
            sb2.append("CANCELLED");
        } catch (RuntimeException e5) {
            sb2.append("UNKNOWN, cause=[");
            sb2.append(e5.getClass());
            sb2.append(" thrown from get()]");
        } catch (ExecutionException e6) {
            sb2.append("FAILURE, cause=[");
            sb2.append(e6.getCause());
            sb2.append("]");
        }
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        executor.getClass();
        c cVar = this.f14991l;
        c cVar2 = c.f14974d;
        if (cVar != cVar2) {
            c cVar3 = new c(runnable, executor);
            do {
                cVar3.f14977c = cVar;
                if (f14988p.c(this, cVar, cVar3)) {
                    return;
                } else {
                    cVar = this.f14991l;
                }
            } while (cVar != cVar2);
        }
        i(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.f14990i;
        if (obj == null) {
            if (f14988p.d(this, obj, f14986n ? new a(new CancellationException("Future.cancel() was called."), z) : z ? a.f14969c : a.f14970d)) {
                f(this);
                return true;
            }
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        f fVar = f.f14983c;
        long nanos = timeUnit.toNanos(j10);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f14990i;
        if (obj != null) {
            return j(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            f fVar2 = this.f14992m;
            if (fVar2 != fVar) {
                f fVar3 = new f();
                do {
                    f0 f0Var = f14988p;
                    f0Var.H(fVar3, fVar2);
                    if (f0Var.e(this, fVar2, fVar3)) {
                        while (true) {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                m(fVar3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.f14990i;
                            if (obj2 != null) {
                                return j(obj2);
                            }
                            long jNanoTime2 = jNanoTime - System.nanoTime();
                            if (jNanoTime2 < 1000) {
                                m(fVar3);
                                nanos = jNanoTime2;
                                break;
                            }
                            nanos = jNanoTime2;
                        }
                    } else {
                        fVar2 = this.f14992m;
                    }
                } while (fVar2 != fVar);
            }
            return j(this.f14990i);
        }
        while (nanos > 0) {
            Object obj3 = this.f14990i;
            if (obj3 != null) {
                return j(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "Waited ", " ");
        sbR.append(timeUnit.toString().toLowerCase(locale));
        String string3 = sbR.toString();
        if (nanos + 1000 < 0) {
            String strM = androidx.compose.material3.d.m(string3, " (plus ");
            long j11 = -nanos;
            long jConvert = timeUnit.convert(j11, TimeUnit.NANOSECONDS);
            long nanos2 = j11 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strM2 = strM + jConvert + " " + lowerCase;
                if (z) {
                    strM2 = androidx.compose.material3.d.m(strM2, ",");
                }
                strM = androidx.compose.material3.d.m(strM2, " ");
            }
            if (z) {
                strM = strM + nanos2 + " nanoseconds ";
            }
            string3 = androidx.compose.material3.d.m(strM, "delay)");
        }
        if (isDone()) {
            throw new TimeoutException(androidx.compose.material3.d.m(string3, " but future completed as timeout expired"));
        }
        throw new TimeoutException(androidx.compose.foundation.c.t(string3, " for ", string));
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f14990i instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f14990i != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String l() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public final void m(f fVar) {
        fVar.f14984a = null;
        while (true) {
            f fVar2 = this.f14992m;
            if (fVar2 == f.f14983c) {
                return;
            }
            f fVar3 = null;
            while (fVar2 != null) {
                f fVar4 = fVar2.f14985b;
                if (fVar2.f14984a != null) {
                    fVar3 = fVar2;
                } else if (fVar3 != null) {
                    fVar3.f14985b = fVar4;
                    if (fVar3.f14984a == null) {
                        break;
                    }
                } else if (!f14988p.e(this, fVar2, fVar4)) {
                    break;
                }
                fVar2 = fVar4;
            }
            return;
        }
    }

    public boolean n(Object obj) {
        if (obj == null) {
            obj = f14989q;
        }
        if (!f14988p.d(this, null, obj)) {
            return false;
        }
        f(this);
        return true;
    }

    public boolean o(Throwable th) {
        if (!f14988p.d(this, null, new b(th))) {
            return false;
        }
        f(this);
        return true;
    }

    public final String toString() {
        String strL;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append("[status=");
        if (this.f14990i instanceof a) {
            sb2.append("CANCELLED");
        } else if (isDone()) {
            a(sb2);
        } else {
            try {
                strL = l();
            } catch (RuntimeException e5) {
                strL = "Exception thrown from implementation: " + e5.getClass();
            }
            if (strL != null && !strL.isEmpty()) {
                sb2.append("PENDING, info=[");
                sb2.append(strL);
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

    public void d() {
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        f fVar = f.f14983c;
        if (!Thread.interrupted()) {
            Object obj2 = this.f14990i;
            if (obj2 != null) {
                return j(obj2);
            }
            f fVar2 = this.f14992m;
            if (fVar2 != fVar) {
                f fVar3 = new f();
                do {
                    f0 f0Var = f14988p;
                    f0Var.H(fVar3, fVar2);
                    if (f0Var.e(this, fVar2, fVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f14990i;
                            } else {
                                m(fVar3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return j(obj);
                    }
                    fVar2 = this.f14992m;
                } while (fVar2 != fVar);
            }
            return j(this.f14990i);
        }
        throw new InterruptedException();
    }
}
